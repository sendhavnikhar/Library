package com.nik.Service;

import java.util.List;
import java.util.Optional;

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



	public Payment getPaymentbyName(String bookname) {
		

		Payment payment = this.paymentRepo.findByBookname(bookname);
		
		
		return payment;
		
		
	} 
	
	
	public Payment getPaymentbybname(String buyername) {
		

		Payment payment = this.paymentRepo.findByBuyername(buyername);
		
		
		return payment;
		
		
	} 
	
	
	
	public Payment getPaymentbyId(Integer payid) {
		
		
		
	Payment payment = 	this.paymentRepo.findByPayid(payid);
	
	
	
	return payment;
	}
	
	

}
