package org.learn.resource.repository;

import java.util.List;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public interface RepositoryBase {
	
	public <T> List<T> getQueryResult(String query, SqlParameterSource param, Class<T> clazz);

}
