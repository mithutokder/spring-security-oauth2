package org.learn.resource.voter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.learn.resource.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

public class ActionRoleVoter implements AccessDecisionVoter<Object> {
	
	@Autowired
	private ActionService actionService;

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
		
		FilterInvocation invocation = (FilterInvocation) object;
	    String requestURI = invocation.getHttpRequest().getRequestURI();
	    String context = invocation.getHttpRequest().getContextPath();
	    requestURI = requestURI.replace(context, StringUtils.EMPTY);
	    System.out.println(requestURI);
	    
		List<String> ldapRoles = new ArrayList<String>();
		authentication.getAuthorities().forEach(i->{
			ldapRoles.add(i.getAuthority());
		});
		System.out.println(ldapRoles);
		Boolean hasAccess = actionService.hasAccess(requestURI, ldapRoles);
		if(!hasAccess) {
			System.out.println(String.format("User %s does not have access to  %s", authentication.getPrincipal().toString(), requestURI));
		}
		return hasAccess ? AccessDecisionVoter.ACCESS_GRANTED : AccessDecisionVoter.ACCESS_DENIED;
	}

}
