package org.jsp.resvi.service;

import java.util.Random;


import org.jsp.resvi.dao.HotelDao;
import org.jsp.resvi.dto.Hotel;
import org.jsp.resvi.helper.AES;
import org.jsp.resvi.helper.LoginHelper;
import org.jsp.resvi.helper.SendMailLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;




@Service
public class HotelService {
	 @Autowired
	    HotelDao hotelDao;

	    @Autowired
	    SendMailLogic mailLogic;

	    public String register(Hotel hotel, ModelMap map) {
	        Hotel hotel1 = hotelDao.fetchByEmail(hotel.getEmail());
	        Hotel hotel2 = hotelDao.fetchByPhone(hotel.getPhone());
	        if (hotel1 == null && hotel2 == null) {
	            int otp = new Random().nextInt(1000, 9999);
	            hotel.setOtp(otp);
	            mailLogic.send(hotel);
	            hotel.setPassword(AES.encrypt(hotel.getPassword(), "123"));
	            hotelDao.save(hotel);
	            map.put("id", hotel.getId());
	            return "HotelVerifyOtp";

	        } else {
	            map.put("neg", "emailandphoneshouldnotberepeated");
	            return "HotelRegister";
	        }

	    }

	    public String verify(int id, int otp, ModelMap map) {
	        Hotel hotel = hotelDao.fetchById(id);
	        if (hotel.getOtp() == otp) {
	            hotel.setStatus(true);
	            hotelDao.save(hotel);
	            map.put("pos", "Otp verified successfully");
	            return "HotelHome";
	        } else {
	            map.put("neg", "otp mismatch");
	            map.put("id", hotel.getId());
	            return "HotelVerifyOtp";
	        }

	    }

	    public String login(LoginHelper helper, ModelMap map) {
	        Hotel hotel = hotelDao.fetchByEmail(helper.getEmail());
	        if (hotel == null) {
	            map.put("neg", "invalid email");
	            return "hotel";
	        } else {
	            System.out.println(AES.decrypt(hotel.getPassword(), "123"));
	            System.out.println(helper.getPassword());
	            if (AES.decrypt(hotel.getPassword(), "123").equals(helper.getPassword())) {

	                map.put("pos", "Loggedin successfully");
	                return "hotelhome";
	            } else {
	                map.put("neg", "wrong password");
	                return "hotel";
	            }
	        }
	    }
}
