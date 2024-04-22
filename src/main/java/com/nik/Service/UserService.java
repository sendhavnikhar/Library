package com.nik.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nik.Dao.UserRepo;
import com.nik.User.User;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public void SaveUser(User user) {

		this.userRepo.save(user);
	}

	public User checkedUser(String username, String password) {

		User user = userRepo.findByUsername(username);

		return user;
	}

	public boolean checkUser(String username, String password) {

		User user = userRepo.findByUsername(username);

		if (user != null && user.getPassword().equals(password)) {

			return true;
		}

		return false;
	}

}
