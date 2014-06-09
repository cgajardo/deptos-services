/**
 *
 */
package cl.ps.model.exceptions;

import org.apache.log4j.Logger;

import cl.ps.util.LogFactory;

public class DAOException extends Exception {

	protected Logger log;
	protected String errorCode;
	
	public String getErrorCode(){
		return errorCode;
	}

	private static final long serialVersionUID = 1908768953402281955L;

	public DAOException() {
		super();
		init();
	}


	public DAOException(String message, Throwable cause) {
		super(message, cause);
		init();
	}

	public DAOException(String message) {
		super(message);
		init();
	}

	public DAOException(Throwable cause) {
		super(cause);
		init();
	}

	private void init(){
		log = LogFactory.getLogger();
		log.error(getMessage(),this);
	}
}
