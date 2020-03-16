package fr.excilys.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDaoSpring {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {

	    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public int insertForum() {

	String query = "INSERT INTO company(name) "
	  		 	 + "VALUES (:companyId);";

	Map<String, String> namedParameters = new HashMap<>();

	namedParameters.put("companyId", "ttest");

	return namedParameterJdbcTemplate.update(query, namedParameters);
	}	
}
