package com.naveen.abac.security.spring;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.naveen.abac.security.policy.PolicyEnforcement;

@Component
public class AbacPermissionEvaluator implements PermissionEvaluator {
	private static Logger logger = LoggerFactory.getLogger(AbacPermissionEvaluator.class);
	
	@Autowired
	PolicyEnforcement policy;


	@Override
	public boolean hasPermission(Authentication authentication , Object targetDomainObject, Object permission) {
		//Object user = authentication.getPrincipal();
		//Object roles=authentication.getAuthorities();
		Map<String, Object> environment = new HashMap<>();
		UserContext userContext=new UserContext();
		userContext.setPrincipal(authentication.getPrincipal().toString());

		userContext.setRoles(authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
		/*
		Object authDetails = authentication.getDetails();
		if(authDetails != null) {
			if(authDetails instanceof WebAuthenticationDetails) {
				environment.put("remoteAddress", ((WebAuthenticationDetails) authDetails).getRemoteAddress());
			}
		}
		*/
		environment.put("time", new Date());
		
		logger.debug("hasPersmission({}, {}, {})", userContext, targetDomainObject, permission);
		return policy.check(userContext, targetDomainObject, permission, environment);
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
		return false;
	}

}
