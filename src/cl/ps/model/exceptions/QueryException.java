/**
 *
 */
package cl.ps.model.exceptions;

/**
 * @author pcarreno
 *
 */
public class QueryException extends DAOException {

	/**
	 *
	 */
	private static final long serialVersionUID = 2351772399864105565L;

	public QueryException() {
		super();
	}

	public QueryException(String message, Throwable cause) {
		super(message, cause);
	}

	public QueryException(String message) {
		super(message);
	}

	public QueryException(Throwable cause) {
		super(cause);
	}

}
