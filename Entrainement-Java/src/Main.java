import java.sql.SQLException;

import Company.Company;
import Computer.Computer;
import DAO.DaoFactory;
import DAO.CompanyDaoImpl;
import DAO.ComputerDaoImpl;
import java.sql.Date;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UserInterface.userInterface();
		/*
		DaoFactory dao = DaoFactory.getInstance();
		try {
			dao.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CompanyDaoImpl companyDao = new CompanyDaoImpl(dao);
		
		for (Company details : companyDao.lister()) {
			System.out.println(details.getId() + " " + details.getName());
		}
		
		ComputerDaoImpl computerDao = new ComputerDaoImpl(dao);
		
		Date introduced = Date.valueOf("2006-01-10");
		Date discontinued = Date.valueOf("2016-01-08");
		
		Computer Zasus = new Computer(578, "EvilComp", introduced, discontinued, 40);
		
		//computerDao.ajouter(Zasus);
		
		computerDao.supprimer(577);
		
		computerDao.modifier(Zasus);
		
		for (Computer details : computerDao.lister()) {
			System.out.println(details.getId() + " " + details.getName());
		}
		
		
*/
		
		
		
	}
}
