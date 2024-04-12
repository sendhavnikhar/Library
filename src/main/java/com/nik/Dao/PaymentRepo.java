package com.nik.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nik.User.Payment;
import java.util.List;


public interface PaymentRepo extends JpaRepository<Payment, Integer> {

	
	
	Payment findByPayname(String payname);
	
}
