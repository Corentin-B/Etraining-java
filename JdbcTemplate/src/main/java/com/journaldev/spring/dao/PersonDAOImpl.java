package com.journaldev.spring.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.journaldev.model.Person;
import com.journaldev.model.PersonMapper;

@Component
public class PersonDAOImpl implements PersonDAO {

	JdbcTemplate jdbcTemplate;

	private final String SQL_GET_ALL = "SELECT company.id, company.name FROM company";

	@Autowired
	public PersonDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Person> getAllPersons() {
		return jdbcTemplate.query(SQL_GET_ALL, new PersonMapper());
	}
}
