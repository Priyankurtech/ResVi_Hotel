package org.jsp.exam.dao;

import org.jsp.exam.dto.Student;
import org.jsp.exam.helper.SendMailLogic;
import org.jsp.exam.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {
    @Autowired
    StudentRepository repository;
	public void save(Student student) {
       repository.save(student);
	}
	public Student findByEmail(String email)
	{
		return repository.findByEmail(email);
	}
	public Student findByMobile(long mobile)
	{
		return repository.findByMobile(mobile);
	}
	public Student findById(int id) {
		return repository.findById(id).orElse(null);
	}

	
}
