package com.meal.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.meal.exception.NullProfileImageException;
import com.meal.formatter.RoleSetFormatter;
import com.meal.model.Address;
import com.meal.model.ChangePassword;
import com.meal.model.Contact;
import com.meal.model.IDProof;
import com.meal.model.Image;
import com.meal.model.MealAccount;
import com.meal.model.User;
import com.meal.service.RoleService;
import com.meal.service.SecurityService;
import com.meal.service.UserServiceImpl;
import com.meal.validation.PasswordValidation;

@Controller
@RequestMapping("/user")
public class UserControllers {

	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	RoleService roleService;

	@Autowired
	private ServletContext context;
	
	@Autowired
	private SecurityService securityService;

	@RequestMapping("/changePass")
	@PreAuthorize("#changePassword.user.contact.email==authentication.principal.username or hasAuthority('admin')")
	public String changePassword(@Validated @ModelAttribute("changePassword") ChangePassword changePassword,
			BindingResult br, Model model, RedirectAttributes redirectAttributes,Principal principal) {

		System.out.println("change pass " + changePassword);

		System.out.println("change pass " + br.getFieldErrors());

		if (br.hasErrors()) {
			// System.out.println(br.getAllErrors());
			redirectAttributes.addFlashAttribute("passbr", br);
			redirectAttributes.addFlashAttribute(changePassword);

		} else {
			System.out.println("changing password"+changePassword);
			User user=(User)userServiceImpl.loadUserByUsername(changePassword.getUser().getUsername());
			changePassword.setUser(user);
			userServiceImpl.changeUserPassword(changePassword);
		}

		return "redirect:/payment/get/" + changePassword.getUser().getUsername();
	}

	@RequestMapping("/image/{imageName}")
	@PreAuthorize("#imageName==authentication.principal.profilePic.name or hasAnyAuthority('admin','manager')")
	public void getImage(@PathVariable("imageName") String imageName, HttpServletResponse response) {
		try {
			// Read image file from the folder
			File imageFile = new File(context.getRealPath("/") + "/user_image/" + imageName);

			// Set response headers
			response.setContentType("image/jpeg");
			response.setContentLength((int) imageFile.length());

			// Write image content to response output stream
			FileInputStream inputStream = new FileInputStream(imageFile);
			IOUtils.copy(inputStream, response.getOutputStream());
			response.flushBuffer();
			inputStream.close();
		} catch (IOException e) {
			// Handle exceptions
		}
	}

	@RequestMapping("/users")
	public String userOverview(@ModelAttribute("user") User user,BindingResult br, Model model) {

//		System.out.println("save user "+user);
//		System.out.println("update e " + model.getAttribute("hasError"));
		
		//check if request come from saveController
		BindingResult saveBr=(BindingResult)model.getAttribute("saveUserBr");
		if(saveBr!=null) {
			br.addAllErrors(saveBr);
		}
		
		if (model.getAttribute("hasError") == null) {
			model.addAttribute("hasError", false);
		}

		if (model.getAttribute("update") != null && (boolean) model.getAttribute("update")) {
			model.addAttribute("update", false);
		} else {
			if ((boolean)model.getAttribute("hasError")==false) {
				user.setProfilePic(new Image());
				user.setContact(new Contact());
				user.setIdProof(new IDProof());
				user.setCurrentAddress(new Address());
				user.setPermanentAddress(new Address());
				user.setMealAccount(new MealAccount());
			}
		}

		model.addAttribute("roles", roleService.getAllRoles());
		model.addAttribute("users", userServiceImpl.getAllUsers());

		return "user-overview";

	}

	@RequestMapping("/save")
	public String saveOrUpdateUser(@Valid @ModelAttribute("user") User user, BindingResult br, Model model,RedirectAttributes attributes) {

		attributes.addFlashAttribute("hasError", br.hasErrors());
		
		//if request coming from update controller
		if (user.getId() != 0) {
			attributes.addFlashAttribute("update", true);
			
		}
		
		//removing imagefile error if request is for update
		if (user.getId() != 0) {
			BindingResult newBr=new BindException(user,"user");
			//br.rejectValue("imageFile", null);
			for(FieldError error:br.getFieldErrors()) {
				if(!error.getField().equals("imageFile")) {
					newBr.addError(error);
				}
			}
			br=newBr;
			if(!br.hasFieldErrors())
			attributes.addFlashAttribute("hasError", false);
			
		}
		
		if (br.hasErrors()) {
			System.out.println(br.getAllErrors());
//			model.addAttribute("roles", roleService.getAllRoles());
//			model.addAttribute("users", userServiceImpl.getAllUsers());
			//return "user-overview";
			
			
			
			attributes.addFlashAttribute(user);
			attributes.addFlashAttribute("saveUserBr",br);
		} else {
	
				if (user.getId() == 0) {
					System.out.println("saving user " + user);
					try {
						userServiceImpl.saveUser(user);
						
					} catch (IOException e) {
						
						e.printStackTrace();
					} catch (NullProfileImageException e) {
						br.rejectValue("imageFile","image.mandatory","image is mandatory" );
						
						attributes.addFlashAttribute("hasError", true);
						attributes.addFlashAttribute(user);
						attributes.addFlashAttribute("saveUserBr",br);
	
					}
				} else {
					attributes.addFlashAttribute("update", false);
					System.out.println("updating user " + user);
					
					//setting user password
					user.setPassword(userServiceImpl.getUser(user.getId()).getPassword());
					userServiceImpl.updateUser(user);
	
				}
			
		}


		return "redirect:/user/users";
	}

	@RequestMapping("/update/{id}")
	public String updateUser(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {

		User user = userServiceImpl.getUser(Long.parseLong(id));
		
		System.out.println("updating started " + user);

		// System.out.println(userFormObj);

		redirectAttributes.addFlashAttribute(user);

		// model.addAttribute("roles",roleService.getAllRoles());

		redirectAttributes.addFlashAttribute("update", true);

		// System.out.println("id= "+id+" ra "+model);

		return "redirect:/user/users";
	}

	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") String id) {

		userServiceImpl.deleteUser(Long.parseLong(id));

		return "redirect:/user/users";

	}

	@InitBinder("user")
	public void initBinder(WebDataBinder bind) {
		bind.setDisallowedFields("password");
//		bind.setBindEmptyMultipartFiles(false);
		bind.addCustomFormatter(new RoleSetFormatter(roleService));

	}

	@InitBinder("changePassword")
	public void initBinderForChangePassword(WebDataBinder bind) {
		bind.addValidators(new PasswordValidation());

	}

}
