package com.nik.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nik.Dao.BookRepo;
import com.nik.User.Book;

@Service
public class BookService {

	@Autowired
	private BookRepo bookRepo;

	public BookService(BookRepo bookRepo) {
		this.bookRepo = bookRepo;
	}
	
	 public List<Book> getAllBooks() {
		 
	   List<Book>books=   this.bookRepo.findAll();
	    
	   return books;	
	 
	 }

	public void savebook(Book book) {

		this.bookRepo.save(book);
	}

	public Book checkedbook(String bookname) {

		Book book = bookRepo.findByBookname(bookname);

		return book;
	}

	
}
