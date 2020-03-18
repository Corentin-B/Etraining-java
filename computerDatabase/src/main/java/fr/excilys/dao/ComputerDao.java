package fr.excilys.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.excilys.mapper.MapperComputer;
import fr.excilys.mapper.MapperDateTimeMidNight;
import fr.excilys.mapper.PrepareQuerry;
import fr.excilys.model.Computer;

@Repository
public class ComputerDao {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public ComputerDao(DataSource dataSource) {
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public Computer selectComputerById(int idComputer) {

		MapSqlParameterSource mapParam = new MapSqlParameterSource()
				.addValue("idComputer", idComputer);
		
		return namedParameterJdbcTemplate.queryForObject(EnumSQLRequestComputer.SELECT_ONECOMPUTER.getMessage(), mapParam, new MapperComputer());
	}

	public List<Computer> searchComputerByName(String nameComputer, int numberPage, int range, String order, String sort) {

		MapSqlParameterSource mapParam = new MapSqlParameterSource()
				.addValue("nameComputer", "%" + nameComputer.toUpperCase() + "%")
				.addValue("numberPage", numberPage)
				.addValue("range", range);
		
		return namedParameterJdbcTemplate.query(PrepareQuerry.checkOrderValueSuffix(order, sort, EnumSQLRequestComputer.SELECT_SEARCHCOMPUTER.getMessage()), mapParam, new MapperComputer());
	}

	public List<Computer> list(int numberPage, int range) {

		MapSqlParameterSource mapParam = new MapSqlParameterSource()
				.addValue("numberPage", numberPage)
				.addValue("range",range);

		return namedParameterJdbcTemplate.query(EnumSQLRequestComputer.SELECT_ALLCOMPUTER.getMessage(), mapParam, new MapperComputer());
	}

	public List<Computer> listOrder(int numberPage, int range, String order, String sort) {

		MapSqlParameterSource mapParam = new MapSqlParameterSource()
				.addValue("numberPage", numberPage)
				.addValue("range", range);

		return namedParameterJdbcTemplate.query(PrepareQuerry.checkOrderValueSuffix(order, sort, EnumSQLRequestComputer.SELECT_ALLCOMPUTER_ORDER.getMessage()), mapParam, new MapperComputer());
	}
	
	public int numberPage() {

		MapSqlParameterSource mapParam = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.queryForObject(EnumSQLRequestComputer.SELECT_NUMBERCOMPUTER.getMessage(), mapParam, Integer.class);
	}

	public int numberSearch(String nameComputer) {

		MapSqlParameterSource mapParam = new MapSqlParameterSource()
				.addValue("nameComputer", "%" + nameComputer.toUpperCase() + "%");

		return namedParameterJdbcTemplate.queryForObject(EnumSQLRequestComputer.SELECT_NUMBERSEARCH.getMessage(), mapParam, Integer.class);
	}

	public int add(Computer computer) {
		
		MapSqlParameterSource mapParam = new MapSqlParameterSource()
				.addValue("nameComputer", computer.getName())
				.addValue("introducedComputer", MapperDateTimeMidNight.getDatetimeToTimestamp(computer.getIntroduced()))
				.addValue("discontinuedComputer", MapperDateTimeMidNight.getDatetimeToTimestamp(computer.getDiscontinued()))
				.addValue("idCompanyComputer", computer.getCompany().getId());
		
		return namedParameterJdbcTemplate.update(EnumSQLRequestComputer.INSERT_NEWCOMPUTER.getMessage(), mapParam);
	}

	public int remove(int idComputer) {

		MapSqlParameterSource mapParam = new MapSqlParameterSource()
				.addValue("idComputer", idComputer);

		return namedParameterJdbcTemplate.update(EnumSQLRequestComputer.DELETE_COMPUTER.getMessage(), mapParam);
	}

	public int update(Computer computer) {

		MapSqlParameterSource mapParam = new MapSqlParameterSource()
				.addValue("nameComputer", computer.getName())
				.addValue("introducedComputer", MapperDateTimeMidNight.getDatetimeToTimestamp(computer.getIntroduced()))
				.addValue("discontinuedComputer", MapperDateTimeMidNight.getDatetimeToTimestamp(computer.getDiscontinued()))
				.addValue("idCompanyComputer", computer.getCompany().getId())
				.addValue("idComputer", computer.getId());

		return namedParameterJdbcTemplate.update(EnumSQLRequestComputer.UPDATE_COMPUTER.getMessage(), mapParam);
	}
}
