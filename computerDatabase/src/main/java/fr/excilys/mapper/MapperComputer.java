package fr.excilys.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import fr.excilys.model.Company;
import fr.excilys.model.Computer;

public class MapperComputer {
	
	private static volatile MapperComputer instance = null;
	
	@Autowired
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
									   	.setId(resultComputer.getLong("computer.id"))
									   	.setName(resultComputer.getString("computer.name"))
									   	.setIntroduced(getTimestampToLocalDate(resultComputer.getDate("computer.introduced")))
									   	.setDiscontinued(getTimestampToLocalDate(resultComputer.getDate("computer.discontinued")))
									   	.setCompany(new Company.Builder()
									   						   .setId(resultComputer.getLong("company_id"))
									 			 			   .setName(resultComputer.getString("company.name"))
									 	 		 			   .build())
									    .build();
		return computer;
	}
	
	public Computer getComputerFromResponse(HttpServletRequest request) {
		
		System.out.println(request.getParameter("introduced"));
		Computer computer = new Computer.Builder()
										.setId(FormatServletRequest.checkIntFormatAndConvert(request.getParameter("computerId")))
									   	.setName(request.getParameter("computerName"))
									   	.setIntroduced(FormatServletRequest.checkDateFormatValueAndConvert(request.getParameter("introduced")))
									   	.setDiscontinued(FormatServletRequest.checkDateFormatValueAndConvert(request.getParameter("discontinued")))
									   	.setCompany(new Company.Builder()
									   						   .setId(FormatServletRequest.checkIntFormatAndConvert(request.getParameter("companyId")))
									 	 		 			   .build())
									    .build();
		
		computer.setIntroduced(FormatServletRequest.checkIntroducedDiscontinued(computer.getIntroduced(), computer.getDiscontinued()));
		
		return computer;
	}
	
	public Computer getComputerFromString(int computerId,String computerName, LocalDate computerIntroduced, LocalDate computerDiscontinued, int companyId) {
		
		Computer computer = new Computer.Builder()
										.setId(computerId)
									   	.setName(computerName)
									   	.setIntroduced(computerIntroduced)
									   	.setDiscontinued(computerDiscontinued)
									   	.setCompany(new Company.Builder()
									   						   .setId(companyId)
									 	 		 			   .build())
									    .build();
		return computer;
	}
	
	private LocalDate getTimestampToLocalDate(Date dateComputer) {
		
		if (dateComputer != null)
		{
			return dateComputer.toLocalDate();
		}
		else {
			return null;
		}
	}
}
