package com.meal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meal.dao.RoleDao;
import com.meal.model.Role;

@Service
public class RoleService {
	
	@Autowired
	RoleDao roleDao;
	

	public Role getRole(long id) {
		return roleDao.get(id);
	}
	
	public void saveRole(Role role) {
		roleDao.save(role);
	}
	
	public List<Role> getAllRoles() {
		return roleDao.getAll();
	}
	
	public void updateRole(Role role) {
		roleDao.update(role);
	}
	
	public void deleteRole(Role role) {
		roleDao.delete(role);
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	
}
