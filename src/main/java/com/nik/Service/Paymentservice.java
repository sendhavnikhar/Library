package com.nik.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.nik.Dao.PaymentRepo;
import com.nik.User.Book;
import com.nik.User.Payment;

@Service
public class Paymentservice {

	@Autowired
	private PaymentRepo  paymentRepo;
	public void savepayment(Payment payment) {
		
		
		this.paymentRepo.save(payment);
	}
	
	
	
	public  List <Payment> showpayment() {

		
		 List<Payment>payment=   this.paymentRepo.findAll();
		    
		   
		 
		return payment;
	}



	public Payment getPaymentbyName(String payname) {
		

		Payment payment = this.paymentRepo.findByPayname(payname);
		
		
		return payment;
		
		
	}
}
