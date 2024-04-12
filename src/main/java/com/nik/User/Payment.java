package com.nik.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;


@Entity
public class Payment {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int PayId;
	
	private String paycard;
	
	private String expiryDate;
	
	private String Cvv;
	
	
	@Column(unique = true)
	private String payname;
	
	
	

	


	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Payment(int payId, String paycard, String expiryDate, String cvv, String payname) {
		super();
		PayId = payId;
		this.paycard = paycard;
		this.expiryDate = expiryDate;
		Cvv = cvv;
		this.payname = payname;
	}


	public Payment(String paycard, String expiryDate, String cvv, String payname) {
		super();
		this.paycard = paycard;
		this.expiryDate = expiryDate;
		Cvv = cvv;
		this.payname = payname;
	}


	public int getPayId() {
		return PayId;
	}


	public void setPayId(int payId) {
		PayId = payId;
	}


	public String getPaycard() {
		return paycard;
	}


	public void setPaycard(String paycard) {
		this.paycard = paycard;
	}


	public String getExpiryDate() {
		return expiryDate;
	}


	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}


	public String getCvv() {
		return Cvv;
	}


	public void setCvv(String cvv) {
		Cvv = cvv;
	}


	public String getPayname() {
		return payname;
	}


	public void setPayname(String payname) {
		this.payname = payname;
	}


	@Override
	public String toString() {
		return "Payment [PayId=" + PayId + ", paycard=" + paycard + ", expiryDate=" + expiryDate + ", Cvv=" + Cvv
				+ ", payname=" + payname +"]";
	}


	
	
	
}
