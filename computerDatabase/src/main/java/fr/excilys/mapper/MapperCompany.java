package fr.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.excilys.model.Company;

public class MapperCompany {

	private static volatile MapperCompany instance = null;
	
	private MapperCompany() {
		super();
	}
	
	public static MapperCompany getInstance() {

		if (MapperCompany.instance == null) {

			synchronized (MapperCompany.class) {
				if (MapperCompany.instance == null) {

					MapperCompany.instance = new MapperCompany();
				}
			}
		}
		return MapperCompany.instance;
	}
	
	public Company getCompanyFromResultSet(ResultSet resultCompany) throws SQLException {
		
		Company company = new Company.Builder()
									 .setId(resultCompany.getLong("id"))
									 .setName(resultCompany.getString("name"))
									 .build();
		return company;
	}
}
