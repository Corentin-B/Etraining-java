package fr.excilys.DefaultLogger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {

	public static void writeLog(String titre, String message) {

		try {
			FileWriter fileWriter = new FileWriter("log.txt", true);
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
}
