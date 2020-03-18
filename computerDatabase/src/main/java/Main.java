import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.excilys.client.CommandLineUi;
import fr.excilys.configuration.AppConfig;
import fr.excilys.dao.CompanyDao;

public class Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		CompanyDao companyDao = context.getBean(CompanyDao.class);
		
		companyDao.lister().forEach(System.out::println);
		
		companyDao.remove(44);

		CommandLineUi.menuPrincipal();
		context.close();
	}
}
