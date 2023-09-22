package org.jsp.resvi.repository;

import org.jsp.resvi.dto.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;




public interface CustomerRespositary extends JpaRepository<CustomerDto, Integer> {

    CustomerDto findByEmail(String email);

    CustomerDto findByMobile(long mobile);

}

