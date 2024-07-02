package com.meal.config;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.meal.helper.Utility;
import com.meal.model.User;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		
		if (targetDomainObject instanceof Long && permission instanceof String) {
            Long userId = (Long) targetDomainObject;
            String role = (String) permission;
            return isOwnerOrAdmin(authentication, userId, role);
        }
		
		
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		return false;
	}

	private boolean isOwnerOrAdmin(Authentication authentication, Long userId, String role) {
        // Check if the user has the required role
        if (authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role))) {
            return true;
        }

        // Check if the authenticated user's ID matches the provided userId
        if (authentication.getPrincipal() instanceof User) {
        	User user = (User) authentication.getPrincipal();
            return user.getId()==userId;
        }

        return false;
    }
}
