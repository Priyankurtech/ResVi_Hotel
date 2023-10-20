package org.jsp.exam.controller;

import org.jsp.exam.dto.Student;
import org.jsp.exam.dto.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
@Controller
@RequestMapping("/teacher")
public class TeacherController {
	@Autowired
	Teacher teacher;

	@GetMapping("/login")
	public String loadLogin() {
		return "teacherlogin";
	}

	@GetMapping("/signup")
	public String loadSignup(ModelMap map) {
		map.put("teacher", teacher);
		return "teachersignup";
	}

	@PostMapping("/signup")
	public String signup(@Valid Teacher teacher, BindingResult result,@RequestParam MultipartFile pic, ModelMap map) {
		if (result.hasErrors()) {
			return "teachersignup";
		} else {
			return "teacherlogin";
		}
	}
}
