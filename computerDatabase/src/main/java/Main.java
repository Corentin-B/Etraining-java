import java.sql.SQLException;

import fr.excilys.client.CommandLineUi;
import fr.excilys.services.ServicesCompany;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try {
			ServicesCompany.companyRemove(44);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		
		
		
		
		CommandLineUi.menuPrincipal();
	}
}
