package com.nik.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nik.User.Payment;
import java.util.List;



@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {

	Payment   findByBuyername(String buyername);


	Payment   findByPayid(int payid);
	
	Payment   findByBookname(String bookname);
}

