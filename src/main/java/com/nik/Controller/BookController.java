package com.nik.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nik.Service.BookService;
import com.nik.User.Book;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class BookController {

	
	
	@Autowired
	private BookService bookService;

	@RequestMapping("/addbook")
	public String addbook() {

		
		
		return "addbook";
	}
	
	

	@GetMapping("/viewBook")
	public String getAllBooks(Model model){
		
		List<Book> books = this.bookService.getAllBooks();
		
		model.addAttribute("books", books);
		return "viewBook";
	}
		
	
	@RequestMapping("/buybook")
	public String buybook( @RequestParam("bookname") String bookname,Model model) {
		
		
		model.addAttribute("bookname",bookname);
		
		return "buybook";
		
	}
	
	
	@RequestMapping("/bookadd")
	public String bookadd(@RequestParam("Bookname") String Bookname, @RequestParam("Bookprice") String Bookprice,
			@RequestParam("BookDetail") String BookDetail ,Model model) {

		Book book = new Book(Bookname, Bookprice, BookDetail);

		
		Book excistingBook = bookService.checkedbook(Bookname);
		
		
		if(excistingBook !=null) {
			
			  model.addAttribute("add","Book is Already Exists");
		        return "addbook";
		}
		
		
		
		
		this.bookService.savebook(book);

		model.addAttribute("add", "Book Added SuccessFully");
		return "addbook";
	}
}
