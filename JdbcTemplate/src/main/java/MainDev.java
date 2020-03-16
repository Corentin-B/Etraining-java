import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.journaldev.model.Person;
import com.journaldev.spring.config.AppConfig;
import com.journaldev.spring.dao.PersonDAO;

public class MainDev {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		PersonDAO personDAO = context.getBean(PersonDAO.class);

		System.out.println("List of person is:");

		for (Person p : personDAO.getAllPersons()) {
			System.out.println(p);
		}
		
		System.out.println("STOP");
		context.close();
	}
}
