package logos.lesson19.domain;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "students")
public class Student {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private String firstName;
	
	private String lastName;
	
	private Integer age;
	
	private String photoFileName;

	private String fileType;

	@Lob
	private byte[] data;

	public Student(String firstName, String lastName, Integer age, String fileName, String fileType,
			byte[] data) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.photoFileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}

	
	
	public Student() {
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getFileName() {
		return photoFileName;
	}

	public void setFileName(String fileName) {
		this.photoFileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}



	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", photoFileName=" + photoFileName + ", fileType=" + fileType + ", data=" + Arrays.toString(data)
				+ "]";
	}

	
	
}
