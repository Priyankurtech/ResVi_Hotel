package org.jsp.resvi.helper;

import org.jsp.resvi.dto.CustomerDto;
import org.jsp.resvi.dto.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class SendMailLogic {
	@Autowired
    JavaMailSender mailSender;

    public void send(CustomerDto customer) {
        MimeMessage mess = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mess);
        try {
            helper.setTo(customer.getEmail());
            helper.setSubject("Verify Your Otp");
            helper.setText("Hello " + customer.getName() + "Otp is " + customer.getOtp(), true);
            mailSender.send(mess);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void send(Hotel hotel) {
        MimeMessage mess = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mess);
        try {
            helper.setTo(hotel.getEmail());
            helper.setSubject("Verify Your Otp");
            helper.setText("Hello " + hotel.getName() + "Otp is " + hotel.getOtp(), true);
            mailSender.send(mess);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
      
}
