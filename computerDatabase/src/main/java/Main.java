import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.excilys.client.CommandLineUi;
import fr.excilys.configuration.AppConfig;
import fr.excilys.services.ServicesCompany;



public class Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		ServicesCompany serviceCompany = new ServicesCompany();
		
		serviceCompany.companyList().forEach(System.out::println);
		
		try {
			serviceCompany.companyRemove(44);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		CommandLineUi.menuPrincipal();
		context.close();
	}
}