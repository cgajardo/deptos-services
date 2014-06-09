package cl.ps.controller.exceptions;

public class InvalidArgumentException extends BusinessControllerException {
	/**
	 *
	 */
	private static final long serialVersionUID = -1956797879619909770L;

	public InvalidArgumentException() {
		super(true);
	}

	public InvalidArgumentException(String message, Throwable cause) {
		super(message, cause,true);
	}

	public InvalidArgumentException(String message) {
		super(message,true);
	}

	public InvalidArgumentException(Throwable cause) {
		super(cause,true);
	}

	public InvalidArgumentException(boolean dolog) {
		super(dolog);
	}

	public InvalidArgumentException(String message, boolean dolog) {
		super(message,dolog);
	}

	public InvalidArgumentException(String message, Throwable cause,
			boolean dolog) {
		super(message, cause, dolog);
	}

	public InvalidArgumentException(Throwable cause, boolean dolog) {
		super(cause, dolog);
	}

}
