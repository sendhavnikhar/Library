package com.nik.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nik.User.Book;
import java.util.List;


@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {

	
	Book  findByBookname(String bookname);
	
}
