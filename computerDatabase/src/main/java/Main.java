import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.excilys.client.CommandLineUi;
import fr.excilys.configuration.AppConfig;
import fr.excilys.dao.CompanyDao;
import fr.excilys.services.ServicesCompany;

public class Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		CompanyDao companyDao = context.getBean(CompanyDao.class);
		
		System.out.println(companyDao.lister());
		
		try {
			ServicesCompany.companyRemove(44);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		}

		CommandLineUi.menuPrincipal();
		context.close();
	}
}
