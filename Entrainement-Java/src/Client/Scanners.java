package Client;

import java.util.Scanner;

public class Scanners {

	protected static int ScanNumbers(int min, int max) {

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

	protected static int ScanNumber() {

		Scanner monScanner = new Scanner(System.in);
		int choix = 0;

		try {

			choix = Integer.parseInt(monScanner.nextLine());

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return choix;
	}

	protected static String ScanText() {

		Scanner monScanner = new Scanner(System.in);
		return monScanner.nextLine();
	}
}
