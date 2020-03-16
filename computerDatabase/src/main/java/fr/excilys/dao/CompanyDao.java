package fr.excilys.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.excilys.mapper.MapperCompany;
import fr.excilys.model.Company;

@Repository
public class CompanyDao {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public CompanyDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Transactional
	public void remove(int id) throws SQLException {
		
		MapSqlParameterSource mapParam = new MapSqlParameterSource().addValue("companyId", id);

		jdbcTemplate.update(EnumSQLRequestCompany.DELETE_COMPANYCOMPUTER.getMessage(), mapParam);
		jdbcTemplate.update(EnumSQLRequestCompany.DELETE_COMPANY.getMessage(), mapParam);
	}

	public List<Company> lister() {

		return jdbcTemplate.query(EnumSQLRequestCompany.SELECT_ALLCOMPANY.getMessage(), new MapperCompany());
	}

	public Optional<Company> selecOneCompany(long idComputer) {

		return Optional.ofNullable(jdbcTemplate.queryForObject(EnumSQLRequestCompany.SELECT_ONECOMPANY.getMessage(), new MapperCompany()));
	}
}
