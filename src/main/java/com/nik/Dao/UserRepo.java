package com.nik.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nik.User.User;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	
 	User findByUsername(String username);
}

