package com.nik.User;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Userid;
	
	
	@Column(unique = true)
	private String username;
	private String Password;
	private String CPassword;
	
	
	
	public User(int userid, String username, String password, String cPassword) {
		super();
		Userid = userid;
		this.username = username;
		Password = password;
		CPassword = cPassword;
	}


	public User(String username, String password, String cPassword) {
		super();
		this.username = username;
		Password = password;
		CPassword = cPassword;
	}
	
	
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getUserid() {
		return Userid;
	}


	public void setUserid(int userid) {
		Userid = userid;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		username = username;
	}


	public String getPassword() {
		return Password;
	}


	public void setPassword(String password) {
		Password = password;
	}


	public String getCPassword() {
		return CPassword;
	}


	public void setCPassword(String cPassword) {
		CPassword = cPassword;
	}


	@Override
	public String toString() {
		return "User [Userid=" + Userid + ", Username=" + username + ", Password=" + Password + ", CPassword="
				+ CPassword + "]";
	}
	
	
	
	
	
	
	
	
	
}
