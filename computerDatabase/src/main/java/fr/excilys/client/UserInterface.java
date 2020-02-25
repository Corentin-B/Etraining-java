package fr.excilys.client;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import fr.excilys.enumerations.MenuModifSwitch;
import fr.excilys.enumerations.MenuPrincipalSwitch;
import fr.excilys.model.Company;
import fr.excilys.model.Computer;
import fr.excilys.services.ServicesCompany;
import fr.excilys.services.ServicesComputer;

public class UserInterface {

	private final static String REGEX_DATEFORMAT = "([0-9]{4}-[0-9]{2}-[0-9]{2})";
	private final static String REGEX_ANYNUMBER = "([0-9]{1,})";

	
	public static void menuPrincipal() {

		boolean wantStay = true;

		System.out.println("Que voulez vous faire ?" 
						 + "\n1 - Lister les Entreprises" 
						 + "\n2 - Lister les ordinateurs"
						 + "\n3 - Ajouter un ordinateur" 
						 + "\n4 - Modifier un ordinateur" 
						 + "\n5 - Supprimer un ordinateur"
						 + "\n6 - Quitter\n");

		int choixMenuPrincipal = Scanners.scanNumbers(1, 6);

		MenuPrincipalSwitch menuPrincipalSwitch = MenuPrincipalSwitch.values()[choixMenuPrincipal];

		switch (menuPrincipalSwitch) {
		case LIST_ENTREPRISE:// 1
			System.out.println("Lister les Entreprises\n");
			displayCompanyList();
			break;
		case LIST_ORDI:// 2
			System.out.println("Lister les ordinateurs\n");
			displayComputerList();
			break;
		case AJOUTER_ORDI:// 3
			System.out.println("Ajouter un ordinateur\n");
			menuCreationComputer();
			break;
		case CHANGE_ORDI:// 4
			System.out.println("Modifier un ordinateur\n");
			menuUpdateComputer();
			break;
		case SUPP_ORDI:// 5
			System.out.println("Supprimer un ordinateur\n");
			menuRemoveComputer();
			break;
		case QUITTER:// 6
			wantStay = false;
			break;
		default:
			System.out.println("Retour au menu principal\n");
			break;
		}

		if (wantStay) {
			menuPrincipal();
		}
	}

	public static void displayCompanyList() {

		List<Company> company = new ArrayList<>();
		int range = 0;

		do {
			company.removeAll(company);
			company = ServicesCompany.companyList(range);
			
			if(!company.isEmpty()) {
				
				company.stream().forEach(companyDetails -> System.out.println(companyDetails.getId() 
														   + " " + companyDetails.getName()));

				System.out.println("Entrez une touche pour afficher la suite");
				Scanners.scanAnyInput();
			}
			range = range + 20;

		} while (company.size() == 20);
	}

	public static void displayComputerList() {

		List<Computer> computer = new ArrayList<>();
		int range = 0;
		int numberPage = 20;

		do {
			computer.removeAll(computer);
			computer = ServicesComputer.computerList(numberPage, range);
			
			if(!computer.isEmpty()) {
				
				computer.stream().forEach(computerDetails -> System.out.println(computerDetails.getId()
															 + " " + computerDetails.getName()));
				
				System.out.println("Entrez une touche pour afficher la suite");
				Scanners.scanAnyInput();
			}
			range = range + 20;

		} while (computer.size() == 20);	
	}

	public static void menuCreationComputer() {

		String name;
		Date introduced;
		Date discontinued;
		long idCompany;

		System.out.println("Nom de l'ordinateur ?\n");
		name = Scanners.scanText();

		introduced = Date.valueOf(match("Date de mise en servie ? (AAAA-MM-JJ)\n",REGEX_DATEFORMAT));
		
		discontinued = Date.valueOf(match("Date de fin de servie ? (AAAA-MM-JJ)\n",REGEX_DATEFORMAT));

		idCompany = Integer.parseInt(match("Id de l'entreprise ?\n",REGEX_ANYNUMBER));

		Computer computerNew = new Computer.ComputerBuilder()
										   .setName(name)
										   .setIntroduced(introduced)
										   .setDiscontinued(discontinued)
										   .setCompany(new Company.CompanyBuilder()
												   				  .setId(idCompany)
												   				  .build())
										   .build();

		ServicesComputer.computerAdd(computerNew);
	}

	public static void menuUpdateComputer() {

		boolean fini = false;
		boolean execute = true;

		Computer computerUpdate = ServicesComputer.computerSelectForUpdate(Integer.parseInt(match("Quel est l'id de l'ordinateur que vous voulez modifier ?\n",REGEX_ANYNUMBER)));

		do {
			System.out.println("Ordinateur : " + computerUpdate.getName() 
							 + " - " + computerUpdate.getIntroduced()
							 + " - " + computerUpdate.getDiscontinued() 
							 + " - " + computerUpdate.getCompany().getId()
							 + " - " + computerUpdate.getCompany().getName());

			System.out.println("Que voulez vous modifier ?" 
							 + "\n1 - Nom de l'ordinateur"
							 + "\n2 - Date de mise en servie ?" 
							 + "\n3 - Date de fin de servie ?" 
							 + "\n4 - Id de l'entreprise ?"
							 + "\n5 - Ajout des modification à la base" 
							 + "\n6 - Retour Menu Principale");

			int choixMenuModification = Scanners.scanNumbers(1, 6);

			MenuModifSwitch menuModifSwitch = MenuModifSwitch.values()[choixMenuModification];

			switch (menuModifSwitch) {
			case MODIF_NAME_ORDI: // 1
				System.out.println("Nom de l'ordinateur ?\n");
				computerUpdate.setName(Scanners.scanText());
				break;
			case MODIF_INTRODUCED_ORDI: // 2
				computerUpdate.setIntroduced(Date.valueOf(match("Date de mise en servie ? (AAAA-MM-JJ)\n",REGEX_DATEFORMAT)));
				break;
			case MODIF_DISCONTINUED_ORDI: // 3
				computerUpdate.setDiscontinued(Date.valueOf(match("Date de fin de servie ? (AAAA-MM-JJ)\n",REGEX_DATEFORMAT)));
				break;
			case MODIF_ID__ORDI_ENTREPRISE: // 4
				computerUpdate.setCompany(new Company.CompanyBuilder()
		   				  							 .setId(Integer.parseInt(match("Id de l'entreprise ?\n",REGEX_ANYNUMBER)))
		   				  							 .build());
				break;
			case ENVOYER_MODIFICATION: // 5
				System.out.println("Ajout des modification à la base\n");
				fini = true;
				break;
			case QUITTER: // 6
				System.out.println("Retour Menu Principale\n");
				execute = false;
				fini = true;
				break;
			default:
				System.out.println("Retour au menu\n");
				break;
			}
		} while (!fini);

		if (execute) {
			ServicesComputer.computerUpdate(computerUpdate);
		}
	}

	public static void menuRemoveComputer() {

		System.out.println("Id de l'ordinateur ?");
		ServicesComputer.computerRemove(Integer.parseInt(match("Id de l'ordinateur ?\n",REGEX_ANYNUMBER)));
	}
	
	public static String match(String message, String regex) {
		
		String rawValue;
		boolean ismatch = false;
		
		do {		
			System.out.println(message);	
			rawValue = Scanners.scanText();
			ismatch = rawValue.matches(regex);
		} while (!ismatch);
		
		return rawValue;
	}
}
