package Client;

import java.sql.Date;
import java.util.List;

import Company.Company;
import Computer.Computer;
import Services.ServicesCompany;
import Services.ServicesComputer;

public class UserInterface {

	public static void menuPrincipal() {

		boolean wantStay = true;

		System.out.println("Que voulez vous faire ?" + "\n1 - Lister les Entreprises" + "\n2 - Lister les ordinateurs"
				+ "\n3 - Ajouter un oridnateur" + "\n4 - Modifier un ordinateur" + "\n5 - Supprimer un ordinateur"
				+ "\n6 - Quitter");

		int choix = Scanners.ScanNumbers(1, 6);

		switch (choix) {
		case 1:
			System.out.println("Lister les Entreprises");
			displayCompanyList();
			break;
		case 2:
			System.out.println("Lister les ordinateurs");
			displayComputerList();
			break;
		case 3:
			System.out.println("Ajouter un oridnateur");
			ServicesComputer.computerAdd();
			break;
		case 4:
			System.out.println("Modifier un ordinateur");
			menuUpdateCreationComputer();
			break;
		case 5:
			System.out.println("Supprimer un ordinateur");
			menuRemoveComputer();
			break;
		case 6:
			wantStay = false;
			break;
		default:
			System.out.println("Retour au menu principal");
			break;
		}
		if (wantStay) {
			menuPrincipal();
		}
	}

	public static void displayCompanyList() {

		List<Company> company = ServicesCompany.companyList();

		for (Company details : company) {

			System.out.println(details.getId() + " " + details.getName());
		}
	}

	public static void displayComputerList() {

		List<Computer> computer = ServicesComputer.computerList();

		for (Computer details : computer) {

			System.out.println(details.getId() + " " + details.getName());
		}
	}

	public static Computer menuCreationComputer() {

		System.out.println("Nom de l'ordinateur ?");
		String name = Scanners.ScanText();

		System.out.println("Date de mise en servie ? (AAAA-MM-JJ)");
		Date introduced = Date.valueOf(Scanners.ScanText());

		System.out.println("Date de fin de servie ? (AAAA-MM-JJ)");
		Date discontinued = Date.valueOf(Scanners.ScanText());

		System.out.println("Id de l'entreprise ?");
		int idCompany = Scanners.ScanNumber();

		Computer computerNew = new Computer(0, name, introduced, discontinued, idCompany);

		return computerNew;
	}

	public static void menuUpdateCreationComputer() {

		System.out.println("Quel est l'id de l'ordinateur que vous voulez modifier ?");

		menuUpdateComputer(ServicesComputer.computerSelectForUpdate(Scanners.ScanNumber()));
	}

	public static void menuUpdateComputer(Computer computerUpdate) {

		int choix = 0;
		boolean fini = false;

		do {
			System.out.println("Ordinateur : " + computerUpdate.getName() + " - " + computerUpdate.getIntroduced()
					+ " - " + computerUpdate.getDiscontinued() + " - " + computerUpdate.getCompany_id());
			System.out.println(
					"Que voulez vous modifier ?" + "\n1 - Nom de l'ordinateur" + "\n2 - Date de mise en servie ?"
							+ "\n3 - Date de fin de servie ?" + "\n4 - Id de l'entreprise ?" + "\n5 - Fin");

			choix = Scanners.ScanNumbers(1, 5);

			switch (choix) {
			case 1:
				System.out.println("Nom de l'ordinateur ?");
				computerUpdate.setName(Scanners.ScanText());
				break;
			case 2:
				System.out.println("Date de mise en servie ? (AAAA-MM-JJ)");
				computerUpdate.setIntroduced(Date.valueOf(Scanners.ScanText()));
				break;
			case 3:
				System.out.println("Date de fin de servie ? (AAAA-MM-JJ)");
				computerUpdate.setDiscontinued(Date.valueOf(Scanners.ScanText()));
				break;
			case 4:
				System.out.println("Id de l'entreprise ?");
				computerUpdate.setCompany_id(Scanners.ScanNumber());
				break;
			case 5:
				System.out.println("Ajout des modification à la base");
				fini = true;
				break;
			default:
				System.out.println("Retour au menu");
				break;
			}
		} while (!fini);
		ServicesComputer.computerUpdate(computerUpdate);
	}

	public static void menuRemoveComputer() {

		System.out.println("Id de l'ordinateur ?");
		ServicesComputer.computerRemove(Scanners.ScanNumber());
	}
}
