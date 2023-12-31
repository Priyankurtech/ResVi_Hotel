package org.jsp.resvi.controller;

import org.jsp.resvi.dto.CustomerDto;
import org.jsp.resvi.helper.LoginHelper;
import org.jsp.resvi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerDto customer;

    @Autowired
    CustomerService customerService;

    @GetMapping
    public String customer() {
        return "CustomerLogin";
    }

    @GetMapping("/signup")
    public String loadSign(ModelMap map) {
        map.put("customer", customer);
        return "CustomerSignUp";
    }

    @PostMapping("/signup")
    public String signup(@Valid CustomerDto customer, BindingResult result, ModelMap map) {
        if (result.hasErrors()) {
            return "CustomerSignup";
        } else {
            return customerService.signup(customer, map);
        }
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam int id, @RequestParam int otp, ModelMap map) {
        return customerService.verifyOtp(id, otp, map);
    }

    @PostMapping("login")
    public String login(LoginHelper helper, ModelMap map) {
        return customerService.login(helper, map);
    }
}