package fr.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.excilys.model.Company;
import fr.excilys.model.Computer;

public class MapperComputer {
	
	private static volatile MapperComputer instance = null;
	
	private MapperComputer() {
		super();
	}
	
	public static MapperComputer getInstance() {

		if (MapperComputer.instance == null) {

			synchronized (MapperComputer.class) {
				if (MapperComputer.instance == null) {

					MapperComputer.instance = new MapperComputer();
				}
			}
		}
		return MapperComputer.instance;
	}
	
	public Computer getComputerFromResultSet(ResultSet resultComputer) throws SQLException {
		
		Computer computer = new Computer.Builder()
									   	.setId(resultComputer.getLong("id"))
									   	.setName(resultComputer.getString("name"))
									   	.setIntroduced(resultComputer.getDate("introduced"))
									   	.setDiscontinued(resultComputer.getDate("discontinued"))
									   	.setCompany(new Company.Builder()
									   						   .setId(resultComputer.getLong("id"))
									 			 			   .setName(resultComputer.getString("name"))
									 	 		 			   .build())
									    .build();
		return computer;
	}
}
