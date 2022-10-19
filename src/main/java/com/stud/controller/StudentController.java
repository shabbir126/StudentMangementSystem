package com.stud.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.stud.model.Role;
import com.stud.model.Student;
import com.stud.model.User;
import com.stud.repository.RoleRepository;
import com.stud.repository.UserRepository;
import com.stud.service.StudentService;

@Controller
public class StudentController {

	public static String uploadDir=System.getProperty("user.dir") + "/src/main/resources/static/studentImages";

	@Autowired
	private StudentService service;
	
	@GetMapping("/home")
	public String Home(Model model)
	{
		model.addAttribute("student", new Student());
		return "index";
	}
	
	
	@PostMapping("/register")
	public String PostStudent(@ModelAttribute("student") Student student,@RequestParam("studentImage")MultipartFile file,
			 @RequestParam("imgName")String imgName) throws IOException
	{
		String imageUUID;
		if (!file.isEmpty()) {
			imageUUID=file.getOriginalFilename();
			Path fileNameAndPath=Paths.get(uploadDir,imageUUID);
			Files.write(fileNameAndPath, file.getBytes());
		}else {
			imageUUID=imgName;
		}
		student.setImage(imageUUID);
		service.Save(student);
	  
		return "redirect:/view";
	}
	@GetMapping("/view")
	public String viewhome(Model model) {
		
		model.addAttribute("students", service.getAllStudents());
		 return "view";
	}
	@GetMapping("/delete/{id}")
	public String Remove(@PathVariable("id") int id)
	{
		service.removeById(id);
		return "redirect:/view";
	}
	
	@GetMapping("/update/{id}")
	public String ChangeStudent(@PathVariable("id") int id,Model model)
	{
		
		Student student=service.getStudentId(id).get();
		model.addAttribute("student",student);
		
		return "index";
	}
	
	
}
