package com.meal.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.meal.model.ChangePassword;

@Service
public class SecurityService {

	public boolean isEmailEqual(ChangePassword changePassword,Authentication authentication) {
		System.out.println("email="+changePassword);
		System.out.println("username="+authentication);
		return true;
	}
}
