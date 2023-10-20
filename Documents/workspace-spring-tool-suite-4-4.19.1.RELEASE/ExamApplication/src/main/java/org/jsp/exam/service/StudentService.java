package org.jsp.exam.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.apache.catalina.Server;
import org.jsp.exam.dao.StudentDao;
import org.jsp.exam.dto.Student;
import org.jsp.exam.helper.AES;
import org.jsp.exam.helper.SendMailLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@Service
public class StudentService {
	@Autowired
	SendMailLogic sendMailLogic;
	@Value("${Server.host}")
	String host;
	@Value("${Server.port}")
	int port;
	@Autowired
	StudentDao studentDao;

	public String signUp(Student student, MultipartFile pic, ModelMap map) throws IOException, MessagingException {
		Student student1 = studentDao.findByEmail(student.getEmail());
		Student student2 = studentDao.findByMobile(student.getMobile());
		// Checking if Email and Mobile Already Exists
		if (student1 == null && student2 == null) {
			//logic for encrypting password
			// Logic for converting multipartfile to byte[]
			byte[] picture = new byte[pic.getInputStream().available()];
			pic.getInputStream().read(picture);
			student.setPicture(picture);
			// Logic for generating otp
			student.setOtp(new Random().nextInt(100000, 999999));
			// Logic for Sending Mail
			student.setPassword(AES.encrypt(student.getPassword(), "123"));
			sendMailLogic.sendMail(student);
			// Logic for Saving Data
			studentDao.save(student);
			map.put("id", student.getId());
			return "VerifyOtp";
		} else {
			if (student1 == student2) {
				if (student1.isVerified()) {
					map.put("fail", "Account Already Exists with the above Email and Mobile");
					map.put("student", student1);
					return "studentsignup";
				} else {
					student1.setOtp(new Random().nextInt(100000, 999999));
				sendMailLogic.sendMail(student1);
					studentDao.save(student1);
					map.put("pass", "OTP Sent Again");
					map.put("id", student1.getId());
					return "VerifyOtp";
				}
			} else {
				if (student1 == null) {
					map.put("fail", "Account with Number Already Exists");
					map.put("student", student2);
					return "studentsignup";
				} else {
					map.put("fail", "Account with Email Already Exists");
					map.put("student", student1);
					return "studentsignup";
				}
			}
		}

	}
	public String verifyOtp(int id, int otp, ModelMap map) {
		Student student = studentDao.findById(id);
		if (student == null) {
			map.put("fail", "Something went wrong");
			return "index";
		} else {
			if (student.getOtp() == otp) {
				student.setVerified(true);
				studentDao.save(student);
				map.put("pass", "Otp verified Success Account Created");
				return "studentlogin";
			} else {
				map.put("fail", "OTP Missmatch, Try Again");
				map.put("id", student.getId());
				return "VerifyOtp";
			}
		}
	}

	public String resendOtp(int id, ModelMap map) throws UnsupportedEncodingException, MessagingException {
		Student student = studentDao.findById(id);
		if (student == null) {
			map.put("fail", "Something went wrong");
			return "index";
		} else {
			student.setOtp(new Random().nextInt(100000, 999999));
			sendMailLogic.sendMail(student);
			studentDao.save(student);
			map.put("id", student.getId());
			map.put("pass", "OTP Resent Success");
			return "VerifyOtp";
		}
	}
}
