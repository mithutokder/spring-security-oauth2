package org.learn.resource.service;

import java.util.List;

import org.learn.resource.repository.RepositoryBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

@Service
public class ActionServiceImpl implements ActionService {
	
	@Autowired
	private RepositoryBase repository;
	
	private static final String PATTERN_BY_ROLES = "select ac.action_url_pattern from actions ac, actions_role_participant ac_ptcp, " +
			" application_role role where ac.actions_pk=ac_ptcp.actions_pk and" +
			" ac_ptcp.app_role_pk = role.app_role_pk and" +
			" ac_ptcp.status='NORMAL' and" +
			" role.ldap_role = (:roles)";

	@Override
	public List<String> getUrlPattern(List<String> roles) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("roles", roles);
		return repository.getQueryResult(PATTERN_BY_ROLES, param, String.class);
	}

	@Override
	public boolean hasAccess(String requestURI, List<String> roles) {
		List<String> uriPatterns =  getUrlPattern(roles);
		System.out.println("URL : " + uriPatterns);
		return uriPatterns.stream()
				.filter(e -> requestURI.matches(e))
				.findAny()
				.map(s -> Boolean.TRUE)
				.orElseGet(() -> Boolean.FALSE);
	}

}
