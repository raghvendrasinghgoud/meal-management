package com.meal.service;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.meal.exception.NullProfileImageException;
import com.meal.helper.UserFormObj;
import com.meal.model.ChangePassword;
import com.meal.model.Image;
import com.meal.model.User;

public interface UserService extends UserDetailsService {

	public void changeUserPassword(ChangePassword changePassword);
	public void saveUser(User user)  throws IOException, NullProfileImageException;
	public Image createProfilePic(User user) throws NullProfileImageException ;
	public List<User> getAllUsers();
	public User getUser(long id);
	public void updateUser(User user);
	public void deleteUser(long id);
	
}
