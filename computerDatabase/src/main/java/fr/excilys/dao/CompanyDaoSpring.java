package fr.excilys.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class CompanyDaoSpring {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {

	    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

	}

	public void insertForum(String forum) {

	String query = "INSERT INTO FORUMS (FORUM_ID, FORUM_NAME, FORUM_DESC) VALUES (:forumId,:forumName,:forumDesc)";

	Map namedParameters = new HashMap();

	namedParameters.put("forumId", Integer.valueOf(forum.getForumId()));

	namedParameters.put("forumName", forum.getForumName());

	namedParameters.put("forumDesc", forum.getForumDesc());

	namedParameterJdbcTemplate.update(query, namedParameters);
	

	}
	
}
