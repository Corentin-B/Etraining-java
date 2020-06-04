package fr.excilys.client;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.excilys.model.Company;
import fr.excilys.model.Computer;
import fr.excilys.services.ServicesCompany;
import fr.excilys.services.ServicesComputer;

public class CommandLineUi {

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

		int choixMenuPrincipal = VerrificationUi.scanNumbers(1, 6);

		EnumMenuPrincipalSwitch menuPrincipalSwitch = EnumMenuPrincipalSwitch.values()[choixMenuPrincipal];

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
			company = ServicesCompany.companyList();
			
			if(!company.isEmpty()) {
				
				company.stream().forEach(companyDetails -> System.out.println(companyDetails.getId() 
														   + " " + companyDetails.getName()));

				System.out.println("Entrez une touche pour afficher la suite");
				VerrificationUi.scanAnyInput();
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
			computer = ServicesComputer.computerList(range,numberPage, null ,null);
			
			if(!computer.isEmpty()) {
				
				computer.stream().forEach(computerDetails -> System.out.println(computerDetails.getId()
															 + " " + computerDetails.getName() + " - " + computerDetails.getCompany().getName()));
				
				System.out.println("Entrez une touche pour afficher la suite");
				VerrificationUi.scanAnyInput();
			}
			range = range + 20;

		} while (computer.size() == 20);	
	}

	public static void menuCreationComputer() {

		String name;
		LocalDate introduced;
		LocalDate discontinued;
		long idCompany;

		System.out.println("Nom de l'ordinateur ?\n");
		name = VerrificationUi.scanText();

		introduced = LocalDate.parse(getInputWithTest("Date de mise en servie ? (AAAA-MM-JJ)\n",REGEX_DATEFORMAT));
		
		discontinued = LocalDate.parse(getInputWithTest("Date de fin de servie ? (AAAA-MM-JJ)\n",REGEX_DATEFORMAT));

		idCompany = Integer.parseInt(getInputWithTest("Id de l'entreprise ?\n",REGEX_ANYNUMBER));

		Computer computerNew = new Computer.Builder()
										   .setName(name)
										   .setIntroduced(introduced)
										   .setDiscontinued(discontinued)
										   .setCompany(new Company.Builder()
												   				  .setId(idCompany)
												   				  .build())
										   .build();

		ServicesComputer.computerAdd(computerNew);
	}

	public static void menuUpdateComputer() {

		boolean fini = false;
		boolean execute = true;

		Computer computerUpdate = ServicesComputer.computerSelectForUpdate(Integer.parseInt(getInputWithTest("Quel est l'id de l'ordinateur que vous voulez modifier ?\n",REGEX_ANYNUMBER)));

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

			int choixMenuModification = VerrificationUi.scanNumbers(1, 6);

			EnumMenuModifSwitch menuModifSwitch = EnumMenuModifSwitch.values()[choixMenuModification];

			switch (menuModifSwitch) {
			case MODIF_NAME_ORDI: // 1
				System.out.println("Nom de l'ordinateur ?\n");
				computerUpdate.setName(VerrificationUi.scanText());
				break;
			case MODIF_INTRODUCED_ORDI: // 2
				computerUpdate.setIntroduced(LocalDate.parse(getInputWithTest("Date de mise en servie ? (AAAA-MM-JJ)\n",REGEX_DATEFORMAT)));
				break;
			case MODIF_DISCONTINUED_ORDI: // 3
				computerUpdate.setDiscontinued(LocalDate.parse(getInputWithTest("Date de fin de servie ? (AAAA-MM-JJ)\n",REGEX_DATEFORMAT)));
				break;
			case MODIF_ID__ORDI_ENTREPRISE: // 4
				computerUpdate.setCompany(new Company.Builder()
		   				  							 .setId(Integer.parseInt(getInputWithTest("Id de l'entreprise ?\n",REGEX_ANYNUMBER)))
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
		ServicesComputer.computerRemove(Integer.parseInt(getInputWithTest("Id de l'ordinateur ?\n",REGEX_ANYNUMBER)));
	}
	
	public static String getInputWithTest(String message, String regex) {
		
		String rawValue;
		boolean ismatch = false;
		
		do {		
			System.out.println(message);	
			rawValue = VerrificationUi.scanText();
			ismatch = rawValue.matches(regex);
		} while (!ismatch);
		
		return rawValue;
	}
}
