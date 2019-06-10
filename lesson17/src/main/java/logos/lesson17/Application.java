package logos.lesson17;

import java.text.ParseException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import logos.lesson17.domain.Univercity;
import logos.lesson17.service.UnivercityService;

@SpringBootApplication
public class Application {

	public static void main(String[] args)throws ParseException {
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
		UnivercityService service = ctx.getBean(UnivercityService.class);
		
		Univercity univercity = new Univercity();
		univercity.setName("Economy Univercity");
		univercity.setLevel(7);
		univercity.setNumberOfInstitute(9);
		univercity.setNumberOfStudents(12000);
		univercity.setAddress("Lviv");
		
		service.save(univercity);
		
		System.out.println(service.findById((long) 1));
		
		System.out.println(service.findByName("Technical Univercity"));
		
		Univercity forUpdate = service.findById((long) 3);
		forUpdate.setAddress("Kiev");
		service.update(forUpdate);
		
		service.findAll().stream().forEach(System.out::println);
		
		service.deleteById((long)3);
		
	}

}
