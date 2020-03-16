package fr.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.excilys.model.Company;

public class MapperCompany implements RowMapper<Company> {

	public Company getCompanyFromResultSet(ResultSet resultCompany) throws SQLException {
		
		Company company = new Company.Builder()
									 .setId(resultCompany.getLong("id"))
									 .setName(resultCompany.getString("name"))
									 .build();
		return company;
	}

	@Override
	public Company mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Company company = new Company.Builder()
				 .setId(resultSet.getLong("id"))
				 .setName(resultSet.getString("name"))
				 .build();
		return company;
	}
}
