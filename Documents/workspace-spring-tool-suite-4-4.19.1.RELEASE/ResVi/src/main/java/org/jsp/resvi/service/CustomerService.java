package org.jsp.resvi.service;

import java.util.Random;

import org.jsp.resvi.dao.CustomerDao;
import org.jsp.resvi.dto.CustomerDto;
import org.jsp.resvi.helper.AES;

import org.jsp.resvi.helper.CustomerMailSendingLogic;
import org.jsp.resvi.helper.LoginHelper;
import org.jsp.resvi.helper.SendMailLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;



@Service
@Component
public class CustomerService {
	
	 @Autowired
	    CustomerDao dao;

	    @Autowired
	    SendMailLogic logic;

	    public String signup(CustomerDto customer, ModelMap map) {
	        CustomerDto customer1 = dao.fetchByEmail(customer.getEmail());
	        CustomerDto customer2 = dao.fetchByMobile(customer.getMobile());
	        if (customer1 == null && customer2 == null) {
	            int otp = new Random().nextInt(100000, 999999);
	            customer.setOtp(otp);
	            logic.send(customer);
	            customer.setPassword(AES.encrypt(customer.getPassword(), "123"));
	            dao.save(customer);
	            map.put("id", customer.getId());
	            return "CustomerVerifyOtp";
	        } else {
	            map.put("neg", "Email and Phone Number Should not be repeated");
	            return "CustomerSignUp";
	        }
	    }

	    public String verifyOtp(int id, int otp, ModelMap map) {
	        CustomerDto customer = dao.fetchById(id);
	        if (customer.getOtp() == otp) {
	            customer.setStatus(true);
	            dao.save(customer);
	            map.put("pos", "Otp verify Successfully");
	            return "Customer";
	        } else {
	            map.put("neg", "Otp mismatch");
	            map.put("id", customer.getId());
	            return "CustomerVerifyOtp";
	        }
	    }

	    public String login(LoginHelper helper, ModelMap map) {
	        CustomerDto customer = dao.fetchByEmail(helper.getEmail());
	        if (customer == null) {
	            map.put("neg", "Invalid Email");
	            return "Customer";
	        } else if (AES.decrypt(customer.getPassword(), "123").equals(helper.getPassword())) {
	            map.put("pos", "Valid Email");
	            return "CustomerHome";
	        } else {
	            map.put("neg", "Invalid Password");
	            return "Customer";
	        }
	    }
}


