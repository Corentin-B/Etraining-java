import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

import Company.Company;
import Computer.Computer;
import DAO.CompanyDaoImpl;
import DAO.ComputerDaoImpl;
import DAO.DaoFactory;

public class UserInterface {

	public static void userInterface() {
			
		Scanner monScanner = new Scanner(System.in);	
		System.out.println("Que voulez vous faire ?"
						  +"\n1 - Lister les Entreprises"
						  +"\n2 - Lister les ordinateurs"
						  +"\n3 - Ajouter un oridnateur"
						  +"\n4 - Modifier un ordinateur"
						  +"\n5 - Supprimer un ordinateur");
		int choix = 0;
		
		choix = monScanner.nextInt();
		monScanner.nextLine();
		
		if(choix < 1 && choix > 4) {
			choix = 0;
		}
		
		switch (choix) {
		case 1 :
			System.out.println("Lister les Entreprises");
			companyList();
			break;
		case 2 :
			System.out.println("Lister les ordinateurs");
			computerList();
			break;
		case 3 :
			System.out.println("Ajouter un oridnateur");
			computerAdd();
			break;
		case 4 :
			System.out.println("Modifier un ordinateur");
			System.out.println("Quel est l'id de l'ordinateur que vous voulez modifier ?");
			int idUpdateComputer = monScanner.nextInt();
			
			computerUpdate(idUpdateComputer);
			break;
		case 5 :
			System.out.println("Supprimer un ordinateur");
			computerRemove();
			break;
		default:
			System.out.println("Retour au menu principal");
			break;	
		}
		userInterface();
	}


	private static void companyList() {
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
	}
	
	
	private static void computerList() {
		DaoFactory dao = DaoFactory.getInstance();
		try {
			dao.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ComputerDaoImpl computerDao = new ComputerDaoImpl(dao);
		
		for (Computer details : computerDao.lister()) {
			System.out.println(details.getId() + " " + details.getName());
		}
	}


	private static Computer computerGetInfo() {
		
		Scanner monScanner = new Scanner(System.in);	
		String name;
		Date introduced;
		Date discontinued;
		int idCompany;
		
		System.out.println("Nom de l'ordinateur ?");
		name = monScanner.nextLine();
		
		System.out.println("Date de mise en servie ? (AAAA-MM-JJ)");
		introduced = Date.valueOf(monScanner.nextLine());
		
		System.out.println("Date de fin de servie ? (AAAA-MM-JJ)");
		discontinued = Date.valueOf(monScanner.nextLine());
		
		System.out.println("Id de l'entreprise ?");
		idCompany = monScanner.nextInt();
		monScanner.nextLine();
		
		Computer computerNew = new Computer(0, name, introduced, discontinued, idCompany);
		
		return computerNew;
	}
	
	
	private static void computerAdd() {
		DaoFactory dao = DaoFactory.getInstance();
		try {
			dao.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ComputerDaoImpl computerDao = new ComputerDaoImpl(dao);
		Computer computerNew = computerGetInfo();
		computerDao.ajouter(computerNew);
	}

	
	private static Computer computerUpdateInfo(Computer computerUpdate) {
		
		Scanner monScanner = new Scanner(System.in);	
		int choix = 0;
		String name;
		Date introduced;
		Date discontinued;
		int idCompany;
		boolean fini = false;
		
		do{
		System.out.println("Ordinateur : "+ computerUpdate.getName() +" - "+ computerUpdate.getIntroduced() +" - "+ computerUpdate.getDiscontinued() +" - "+ computerUpdate.getCompany_id());
		System.out.println("Que voulez vous modifier ?"
				  +"\n1 - Nom de l'ordinateur"
				  +"\n2 - Date de mise en servie ?"
				  +"\n3 - Date de fin de servie ?"
				  +"\n4 - Id de l'entreprise ?"
				  +"\n5 - Fin");
		
		choix = monScanner.nextInt();
		monScanner.nextLine();
		
		switch (choix) {
		case 1 :
			System.out.println("Nom de l'ordinateur ?");
			name = monScanner.nextLine();
			computerUpdate.setName(name);
			break;
		case 2 :
			System.out.println("Date de mise en servie ? (AAAA-MM-JJ)");
			introduced = Date.valueOf(monScanner.nextLine());
			computerUpdate.setIntroduced(introduced);
			break;
		case 3 :
			System.out.println("Date de fin de servie ? (AAAA-MM-JJ)");
			discontinued = Date.valueOf(monScanner.nextLine());
			computerUpdate.setDiscontinued(discontinued);
			break;
		case 4 :
			System.out.println("Id de l'entreprise ?");
			idCompany = monScanner.nextInt();
			computerUpdate.setCompany_id(idCompany);
			break;
		case 5 :
			System.out.println("Ajout des modification à la base");
			fini = true;
			break;
		default:
			System.out.println("Retour au menu");
			break;	
		}
		} while (!fini);
		return computerUpdate;
	}
	

	private static void computerUpdate(int idComputer) {
		DaoFactory dao = DaoFactory.getInstance();
		try {
			dao.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ComputerDaoImpl computerDao = new ComputerDaoImpl(dao);
		Computer selectedComputer = computerDao.selectionner(idComputer);
		
		Computer computerUpdate = computerUpdateInfo(selectedComputer);
		computerDao.modifier(computerUpdate);
	}


	private static void computerRemove() {
		
		Scanner monScanner = new Scanner(System.in);	
		int idComputer;
		
		System.out.println("Id de l'ordinateur ?");
		idComputer = monScanner.nextInt();
				
		
		DaoFactory dao = DaoFactory.getInstance();
		try {
			dao.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ComputerDaoImpl computerDao = new ComputerDaoImpl(dao);
		computerDao.supprimer(idComputer);
	}
}













