package com.meal.dao;

import java.util.List;

import com.meal.model.Role;

public interface RoleDao {

	public void save(Role role);
	public Role get(long id);
	public List<Role> getAll();
	public void update(Role role);
	public void delete(Role role);
}
