package org.learn.resource.service;

import java.util.List;

public interface ActionService {
	
	public List<String> getUrlPattern(List<String> roles);
	
	public boolean hasAccess(String requestURI, List<String> roles);

}
