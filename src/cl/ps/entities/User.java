package cl.ps.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2764121192980714252L;
	private Long id;
	private String firstname;
	private String secondname;
	private String lastname;
	private String secondlastname;
	private String email;
	private String rut;
	private String phone;
	private String cellphone;
	private Role role;
	private String deptoNumber;
	private Building building;
	
	public User() {
		this.id = null;
		this.firstname = null;
		this.secondname = null;
		this.lastname = null;
		this.secondlastname = null;
		this.email = null;
		this.rut = null;
		this.phone = null;
		this.cellphone = null;
		this.role = null;
		this.deptoNumber = null;
		this.building = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSecondname() {
		return secondname;
	}

	public void setSecondname(String secondname) {
		this.secondname = secondname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSecondlastname() {
		return secondlastname;
	}

	public void setSecondlastname(String secondlastname) {
		this.secondlastname = secondlastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getDeptoNumber() {
		return deptoNumber;
	}

	public void setDeptoNumber(String deptoNumber) {
		this.deptoNumber = deptoNumber;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", secondname="
				+ secondname + ", lastname=" + lastname + ", secondlastname="
				+ secondlastname + ", email=" + email + ", rut=" + rut
				+ ", phone=" + phone + ", cellphone=" + cellphone + ", role="
				+ role + ", deptoNumber=" + deptoNumber + ", building="
				+ building + "]";
	}
	
}
