package cl.ps.ws.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import cl.ps.wrappers.ResponseWrapper;

public class ErrorWSException extends WebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3450986298471677801L;
	
	public ErrorWSException() {
        super(Response.status(Status.OK).build());
    }
	
	public ErrorWSException(String message) {
        super(Response.status(Status.OK).entity(message).build());
    }
	
	public ErrorWSException(ResponseWrapper message) {
        super(Response.status(Status.OK).entity(message).build());
    }

}
