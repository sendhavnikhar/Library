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
	public String dopayment(@RequestParam("CardNumber")String CardNumber,@RequestParam("ExpiryDate") String ExpiryDate,
			@RequestParam("CVV") String CVV ,@RequestParam("CardOwnerName") String CardOwnerName,Model model) {

		Payment payment = new Payment(CardNumber.trim(),ExpiryDate.trim(),CVV.trim(),CardOwnerName.trim());
		
		
		
		
		
		this.paymentservice.savepayment(payment);
	
      
		
		
		List<Payment> payments = this.paymentservice.showpayment();
		
		model.addAttribute("payments",payments);
		
		
        return "vieworder";
		
	}
	
	

   
	 @GetMapping("/generatePDF")
	    public void generatePDF(@RequestParam("payname")String payname,HttpServletResponse response) throws IOException {
	        response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + payname + "_payment_details.pdf\"");

	        try {
	            
	        
	        	  System.out.println(payname);
	        	
	        	Payment payment = this.paymentservice.getPaymentbyName(payname);
	        	
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
	            table.addCell("payName");
	            table.addCell(payment.getPayname());
	            table.addCell("CardNumber");
	            table.addCell(payment.getExpiryDate());
	            table.addCell("CardExpiryDate");
	            table.addCell(payment.getPaycard());
	            table.addCell("Card Cvv");
	            table.addCell(payment.getCvv());
	            
	            
	            
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
	public String getAllBooks(Model model){
		
		List<Payment> payments = this.paymentservice.showpayment();
		
		model.addAttribute("payments",payments);
		return "vieworder";
	}
	
	
	}

