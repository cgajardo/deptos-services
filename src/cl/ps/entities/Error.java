/**
 *
 */
package cl.ps.entities;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author pcarreno
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Error implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -3633790261304808250L;

	private String message;
	private String codError;

	public Error() {
	}

	public Error(String message, String codError) {
		this.message = message;
		this.codError = codError;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCodError() {
		return codError;
	}
	public void setCodError(String codError) {
		this.codError = codError;
	}

	@Override
	public String toString() {
		return "Error [message=" + message + ", codError=" + codError + "]";
	}

}
