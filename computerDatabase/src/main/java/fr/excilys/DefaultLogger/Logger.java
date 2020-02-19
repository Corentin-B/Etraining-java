package fr.excilys.DefaultLogger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class Logger {
	
	private final static String LOG_FILE = "log.log";
	private final static String LOGSQL_FILE = "logsql.log";


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
	
	public static void writeLogSQLException(String titre, SQLException sqlException) {

		System.out.println(titre + " :\n" + sqlException);
		
		try {
			FileWriter fileWriter = new FileWriter(LOGSQL_FILE, true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.println(java.time.LocalDate.now());
			printWriter.println(titre);
			printWriter.println(sqlException + "\n");
			printWriter.close();

		} catch (IOException e) {
			writeLog("WARN",e.toString());
		}
	}
}
