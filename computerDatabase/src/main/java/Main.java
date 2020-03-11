import java.sql.SQLException;

import fr.excilys.client.CommandLineUi;
import fr.excilys.services.ServicesCompany;

public class Main {


	public static void main(String[] args) {

		try {
			ServicesCompany.companyRemove(44);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		}

		CommandLineUi.menuPrincipal();
	}
}
