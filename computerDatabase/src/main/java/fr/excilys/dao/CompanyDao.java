package fr.excilys.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.excilys.mapper.MapperCompany;
import fr.excilys.model.Company;

@Repository
public class CompanyDao {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public CompanyDao(DataSource dataSource) {
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Transactional
	public int[] remove(int idCompany) {

		int[] returnValue = { 0, 0 };

		MapSqlParameterSource mapParam = new MapSqlParameterSource().addValue("idCompany", idCompany);

		returnValue[0] = namedParameterJdbcTemplate.update(EnumSQLRequestCompany.DELETE_COMPANYCOMPUTER.getMessage(),
				mapParam);
		returnValue[1] = namedParameterJdbcTemplate.update(EnumSQLRequestCompany.DELETE_COMPANY.getMessage(), mapParam);

		return returnValue;
	}

	public List<Company> lister() {

		return namedParameterJdbcTemplate.query(EnumSQLRequestCompany.SELECT_ALLCOMPANY.getMessage(),
				new MapperCompany());
	}

	public Company selecOneCompany(long idCompany) {

		MapSqlParameterSource mapParam = new MapSqlParameterSource().addValue("idCompany", idCompany);

		return namedParameterJdbcTemplate.queryForObject(EnumSQLRequestCompany.SELECT_ONECOMPANY.getMessage(), mapParam,
				new MapperCompany());
	}
}
