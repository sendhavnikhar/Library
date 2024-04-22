package com.nik.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nik.User.Book;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {

	Book findByBookname(String bookname);

    public void deleteById(int Bookid) ;

	Optional<Book> findById(Integer Bookid);

	
	


}
