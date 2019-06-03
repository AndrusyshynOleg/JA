package logos.lesson_16;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import logos.lesson_16.dao.StudentDAO;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

		StudentDAO studentDAO = (StudentDAO) ctx.getBean("studentDAO");
		ConsoleEventLogger cel = (ConsoleEventLogger) ctx.getBean("consoleEventLogger");
		
		//create
		Student student = (Student) ctx.getBean("student");
		studentDAO.create(student);
		
		//read
		Student studentRead = studentDAO.read(0);
		cel.logEvent(studentRead.toString());
		
		//update
		studentRead.setName("Oliver Twist");
		studentDAO.update(studentRead);
		studentRead = studentDAO.read(0);
		cel.logEvent(studentRead.toString());
		
		//delete
		studentDAO.delete(0);
		List<Student> students = new ArrayList<>();
		students = studentDAO.readAll();
		cel.logEvent(students.toString());
		
		
		
		
	}

}
