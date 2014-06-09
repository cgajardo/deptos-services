package cl.ps.wrappers;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import cl.ps.entities.Error;

/**
 * @author pcarreno
 *
 */
@XmlRootElement
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ResponseWrapper implements Serializable  {

	/**
	 *
	 */
	private static final long serialVersionUID = -1179133022986332707L;

	/** METADATA **/
	private Error error;

	private Integer currentPage;
	private Integer totalRows;

	/** DATA **/
	private Object data;

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object json) {
		this.data = json;
	}

	/**
		 * @param response
		 * @param data
		 * @param locale
		 * @param currentPage
		 * @param totalPage
		 */
	public void fillResponse(Integer currentPage, Integer totalPage) {
		if( currentPage != null )
			setCurrentPage(currentPage);
		if( totalPage != null )
			setTotalRows(totalPage);
	}

	@Override
	public String toString() {
		return "ResponseWrapper [error=" + error + ", currentPage="
				+ currentPage + ", totalRows=" + totalRows + ", data=" + data
				+ "]";
	}
	
	

}
