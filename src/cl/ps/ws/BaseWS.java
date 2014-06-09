package cl.ps.ws;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;

import cl.ps.controller.BusinessControllerFactory;
import cl.ps.controller.exceptions.BusinessControllerException;
import cl.ps.controller.exceptions.ForbiddenException;
import cl.ps.controller.exceptions.InvalidArgumentException;
import cl.ps.controller.exceptions.InvalidUserException;
import cl.ps.entities.Error;
import cl.ps.util.Constants;
import cl.ps.util.Enviroment;
import cl.ps.util.LogFactory;
import cl.ps.util.PoolConnection;
import cl.ps.wrappers.ResponseWrapper;
import cl.ps.ws.exceptions.AuthWSException;
import cl.ps.ws.exceptions.ErrorWSException;


/**
 * @ignore
 *
 */
public class BaseWS {

	@Context
	private HttpServletRequest httprequest;
	@Context
	private HttpServletResponse httpresponse;

	private Enviroment enviroment;
	
	protected void forward(String url) throws ServletException, IOException{
		if(httprequest != null)
			httprequest.getRequestDispatcher(url).forward(httprequest, httpresponse);//redirige al servicio de error por token inválido
	}

	public Enviroment getEnviroment() {
		if( httprequest != null ){
			enviroment = (Enviroment) httprequest.getAttribute(Constants.ENVIROMENT);
		}
		return enviroment;
	}

	protected Logger log;
	protected Logger track;
	protected BusinessControllerFactory bcfactory;

	protected BusinessControllerFactory getBCFactory(){
		if( bcfactory == null  )
			bcfactory = new BusinessControllerFactory();
		bcfactory.setEnviroment(getEnviroment());
		return bcfactory;
	}

	protected BaseWS(){
		log = LogFactory.getLogger();
		track = LogFactory.getTracker();
	}

	protected ResponseWrapper prefillResponse(){
		ResponseWrapper response = new ResponseWrapper();
		   Integer currentPage = -1;

		   //verificar la validez de la sesion y setear el origen

		   if( getEnviroment() != null){
			    //obtiene el destino

			    //verifica si se solicito alguna pagina en particular
			    if( getEnviroment().get(Constants.ENVIROMENT_REQUESTEDPAGE) != null )
			    	currentPage = (Integer) getEnviroment().get(Constants.ENVIROMENT_REQUESTEDPAGE);
			    //si se solicitó, la setea en el controlador
			    //sino retorna la primera página y el total
		   }
		   response.fillResponse(currentPage,null);

		    return response;
	}

	/**
	 * 
	 * @param response
	 * @return
	 */
	protected ResponseWrapper postResponse( ResponseWrapper response ){

		Integer totalPage = -1;
		Integer currentPage = response.getCurrentPage();
		   //verificar la validez de la sesion y setear el origen

		   if( getEnviroment() != null){
			    //verificar si del total page
			    if( getEnviroment().get(Constants.ENVIROMENT_TOTALPAGES) != null )
			    	totalPage = (Integer)getEnviroment().get(Constants.ENVIROMENT_TOTALPAGES);
			    
			    if( getEnviroment().get(Constants.ENVIROMENT_REQUESTEDPAGE) != null )
			    	currentPage = (Integer) getEnviroment().get(Constants.ENVIROMENT_REQUESTEDPAGE);
		   }
		   
		   
		   
		   response.fillResponse(currentPage,totalPage);

		    return response;
	}

	/**
	 * 
	 * @param response
	 * @param sqle
	 * @return
	 */
	protected ResponseWrapper errorDefault(ResponseWrapper response, SQLException sqle){
		log.error("SQLERROR",sqle);
		response.setError(new Error(sqle.getMessage(),sqle.getErrorCode()+"")); //TODO DEFINIR EL MENSAJE Y EL CODIGO
		//return response;
		throw new ErrorWSException(response);
	}
	
	/**
	 * 
	 * @param response
	 * @param e
	 * @return
	 */
	protected ResponseWrapper errorDefault(ResponseWrapper response, Exception e){
		return errorDefault(response,e,true);
	}
	
	/**
	 * 
	 * @param response
	 * @param e
	 * @param dolog
	 * @return
	 */
	protected ResponseWrapper errorDefault(ResponseWrapper response, Exception e, boolean dolog){
		
		if( e instanceof ForbiddenException || e instanceof InvalidUserException )
			return errorAuth(response, e, null);
		
		if( dolog )
			log.error("ERROR",e);
		
		if( e != null && (
			e instanceof BusinessControllerException ||
			e instanceof InvalidArgumentException )){
			response.setError(new Error(e.getMessage(), ((BusinessControllerException)e).getCode() )); 		
		}else { // en caso de ser una excepción normal, se mantiene el comportamiento pasado
			if( e != null )
				response.setError(new Error(e.getMessage(), "-1" )); 
			else
				response.setError(new Error("Error", "-1" ));
		}
		response.setData(false);
		//return response; 
		throw new ErrorWSException(response);
	}
	
	/**
	 * Permite crear el objeto de error a la respuesta generado
	 * @param response Objeto de respuesta a setear
	 * @param exception Objecto de excepción a setear
	 * @param code Codigo de retorno, pueden ser : 1 = Existe otra sesión abierta
	 * @return
	 */
	protected ResponseWrapper errorDefault(ResponseWrapper response, Exception exception,String code, boolean dolog){
		if( dolog )
			log.error("ERROR",exception);
		response.setError(new Error(exception.getMessage(),code)); //TODO DEFINIR EL MENSAJE Y EL CODIGO
		//return response;
		throw new ErrorWSException(response);
	}
	
	/**
	 * 
	 * @param response
	 * @param exception
	 * @param code
	 * @return
	 */
	protected ResponseWrapper errorDefault(ResponseWrapper response, Exception exception,String code){
		return errorDefault(response, exception,code,true);
	}
	
	/**
	 * Permite crear el objeto de error a la respuesta generado
	 * @param response Objeto de respuesta a setear
	 * @param exception Objecto de excepción a setear
	 * @param code Codigo de retorno, pueden ser : 1 = Existe otra sesión abierta
	 * @return
	 */
	protected ResponseWrapper errorAuth(ResponseWrapper response, Exception exception,String code, boolean dolog){
		if( dolog )
			log.error("ERROR AUTENTIFICACION",exception);
		response.setError(new Error(exception.getMessage(),code)); //TODO DEFINIR EL MENSAJE Y EL CODIGO
		//return response;
		throw new AuthWSException(response);
	}
	
	/**
	 * 
	 * @param response
	 * @param exception
	 * @param code
	 * @return
	 */
	protected ResponseWrapper errorAuth(ResponseWrapper response, Exception exception,String code){
		return errorAuth(response, exception,code,true);
	}
	
	private static PoolConnection poolConnection;
	// Saca conexion del pool
	public Connection getConnection(){
		if(poolConnection == null)
			poolConnection = new PoolConnection();
		
		return poolConnection.getConnection();
	}
	// Deja conexion en el pool
	public void putConnection(Connection con){
		poolConnection.putConnection(con);
	}
}
