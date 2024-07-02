package com.meal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.meal.model.Role;
import com.meal.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	RoleService roleService;

	@RequestMapping("/roles")
	public String roles(@Valid @ModelAttribute Role role,BindingResult br, Model model) {

		System.out.println("update e " + model.getAttribute("hasError"));
			
		BindingResult saveBr=(BindingResult)model.getAttribute("saveBr");
		
		//check if request coming from save controller
		if(saveBr!=null) {
			br.addAllErrors(saveBr);
		}
		
		if (model.getAttribute("hasError") == null) {
			model.addAttribute("hasError", false);
		}

		model.addAttribute("roles", roleService.getAllRoles());

		if (model.getAttribute("update") != null && (boolean) model.getAttribute("update")) {
			model.addAttribute("update", false);
		} else {
			if((boolean)model.getAttribute("hasError")==false)
					model.addAttribute("role", new Role());
		}

		return "role-overview";
	}

	@RequestMapping("/save")
	public String saveOrUpdateRole(@Valid @ModelAttribute("role") Role role, BindingResult br, Model model,
			RedirectAttributes redirectAttributes) {

		redirectAttributes.addFlashAttribute("hasError", br.hasErrors());
		if (br.hasErrors()) {

			System.out.println(br.getAllErrors());
			
			redirectAttributes.addFlashAttribute("saveBr",br);
			redirectAttributes.addFlashAttribute(role);
			
//			model.addAttribute("roles",roleService.getAllRoles());
//			return "role-overview";

		} else {

			if (role.getId() == 0)
				roleService.saveRole(role);

			else
				roleService.updateRole(role);
		}

		System.out.println(role);

		return "redirect:/role/roles";
	}

	@RequestMapping("/update/{id}")
	public String updateRole(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {

		Role role = roleService.getRole(Long.parseLong(id));

		redirectAttributes.addFlashAttribute("role", role);

		// model.addAttribute("roles",roleService.getAllRoles());

		redirectAttributes.addFlashAttribute("update", true);

		// System.out.println("id= "+id+" ra "+model);

		return "redirect:/role/roles";
	}

	@RequestMapping("/delete/{id}")
	public String deleteRole(@PathVariable("id") String id, Model model) {

		// System.out.println("id= "+id);

		Role role = roleService.getRole(Long.parseLong(id));

		roleService.deleteRole(role);

		return "redirect:/role/roles";
	}

}
