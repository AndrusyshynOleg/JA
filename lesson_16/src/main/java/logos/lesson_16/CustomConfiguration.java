package logos.lesson_16;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import logos.lesson_16.dao.StudentDAO;
import logos.lesson_16.dao.StudentDAOImpl;

@Configuration
public class CustomConfiguration {

	@Bean(name = "studentDAO")
	public StudentDAO getStudentDAO() {
		StudentDAO studentDAO = new StudentDAOImpl();
		return studentDAO;
	}
	
	@Bean(name = "student")
	public Student getStudent() {
		Student student = new Student();
		student.setId(0);
		student.setName("John Doe");
		student.setAge(23);
		return student;
	}

	@Bean(name = "consoleEventLogger")
	public ConsoleEventLogger getConsoleEventLogger() {
		return new ConsoleEventLogger();
	}

	
}