package fr.excilys.defaultLogger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
	
	private final static String LOG_FILE = "log/log.log";

	public static void writeLog(String titre, String message) {

		System.out.println(titre + " :\n" + message);
		
		try {
			FileWriter fileWriter = new FileWriter(LOG_FILE, true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.println(java.time.LocalDate.now());
			printWriter.println(titre);
			printWriter.println(message + "\n");
			printWriter.close();

		} catch (IOException e) {
			 javax.swing.JOptionPane.showMessageDialog(null,e.toString());
		}
	}
}
