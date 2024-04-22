package com.nik.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Payment {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int payid;
	private String bookname;
	private String buyername;
	private String issuedate ;
	private String expirydate;
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(String bookname, String buyername, String issuedate, String expirydate) {
		super();
		this.bookname = bookname;
		this.buyername = buyername;
		this.issuedate = issuedate;
		this.expirydate = expirydate;
	}

	


	public Payment(int payid, String buyername, String issuedate, String expirydate) {
		super();
		this.payid = payid;
		this.buyername = buyername;
		this.issuedate = issuedate;
		this.expirydate = expirydate;
	}

	public int getPayid() {
		return payid;
	}

	public void setPayid(int payid) {
		this.payid = payid;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getBuyername() {
		return buyername;
	}

	public void setBuyername(String buyername) {
		this.buyername = buyername;
	}

	public String getIssuedate() {
		return issuedate;
	}

	public void setIssuedate(String issuedate) {
		this.issuedate = issuedate;
	}

	public String getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}

	@Override
	public String toString() {
		return "Payment [payid=" + payid + ", bookname=" + bookname + ", buyername=" + buyername + ", issuedate="
				+ issuedate + ", expirydate=" + expirydate + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
