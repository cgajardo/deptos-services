package cl.ps.controller.exceptions;

public class ForbiddenException extends BusinessControllerException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4039215518926049675L;

	public ForbiddenException() {
		super(true);
		// TODO Auto-generated constructor stub
	}

	public ForbiddenException(boolean dolog) {
		super(dolog);
		// TODO Auto-generated constructor stub
	}

	public ForbiddenException(String message, boolean dolog) {
		super(message,dolog);
		// TODO Auto-generated constructor stub
	}

	public ForbiddenException(String message, Throwable cause, boolean dolog) {
		super(message, cause, dolog);
		// TODO Auto-generated constructor stub
	}

	public ForbiddenException(String message, Throwable cause) {
		super(message, cause , true);
	}

	public ForbiddenException(String message) {
		super(message,true);
	}

	public ForbiddenException(Throwable cause, boolean dolog) {
		super(cause, dolog);
		// TODO Auto-generated constructor stub
	}

	public ForbiddenException(Throwable cause) {
		super(cause,true);
		// TODO Auto-generated constructor stub
	}

	
}
