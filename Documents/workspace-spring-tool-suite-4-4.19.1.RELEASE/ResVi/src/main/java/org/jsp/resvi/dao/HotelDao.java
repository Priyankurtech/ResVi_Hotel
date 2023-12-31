package org.jsp.resvi.dao;

import org.jsp.resvi.dto.Hotel;
import org.jsp.resvi.repository.HotelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class HotelDao {
	
	@Autowired
    HotelRepository hotelRepository;

    public Hotel fetchByEmail(String email) {
        return hotelRepository.findByEmail(email);
    }

    public Hotel fetchByPhone(long phone) {
        return hotelRepository.findByPhone(phone);
    }

    public void save(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    public Hotel fetchById(int id) {
        return hotelRepository.findById(id).orElse(null);
    }
	

	
}