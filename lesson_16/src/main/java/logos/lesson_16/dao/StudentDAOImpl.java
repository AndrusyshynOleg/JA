package logos.lesson_16.dao;

import java.util.ArrayList;
import java.util.List;

import logos.lesson_16.Student;

public class StudentDAOImpl implements StudentDAO{
	private List<Student> students = new ArrayList<>();

	@Override
	public void create(Student student) {
		students.add(student);
		
	}

	@Override
	public Student read(int id) {
		return students.get(id);
	}

	@Override
	public List<Student> readAll() {
		return students;
	}

	@Override
	public void update(Student student) {
		students.remove(student.getId());
		students.add(student);
	}

	@Override
	public void delete(int id) {
		students.remove(id);
		
	}

}
