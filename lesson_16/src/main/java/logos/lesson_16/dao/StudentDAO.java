package logos.lesson_16.dao;

import java.util.List;

import logos.lesson_16.Student;

public interface StudentDAO {
	
	void create(Student student);
	Student read(int id);
	List<Student> readAll();
	void update(Student student);
	void delete(int id);

}
