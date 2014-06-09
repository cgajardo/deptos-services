package cl.ps.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Building implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3532182117486399484L;
	private Long id;
	private String name;
	
	public Building() {
		this.id = null;
		this.name = null;
	}
	
	public Building(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Building [id=" + id + ", name=" + name + "]";
	}

	
}
