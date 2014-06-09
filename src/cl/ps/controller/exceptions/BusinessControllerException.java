/**
 *
 */
package cl.ps.controller.exceptions;

import org.apache.log4j.Logger;

import cl.ps.util.LogFactory;

/**
 * @author pcarreno
 *
 */
public class BusinessControllerException extends Exception {

	protected Logger log;
	private String code = "-1";	
	/**
	 *
	 */
	private static final long serialVersionUID = -8315299421264087897L;

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BusinessControllerException(String message){
		super(message);
		init(false);
	}
	
	public BusinessControllerException(String message, String code){
		super(message);
		this.code = code;
		init(false);
	}

	public BusinessControllerException() {
		super();
		init(false);
	}

	public BusinessControllerException(String message, Throwable cause) {
		super(message, cause);
		init(false);
	}

	public BusinessControllerException(Throwable cause) {
		super(cause);
		init(false);
	}

	public BusinessControllerException(String message,boolean dolog){
		super(message);
		init(dolog);
	}

	public BusinessControllerException(boolean dolog) {
		super();
		init(dolog);
	}

	public BusinessControllerException(String message, Throwable cause,boolean dolog) {
		super(message, cause);
		init(dolog);
	}

	public BusinessControllerException(Throwable cause,boolean dolog) {
		super(cause);
		init(dolog);
	}

	private void init(boolean dolog){
		if( dolog ){
			log = LogFactory.getLogger();
			log.error(getMessage(),this);
		}
	}
}
