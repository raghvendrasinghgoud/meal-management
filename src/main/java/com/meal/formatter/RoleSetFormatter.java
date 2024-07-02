package com.meal.formatter;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.format.Formatter;

import com.meal.model.Role;
import com.meal.service.RoleService;

public class RoleSetFormatter implements Formatter<Set<Role>> {

	RoleService roleService;
	
	@Override
	public String print(Set<Role> object, Locale locale) {
		System.out.println("inside role list formatter");
		String roles="";
		for(Role role:object) {
			roles+=role.getName()+",";
		}
		return roles;
	}

	@Override
	public Set<Role> parse(String text, Locale locale) throws ParseException {
		System.out.println("roles string "+text+"   "+roleService);
		String[] roles=text.split(",");
		Set<Role> roleList=new HashSet<>();
		
		for(String role:roles) {
			System.out.println("roles string "+role);
			roleList.add(roleService.getRole(Long.parseLong(role)));
		}
		return roleList;
	}

	public RoleSetFormatter(RoleService roleService) {
		super();
		this.roleService = roleService;
	}


	

}
