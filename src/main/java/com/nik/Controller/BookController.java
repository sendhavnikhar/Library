package com.nik.Controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
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
	public String getAllBooks(Model model) {

		List<Book> books = this.bookService.getAllBooks();

		model.addAttribute("books", books);
		return "viewBook";
	}

	@GetMapping("/CviewBook")
	public String getAllBookss(Model model) {

		List<Book> books = this.bookService.getAllBooks();

		model.addAttribute("books", books);
		return "CviewBook";
	}

	
	
	@RequestMapping("/Cbuybook")
	public String buybook(@RequestParam("bookname") String bookname, Model model) {

		
		
		
		model.addAttribute("bookname", bookname);

		return "Cbuybook";

	}
	
	


	@RequestMapping("/returnbook")
	public String returnbook() {
		
		
		return "returnbook";
	}
	

	@GetMapping("/search")
	public String searchBooks(@RequestParam(name = "title", required = false) String title, Model model) {
		if (title == null || title.isEmpty()) {
			model.addAttribute("books", null);
		} else {
			Book books = bookService.getbookbyname(title);
			model.addAttribute("books", books != null ? Collections.singletonList(books) : null);
		}
		return "searchbook";
	}

	@GetMapping("/Csearch")
	public String CsearchBooks(@RequestParam(name = "title", required = false) String title, Model model) {
		if (title == null || title.isEmpty()) {
			model.addAttribute("books", null);
		} else {
			Book books = bookService.getbookbyname(title);
			model.addAttribute("books", books != null ? Collections.singletonList(books) : null);
		}
		return "Csearchbook";
	}

	 @RequestMapping("/bookdelete")
	    public String deleteBook(@RequestParam("bookId") Integer bookId) {
	        bookService.deleteBook(bookId);
	        return "redirect:/viewBook"; // Redirect to the book list page after deletion
	    }
	 
	 
	 @RequestMapping("/bookshow")
	public String showBook (@RequestParam("bookId") Integer bookId, Model model ) {
		
     Book book	= this.bookService.findById(bookId);
		
     
    System.out.println(book);
		
		model.addAttribute("ubook",book);
		return "update";
     }
     
		
	// Handle the form submission to update a book
	    @PostMapping("/updatebook")
	    public String updateBook(@RequestParam("Bookid")int bookid,@RequestParam("Bookname")String bookname, @RequestParam("Bookprice")String bookprice,@RequestParam("BookDetail")String bookDetail ,  RedirectAttributes redirectAttributes) {
	    	
	    	
	    	Book updateBook = bookService.findById(bookid);
	    	
	    	updateBook.setBookname(bookname);
	    	updateBook.setBookprice(bookprice);
	    	updateBook.setBookDetail(bookDetail);
	    	
	    	System.out.println(updateBook);
	    	
	    	bookService.update(updateBook);
	    	
	        // Redirect to a page showing updated book details or a success message
	        redirectAttributes.addFlashAttribute("message", "Book updated successfully");
	        return "redirect:/viewBook"; // Assuming /books maps to a page listing all books
	    }
	
	
	
	 
	 
	 
	@RequestMapping("/bookadd")
	public String bookadd(@RequestParam("Bookname") String Bookname, @RequestParam("Bookprice") String Bookprice,
			@RequestParam("BookDetail") String BookDetail, Model model) {

		Book book = new Book(Bookname, Bookprice, BookDetail);

		Book excistingBook = bookService.checkedbook(Bookname);

		if (excistingBook != null) {

			model.addAttribute("add", "Book is Already Exists");
			return "addbook";
		}

		this.bookService.savebook(book);

		model.addAttribute("add", "Book Added SuccessFully");
		return "addbook";
	}
}
