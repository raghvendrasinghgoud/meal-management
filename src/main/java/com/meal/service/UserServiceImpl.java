package com.meal.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.meal.dao.UserDao;
import com.meal.exception.NullProfileImageException;
import com.meal.model.ChangePassword;
import com.meal.model.Image;
import com.meal.model.User;

@Service
@Qualifier("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	FileHandler fileHandler;
	@Autowired
	UserDao userDao;
	@Autowired
	private ServletContext context;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public void changeUserPassword(ChangePassword changePassword) {
		User user=changePassword.getUser();
		System.out.println("user in change pass "+user);
		//encoding password
		user.setPassword(passwordEncoder.encode(changePassword.getPassword()));
		
		this.updateUser(user);
	}
	
	@Override
	public void saveUser(User user) throws IOException, NullProfileImageException {
	
			MultipartFile file=user.getImageFile();
			InputStream is=file.getInputStream();
			Image profilePic=createProfilePic(user);
			
			//preparing user for saving
			user.setProfilePic(profilePic);
			user.getContact().setUser(user);
			user.getCurrentAddress().setUser(user);
			user.getPermanentAddress().setUser(user);
			user.getIdProof().setUser(user);
			user.getMealAccount().setUser(user);
			
			//setting user default password
			user.setPassword(passwordEncoder.encode("raghav"));
			
			//saving user
			userDao.save(user);
			//saving user profile picture
			fileHandler.saveOrUpdateFile(is, FileHandler.USER_IMAGE_PATH, profilePic.getName());
		
		
	}
	
	@Override
	public Image createProfilePic(User user) throws NullProfileImageException {
		MultipartFile file=user.getImageFile();
		Image image=user.getProfilePic();
		
			if(file==null || file.getSize()<=0) {
				throw new NullProfileImageException("profile image not found");
			}
			
			image.setName(generateImageFileName(user));
			image.setSize(file.getSize());	
			
			return image;
	}
	
	
	
	
	private String generateImageFileName(User user) {
		MultipartFile file=user.getImageFile();
		long id=(user.getId()==0)?userDao.getNextID():user.getId();
		String imageFileName=user.getFirstName()+id+"."+file.getContentType().split("/")[1];
		System.out.println(imageFileName);
		return imageFileName;
	}

	@Override
	public List<User> getAllUsers(){
		
		return userDao.getAll();
	}
	
	@Override
	public User getUser(long id) {
		
		
		return userDao.get(id);
	}
	
	
	@Override
	public void updateUser(User user) {
		try {
			
			System.out.println("inside update "+user);
			
			//saving user
			userDao.update(user);
			
			MultipartFile file=user.getImageFile();
			
			if(file!=null && file.getSize()>0) {
				InputStream is=file.getInputStream();
				Image profilePic=createProfilePic(user);
				user.setProfilePic(profilePic);
				fileHandler.saveOrUpdateFile(is, FileHandler.USER_IMAGE_PATH, profilePic.getName());
				//saving user profile picture
			}
			
		
		}catch(IOException e) {
			
			System.out.println("Image file ");
			
		}catch(NullProfileImageException e) {
			
			//saving user
			userDao.update(user);
			System.out.println("profile image empty");
			
		}
	}
	
	
	@Override
	public void deleteUser(long id) {
		User user=getUser(id);
		userDao.delete(user);
		
		//deleting profile image
		// Read image file from the folder
        new File(context.getRealPath("/")+"/user_image/" + user.getProfilePic().getName()).delete();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return userDao.findUserByEmail(username);
	}
}
