package cl.ps.controller.exceptions;

public class InvalidUserException extends BusinessControllerException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -69172440437170664L;

	public InvalidUserException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidUserException(boolean dolog) {
		super(dolog);
		// TODO Auto-generated constructor stub
	}

	public InvalidUserException(String message, boolean dolog) {
		super(message, dolog);
		// TODO Auto-generated constructor stub
	}

	public InvalidUserException(String message, Throwable cause, boolean dolog) {
		super(message, cause, dolog);
		// TODO Auto-generated constructor stub
	}

	public InvalidUserException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidUserException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidUserException(Throwable cause, boolean dolog) {
		super(cause, dolog);
		// TODO Auto-generated constructor stub
	}

	public InvalidUserException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
