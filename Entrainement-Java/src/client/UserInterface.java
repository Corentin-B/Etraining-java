package client;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import company.Company;
import computer.Computer;
import enumerations.MenuModifSwitch;
import enumerations.MenuPrincipalSwitch;
import services.ServicesCompany;
import services.ServicesComputer;

public class UserInterface {

	public static void menuPrincipal() {

		boolean wantStay = true;

		System.out.println("Que voulez vous faire ?" + "\n1 - Lister les Entreprises" + "\n2 - Lister les ordinateurs"
				+ "\n3 - Ajouter un oridnateur" + "\n4 - Modifier un ordinateur" + "\n5 - Supprimer un ordinateur"
				+ "\n6 - Quitter");

		int choix = Scanners.scanNumbers(1, 6);

		MenuPrincipalSwitch menuPrincipalSwitch = MenuPrincipalSwitch.values()[choix];

		switch (menuPrincipalSwitch) {
		case LIST_ENTREPRISE:
			System.out.println("Lister les Entreprises");
			displayCompanyList();
			break;
		case LIST_ORDI:
			System.out.println("Lister les ordinateurs");
			displayComputerList();
			break;
		case AJOUTER_ORDI:
			System.out.println("Ajouter un oridnateur");
			ServicesComputer.computerAdd();
			break;
		case CHANGE_ORDI:
			System.out.println("Modifier un ordinateur");
			menuUpdateCreationComputer();
			break;
		case SUPP_ORDI:
			System.out.println("Supprimer un ordinateur");
			menuRemoveComputer();
			break;
		case QUITTER:
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

		List<Company> company = new ArrayList<Company>();
		int range = 0;

		do {
			company.removeAll(company);
			company = ServicesCompany.companyList(range);

			for (Company details : company) {

				System.out.println(details.getId() + " " + details.getName());
			}

			System.out.println("Entrez une touche pour afficher la suite");
			Scanners.scanAnyInput();

			range = range + 20;

		} while (company.size() == 20);
	}

	public static void displayComputerList() {

		List<Computer> computer = new ArrayList<Computer>();
		int range = 0;

		do {
			computer.removeAll(computer);
			computer = ServicesComputer.computerList(range);

			for (Computer details : computer) {

				System.out.println(details.getId() + " " + details.getName());
			}

			System.out.println("Entrez une touche pour afficher la suite");
			Scanners.scanAnyInput();

			range = range + 20;

		} while (computer.size() == 20);
	}

	public static Computer menuCreationComputer() {

		System.out.println("Nom de l'ordinateur ?");
		String name = Scanners.scanText();

		System.out.println("Date de mise en servie ? (AAAA-MM-JJ)");
		Date introduced = Date.valueOf(Scanners.scanText());

		System.out.println("Date de fin de servie ? (AAAA-MM-JJ)");
		Date discontinued = Date.valueOf(Scanners.scanText());

		System.out.println("Id de l'entreprise ?");
		int idCompany = Scanners.scanNumber();

		Computer computerNew = new Computer(0, name, introduced, discontinued, idCompany);

		return computerNew;
	}

	public static void menuUpdateCreationComputer() {

		System.out.println("Quel est l'id de l'ordinateur que vous voulez modifier ?");

		menuUpdateComputer(ServicesComputer.computerSelectForUpdate(Scanners.scanNumber()));
	}

	public static void menuUpdateComputer(Computer computerUpdate) {

		int choix = 0;
		boolean fini = false;
		boolean execute = true;

		do {
			System.out.println("Ordinateur : " + computerUpdate.getName() + " - " + computerUpdate.getIntroduced()
					+ " - " + computerUpdate.getDiscontinued() + " - " + computerUpdate.getCompany_id());
			System.out.println(
					"Que voulez vous modifier ?" + "\n1 - Nom de l'ordinateur" + "\n2 - Date de mise en servie ?"
							+ "\n3 - Date de fin de servie ?" + "\n4 - Id de l'entreprise ?" + "\n5 - Fin" + "\n6 - Retour Menu Principale");

			choix = Scanners.scanNumbers(1, 6);

			MenuModifSwitch menuModifSwitch = MenuModifSwitch.values()[choix];

			switch (menuModifSwitch) {
			case MODIF_NAME_ORDI:
				System.out.println("Nom de l'ordinateur ?");
				computerUpdate.setName(Scanners.scanText());
				break;
			case MODIF_INTRODUCED_ORDI:
				System.out.println("Date de mise en servie ? (AAAA-MM-JJ)");
				computerUpdate.setIntroduced(Date.valueOf(Scanners.scanText()));
				break;
			case MODIF_DISCONTINUED_ORDI:
				System.out.println("Date de fin de servie ? (AAAA-MM-JJ)");
				computerUpdate.setDiscontinued(Date.valueOf(Scanners.scanText()));
				break;
			case MODIF_ID__ORDI_ENTREPRISE:
				System.out.println("Id de l'entreprise ?");
				computerUpdate.setCompany_id(Scanners.scanNumber());
				break;
			case ENVOYER_MODIFICATION:
				System.out.println("Ajout des modification à la base");
				fini = true;
				break;
			case QUITTER:
				System.out.println("Retour Menu Principale");
				execute = false;
				fini = true;
				break;
			default:
				System.out.println("Retour au menu");
				break;
			}
		} while (!fini);

		if (execute) {
			ServicesComputer.computerUpdate(computerUpdate);
		}

	}

	public static void menuRemoveComputer() {

		System.out.println("Id de l'ordinateur ?");
		ServicesComputer.computerRemove(Scanners.scanNumber());
	}
}
