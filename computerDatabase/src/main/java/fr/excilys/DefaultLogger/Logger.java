package fr.excilys.DefaultLogger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class Logger {
	
	private final static String LOG_FILE = "log.log";

	public static void writeLog(String titre, String message) {

		try {
			FileWriter fileWriter = new FileWriter(LOG_FILE, true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.print(java.time.LocalDate.now() + "\n");
			printWriter.print(titre + "\n");
			printWriter.print(message + "\n\n");
			printWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writeLogException(String titre, SQLException exception) {

		try {
			FileWriter fileWriter = new FileWriter(LOG_FILE, true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.print(java.time.LocalDate.now() + "\n");
			printWriter.print(titre + "\n");
			printWriter.print(exception + "\n\n");
			printWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
