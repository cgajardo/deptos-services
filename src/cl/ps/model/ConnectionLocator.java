/**
 *
 */
package cl.ps.model;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import cl.ps.model.exceptions.ConnectionException;
import cl.ps.util.App;
import cl.ps.util.LogFactory;

import com.mysql.jdbc.CommunicationsException;
import com.sun.jersey.spi.resource.Singleton;

/**
 * @author pcarreno
 * Clase que permite obtener una conexión del datasource y el mismo datasource
 *
 */
@Singleton
public class ConnectionLocator {

	private static Logger log = LogFactory.getLogger();

	private static DataSource ds;
	private static DataSource dsreadonly;
	private static DataSource etlds;

	public static DataSource getDataSource(){
		if(ds == null)
			initDataSource();
		return ds;
	}
	
	
	public static DataSource getETLSource(){
		if(etlds == null)
			initETLSource();
		return etlds;
	}

	public static DataSource getDataSourceReadOnly(){
		if(dsreadonly == null)
			initDataSource();
		return dsreadonly;
	}
	
	public static Connection getConnection() throws ConnectionException{
		return getConnection(false);
	}
	
	public static Connection getETLConnection() throws ConnectionException{
		int tries = 3;
		if(log != null )log.debug("Se ha solicitado la conexión con los ETL.");
		try{
			int count = 0;
			while( count < tries ){
				try{
						return getETLSource().getConnection();
				}catch(CommunicationsException e ){
					log.error("Error de comunicación con la base de datos de los ETL ("+count+")");
				}
				count++;
			}
			
			throw new ConnectionException("Error al obtener la conexión a los ETL: Se alcanzó el máximo número de solicitudes de conexión.");
		}catch(SQLException sqle){
			throw new ConnectionException("Error al obtener la conexión a los ETL.",sqle);
		}
	}

	public static Connection getConnection(boolean readonly) throws ConnectionException{
		
		int tries = 3;
		if(log != null )log.debug("Se ha solicitado la conexión.");
		try{
			int count = 0;
			while( count < tries ){
				try{
					if(readonly)
						return getDataSourceReadOnly().getConnection();
					else
						return getDataSource().getConnection();
				}catch(CommunicationsException e ){
					log.error("Error de comunicación con la base de datos ("+count+")");
				}
				count++;
			}
			
			throw new ConnectionException("Error al obtener la conexión: Se alcanzó el máximo número de solicitudes de conexión.");
		}catch(SQLException sqle){
			throw new ConnectionException("Error al obtener la conexión.",sqle);
		}
	}

	private static void initDataSource(){
		log.debug("Iniciando DataSources...");
		try {
			Context ctx = new InitialContext();
			App app = App.getInstance();
			final String jndi = app.getProperty("jdbcjndi");
			final String jndireadonly = app.getProperty("jdbcjndireadonly");
			ds = (DataSource)ctx.lookup("java:comp/env/"+jndi);
			dsreadonly = (DataSource)ctx.lookup("java:comp/env/"+jndireadonly);
		  } catch (NamingException e) {
			  if(log != null )log.error("Error al iniciar el DataSource.",e);
		  }
	}
	
	private static void initETLSource() {
		log.debug("Iniciando DataSources...");
		try {
			Context ctx = new InitialContext();
			App app = App.getInstance();
			final String jndi = app.getProperty("jdbcetljndi");
			etlds = (DataSource)ctx.lookup("java:comp/env/"+jndi);
	  } catch (NamingException e) {
		  if(log != null )log.error("Error al iniciar el ETLSource.",e);
	  }
	}

	/**
	 * Permite cerrar una conexión abierta
	 * @param con
	 */
	public static void closeConnection(Connection con) {
		if(con != null)
			try{ if(!con.isClosed()) con.close(); } catch( SQLException e){ if(log != null )log.error("Error al intentar cerrar la conexión.",e);}
	}

}
