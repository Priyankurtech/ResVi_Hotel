package org.jsp.resvi.dao;

import org.jsp.resvi.dto.CustomerDto;
import org.jsp.resvi.repository.CustomerRespositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CustomerDao {

	
	@Autowired
    CustomerRespositary repository;

    public CustomerDto fetchByEmail(String email) {
        return repository.findByEmail(email);
    }

    public CustomerDto fetchByMobile(long mobile) {
        return repository.findByMobile(mobile);
    }

    public void save(CustomerDto customer) {
        repository.save(customer);
    }

    public CustomerDto fetchById(int id) {
        return repository.findById(id).orElse(null);
    }
}

