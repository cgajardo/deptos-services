package cl.ps.ws.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import cl.ps.wrappers.ResponseWrapper;

/**
 * @author pcarreno
 *
 */
public class AuthWSException extends WebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8908178831892319649L;

	
	public AuthWSException() {
        super(Response.status(Status.UNAUTHORIZED).build());
    }
	
	public AuthWSException(String message) {
        super(Response.status(Status.UNAUTHORIZED).entity(message).build());
    }
	
	public AuthWSException(ResponseWrapper message) {
        super(Response.status(Status.UNAUTHORIZED).entity(message).build());
    }
}
