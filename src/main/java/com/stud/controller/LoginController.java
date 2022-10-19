package com.stud.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.stud.model.Role;
import com.stud.model.User;
import com.stud.repository.RoleRepository;
import com.stud.repository.UserRepository;
import com.stud.service.UserService;

@Controller
public class LoginController {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String registerGet(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@PostMapping("/signup")
	public String RegisterSave(@ModelAttribute("user") User user,HttpServletRequest request)throws ServletException
	{
		String password =user.getPassword();
		user.setPassword(bCryptPasswordEncoder.encode(password));
		List<Role> roles=new ArrayList<>();
		roles.add(roleRepository.findById(2).get());
		user.setRoles(roles);
		userService.Save(user);
		request.login(user.getEmail(), password);
		return "redirect:/view";
	}
}
