package logos.lesson17.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Univercity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private Integer level;
	
	@Column
	private Integer numberOfInstitute;
	
	@Column
	private Integer numberOfStudents;
	
	@Column
	private String address;

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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getNumberOfInstitute() {
		return numberOfInstitute;
	}

	public void setNumberOfInstitute(Integer numberOfInstitute) {
		this.numberOfInstitute = numberOfInstitute;
	}

	public Integer getNumberOfStudents() {
		return numberOfStudents;
	}

	public void setNumberOfStudents(Integer numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Univercity [id=" + id + ", name=" + name + ", level=" + level + ", numberOfInstitute="
				+ numberOfInstitute + ", numberOfStudents=" + numberOfStudents + ", address=" + address + "]";
	}
	
	
	
	
}