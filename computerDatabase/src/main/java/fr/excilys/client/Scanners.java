package fr.excilys.client;

import java.io.IOException;
import java.util.Scanner;

public class Scanners {

	protected static int scanNumbers(int min, int max) {

		Scanner monScanner = new Scanner(System.in);
		int choix = 0;

		try {

			choix = Integer.parseInt(monScanner.nextLine());

		} catch (NumberFormatException e) {
			System.out.println("Entrez un nombre valide \n");
		}

		if (choix >= min && choix <= max) {
			return choix;
		} else {
			return 0;
		}
	}

	protected static int scanNumber() {

		Scanner monScanner = new Scanner(System.in);
		int choix = 0;

		try {

			choix = Integer.parseInt(monScanner.nextLine());

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return choix;
	}

	protected static String scanText() {

		Scanner monScanner = new Scanner(System.in);
		return monScanner.nextLine();
	}

	protected static void scanAnyInput() {

		try {
			System.in.read();
		} catch (IOException e) {
			System.out.println("Impossible de lire l'entrée");
		}
	}
}
