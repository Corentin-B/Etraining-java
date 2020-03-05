package fr.excilys.client;

import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;

class ScannersUi {

    static Logger logger = Logger.getLogger(ScannersUi.class);
	
	protected static int scanNumbers(int min, int max) {

		@SuppressWarnings("resource")
		Scanner monScanner = new Scanner(System.in);
		int choix = 0;

		try {

			choix = Integer.parseInt(monScanner.nextLine());

		} catch (NumberFormatException e) {
			System.out.println("Entrez un nombre valide \n");
	        logger.warn(e);
			return 0;
		}

		if (choix >= min && choix <= max) {
			return choix;
		} else {
			return 0;
		}
	}
	
	protected static String scanText() {

		@SuppressWarnings("resource")
		Scanner monScanner = new Scanner(System.in);
		return monScanner.nextLine().toString();
	}

	protected static void scanAnyInput() {

		try {
			System.in.read();
		} catch (IOException e) {
			System.out.println("Impossible de lire l'entrée \n");
	        logger.warn(e);
		}
	}
}
