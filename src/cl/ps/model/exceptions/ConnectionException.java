/**
 *
 */
package cl.ps.model.exceptions;

public class ConnectionException extends DAOException {

	/**
	 *
	 */
	private static final long serialVersionUID = 8718075365790155134L;

	public ConnectionException() {
		super();
	}

	public ConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConnectionException(String message) {
		super(message);
	}

	public ConnectionException(Throwable cause) {
		super(cause);
	}

}
