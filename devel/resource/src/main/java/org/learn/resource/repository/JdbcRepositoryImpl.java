package org.learn.resource.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcRepositoryImpl implements RepositoryBase {
	
	
	private NamedParameterJdbcTemplate npJdbcTemplate;
	
	@Autowired
	public JdbcRepositoryImpl(JdbcTemplate jdbcTemplate) {
		npJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	}

	@Override
	public <T> List<T> getQueryResult(String query, SqlParameterSource param, Class<T> clazz) {
		return npJdbcTemplate.queryForList(query, param, clazz);
	}

}
