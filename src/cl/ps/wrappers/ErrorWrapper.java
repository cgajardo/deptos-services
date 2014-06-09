package cl.ps.wrappers;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorWrapper {
	
	private String code;
	private String message;
	
	public ErrorWrapper() {
		this.code = null;
		this.message = null;
	}
	
	public ErrorWrapper(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorWrapper [code=" + code + ", message=" + message + "]";
	}
}
