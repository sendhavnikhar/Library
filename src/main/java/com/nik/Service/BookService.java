package com.nik.Service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nik.Dao.BookRepo;
import com.nik.User.Book;
import com.nik.Util.BookCompare;

@Service
public class BookService {

	@Autowired
	private BookRepo bookRepo;

	public BookService(BookRepo bookRepo) {
		this.bookRepo = bookRepo;
	}

	public List<Book> getAllBooks() {

		List<Book> books = this.bookRepo.findAll();
		Collections.sort(books, new BookCompare.CompareByNameAsc());
		return books;

	}

	public void deleteBook(int Bookid) {
		bookRepo.deleteById(Bookid);
	}

	public void update(Book book) {

		bookRepo.save(book);
	}

	public Book getbookbyname(String bookname) {

		Book books = this.bookRepo.findByBookname(bookname);

		return books;

	}

	public void savebook(Book book) {

		this.bookRepo.save(book);
	}

	public Book checkedbook(String bookname) {

		Book book = bookRepo.findByBookname(bookname);

		return book;
	}

	public Book findById(Integer bookId) {
		return bookRepo.findById(bookId).orElse(null);
	}

}
