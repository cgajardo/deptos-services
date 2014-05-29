package cl.ps.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	
	private String name;
	private String lastname;
	private String mail;
	private String apartment;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getApartment() {
		return apartment;
	}
	public void setApartment(String apartment) {
		this.apartment = apartment;
	}
	

}
