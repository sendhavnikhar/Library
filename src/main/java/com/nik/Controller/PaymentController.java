package com.nik.Controller;


import java.awt.Color;
import java.awt.PageAttributes.MediaType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Header;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.nik.Service.BookService;
import com.nik.Service.Paymentservice;
import com.nik.User.Book;
import com.nik.User.Payment;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class PaymentController {

	@Autowired
	private Paymentservice paymentservice;

	@Autowired
	private BookService bookService;

	@RequestMapping("/payment")
	public String dopayment(@RequestParam("bookname") String bookname,
			@RequestParam("IssueDate") String IssueDate, @RequestParam("ExpiryDate") String ExpiryDate,
			@RequestParam("BuyerName") String BuyerName,
			Model model) {

		
		Payment payment = new Payment(bookname.trim(), ExpiryDate.trim(), IssueDate.trim(), bookname.trim());

		this.paymentservice.savepayment(payment);

		List<Payment> payments = this.paymentservice.showpayment();

		model.addAttribute("payments", payments);

		return "vieworder";

	}
	
	
	@RequestMapping("/Cpayment")
	public String issuebook(@RequestParam("bookname") String bookname, @RequestParam("issueDate") String issueDate , @RequestParam("expiryDate") String expiryDate , @RequestParam("buyername") String buyername ,Model model) {
		
		
		Payment payment2 = this.paymentservice.getPaymentbybname(buyername);
		
		
		if(payment2 !=null) {
		
			
			model.addAttribute("error", "Same Name is Already Exist !! Try Different One");
		
		
			return "Cbuybook";
		}
		

		
		
		Payment payment = new Payment(bookname,buyername,issueDate,expiryDate);
		
		
		this.paymentservice.savepayment(payment);
		
		
		Payment payment3 = this.paymentservice.getPaymentbybname(buyername);
		
		System.out.println(payment2);
		model.addAttribute("payment",payment3);
		
		return "Cvieworder";
		
		
	}
	

	
	@GetMapping("/generatePDF")
	public void generatePDF(@RequestParam("payId") int payid, HttpServletResponse response) throws IOException {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + payid + "_payment_details.pdf\"");

		try {

			System.out.println(payid);

			Payment payment = this.paymentservice.getPaymentbyId(payid);

			System.out.println(payment);

			Document document = new Document();
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();

			Paragraph paragraph = new Paragraph();
			paragraph.add("Thanks For Using Your Librery Management Website ");
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setSpacingAfter(10);
			document.add(paragraph);

			PdfPTable table = new PdfPTable(2);

			table.addCell("BookName");
			table.addCell(payment.getBookname());
			table.addCell("BuyerName");
			table.addCell(payment.getBuyername());

			table.addCell("IssueDate");
			table.addCell(payment.getIssuedate());

			table.addCell("ExpiryDate");
			table.addCell(payment.getExpirydate());

			

			document.add(table);

			paragraph = new Paragraph();
			paragraph.add("Thanks For Buying Your Great Book");
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setSpacingAfter(20);
			document.add(paragraph);

			document.close();
		} catch (Exception e) {
			e.printStackTrace();
			// Handle exception appropriately
		}
	}

	@GetMapping("/vieworder")
	public String getAllBooks(Model model) {

		List<Payment> payments = this.paymentservice.showpayment();

		model.addAttribute("payments", payments);
		return "vieworder";
	}
	

	@GetMapping("/Cvieworder")
	public String CgetAllBooks(Model model) {

		List<Payment> payments = this.paymentservice.showpayment();

		model.addAttribute("payments", payments);
		return "Cvieworder";
	}

	
	
	
}
