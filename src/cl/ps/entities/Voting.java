package cl.ps.entities;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Voting implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3532182117486399484L;
	private Long id;
	private String title;
	private String description;
	private Building building;
	private User user;
	private Date dateCreated;
	private Long deleted;
	private Long possibleAnwers;
	
	public Voting() {
		this.id = null;
		this.title = null;
		this.description = null;
	}
	
	public Voting(Long id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getDeleted() {
		return deleted;
	}

	public void setDeleted(Long deleted) {
		this.deleted = deleted;
	}

	public Long getPossibleAnwers() {
		return possibleAnwers;
	}

	public void setPossibleAnwers(Long possibleAnwers) {
		this.possibleAnwers = possibleAnwers;
	}

	@Override
	public String toString() {
		return "Voting [id=" + id + ", title=" + title + ", description="
				+ description + ", building=" + building + ", user=" + user
				+ ", dateCreated=" + dateCreated + ", deleted=" + deleted
				+ ", possibleAnwers=" + possibleAnwers + "]";
	}

}
