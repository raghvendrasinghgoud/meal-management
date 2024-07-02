package com.meal.dao;

import java.util.List;

import com.meal.model.User;

public interface UserDao {
	
	public void save(User user);
	public User get(long id);
	public List<User> getAll();
	public void update(User user);
	public void delete(User user);
	public int getNextID();
	public User findUserByEmail(String email);
}
