package com.meal.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.meal.model.ChangePassword;
import com.meal.model.Payment;
import com.meal.model.User;
import com.meal.service.PaymentService;
import com.meal.service.UserServiceImpl;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	@Autowired
	UserServiceImpl userServiceImpl;
	
	
	@RequestMapping("/get/{email}")
	@PreAuthorize("hasAnyAuthority('admin', 'manager') or #email == authentication.principal.username")
	public String getUser(@PathVariable("email") String email,Payment payment,BindingResult paymentBr,ChangePassword changePassword,BindingResult br,Model model) {
	
		BindingResult passBr=(BindingResult)model.getAttribute("passbr");
		BindingResult payBr=(BindingResult)model.getAttribute("paybr");
		
		if(passBr!=null) {
			System.out.println("passbr="+passBr.getAllErrors());
			model.addAttribute("passError",true);
			
			br.addAllErrors(passBr);
		}
		
		if(payBr!=null) {
			System.out.println("paybr="+payBr.getAllErrors());
			model.addAttribute("hasError",true);
			
			paymentBr.addAllErrors(payBr);
		}
		
		if(model.getAttribute("passError")==null) {
			model.addAttribute("passError",false);
		}
		System.out.println("payment "+payment);
		
		User user=(User)userServiceImpl.loadUserByUsername(email);
		payment.setUser(user);
		
		//setting change password
		changePassword.setUser(user);
		
		
		System.out.println("update e "+model.getAttribute("hasError"));		
		
		if(model.getAttribute("hasError")==null) {
			model.addAttribute("hasError",false);
		}
	
	
	if(model.getAttribute("update")!=null && (boolean)model.getAttribute("update")) {
		model.addAttribute("update",false);
	}
	else {
		
		model.addAttribute("payment",payment);
	}
				
		
	List<Payment> payments=new ArrayList<>();
	payments.addAll(user.getPayments());
	
//	Comparator<Payment> comp=;
	
	payments.sort(new Comparator(){

		@Override
		public int compare(Object o1, Object o2) {
			Payment p1=(Payment)o1;
			Payment p2=(Payment)o2;
			
			if(p1.getId()>p2.getId())return 1;
			else if(p1.getId()>p2.getId()) return 2;
			else return -1;
		}
		
	});
	model.addAttribute("payments",payments);
	model.addAttribute("user", user);		
		
		
		return "view-user-info";
	}
	
	
	@RequestMapping("/save")
	public String savePayment(@Valid @ModelAttribute Payment payment, BindingResult br,ChangePassword changePassword, Model model,RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addFlashAttribute("hasError", br.hasErrors());
		
		User user=userServiceImpl.getUser(payment.getUser().getId());
		if(br.hasErrors()) {
			System.out.println(br.getAllErrors());
			model.addAttribute("user", user);	
			redirectAttributes.addFlashAttribute("paybr",br);
			
			//Payment payment=new Payment();
			payment.setUser(user);
		//	model.addAttribute("payment",payment);
		//	return "view-user-info";
			
		}else {
			payment.setUser(user);
			System.out.println("saving payent "+payment);
			System.out.println("payent user "+payment.getUser());
			
			if(payment.getId()==0) {
				System.out.println("saving payment "+payment);
				paymentService.savePayment(payment);
			}
			else {
				System.out.println("updating payment "+payment);
				paymentService.updatePayment(payment);
				
			}
		}
		
		
		return "redirect:/payment/get/"+payment.getUser().getUsername();
	}
	
	@RequestMapping("/update/{id}")
	public String updatePayment(@PathVariable("id") String id,Model model,RedirectAttributes redirectAttributes) {
		
		Payment payment=paymentService.getPayment(Long.parseLong(id));
		
		System.out.println("updating started "+payment);
		
		//System.out.println(userFormObj);
		
		redirectAttributes.addFlashAttribute("payment",payment);
		
		//model.addAttribute("roles",roleService.getAllRoles());
		
		redirectAttributes.addFlashAttribute("update",true);

		//System.out.println("id= "+id+" ra "+model);
		
		return "redirect:/payment/get/"+payment.getUser().getUsername();
		
	}
	
	@RequestMapping("/delete/{id}")
	public String deletePayment(@PathVariable("id") String id) {
		Payment payment=paymentService.getPayment(Long.parseLong(id));
		paymentService.deletePayment(payment);	
		return "redirect:/payment/get/"+payment.getUser().getUsername();
	}
	
}
