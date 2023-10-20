package org.jsp.exam.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.jsp.exam.dto.Student;
import org.jsp.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	Student student;
    @Autowired
    StudentService studentService;
	
	@GetMapping("/login")
	public String loadLogin() {
		return "studentlogin";
	}

	@GetMapping("/signup")
	public String loadSignup(ModelMap map) {
		map.put("student", student);
		return "studentsignup";
	}

	@PostMapping("/signup")
	public String signup(@Valid Student student, BindingResult result,@RequestParam MultipartFile pic, ModelMap map) throws IOException, MessagingException {
		if (result.hasErrors()) {
			return "studentsignup";
		} else {
			return studentService.signUp(student,pic,map);
		}
	}
	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam int id, @RequestParam int otp, ModelMap map) {
		return studentService.verifyOtp(id, otp, map);
	}

	@GetMapping("/resend-otp/{id}")
	public String resendOtp(@PathVariable int id, ModelMap map) throws UnsupportedEncodingException, MessagingException {
		return studentService.resendOtp(id, map);
	}
   
}
