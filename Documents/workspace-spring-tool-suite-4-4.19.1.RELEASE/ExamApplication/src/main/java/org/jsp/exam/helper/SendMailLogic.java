package org.jsp.exam.helper;

import java.io.UnsupportedEncodingException;

import org.jsp.exam.dao.StudentDao;
import org.jsp.exam.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class SendMailLogic {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    StudentDao dao;
	public void sendMail(Student student) throws UnsupportedEncodingException, MessagingException {
		MimeMessage mimeMessage=javaMailSender.createMimeMessage();		
		MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage);
		mimeMessageHelper.setFrom("priyankur.net@gmail.com","Exam App");
		mimeMessageHelper.setTo(student.getEmail());
		mimeMessageHelper.setSubject("Otp Verification");
		String gender="";
		if (student.getGender().equals("male") ){
			gender="Mr.";
		} else {
			gender="Mrs.";
		}
		String body="<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Exam App</title>\r\n"
				+ "    <style>\r\n"
				+ "        body {\r\n"
				+ "            font-family: Arial, sans-serif;\r\n"
				+ "            margin: 20px;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .container {\r\n"
				+ "            max-width: 400px;\r\n"
				+ "            margin: auto;\r\n"
				+ "            text-align: center;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .otp-message {\r\n"
				+ "            font-size: 18px;\r\n"
				+ "            margin-bottom: 20px;\r\n"
				+ "        }\r\n"
				+ "    </style>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "\r\n"
				+ "<div class=\"container\">\r\n"
				+ "    <h2>Exam App</h2>\r\n"
				+ "    <div class=\"otp-message\">\r\n"
				+ "        Hello "+gender+""+student.getName()+" <br>\r\n"
				+ "        Your OTP for creating an account is: <strong>"+student.getOtp()+"</strong><br>\r\n"
				+ "        Thank you, Priyankur<br>\r\n"
				+ "        Exam App.\r\n"
				+ "    </div>\r\n"
				+ "</div>\r\n"
				+ "\r\n"
				+ "</body>\r\n"
				+ "</html>\r\n"
				+ "";
		mimeMessageHelper.setText(body,true);
		javaMailSender.send(mimeMessage);
		dao.save(student);
	}

}
