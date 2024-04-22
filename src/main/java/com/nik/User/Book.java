package com.nik.User;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book implements Comparable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Bookid;
	private String bookname;
	private String Bookprice;
	private String BookDetail;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(int bookid, String bookname, String bookprice, String bookDetail) {
		super();
		Bookid = bookid;
		this.bookname = bookname;
		Bookprice = bookprice;
		BookDetail = bookDetail;
	}

	public Book(String bookname, String bookprice, String bookDetail) {
		super();
		this.bookname = bookname;
		Bookprice = bookprice;
		BookDetail = bookDetail;
	}

	public int getBookid() {
		return Bookid;
	}

	public void setBookid(int bookid) {
		Bookid = bookid;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getBookprice() {
		return Bookprice;
	}

	public void setBookprice(String bookprice) {
		Bookprice = bookprice;
	}

	public String getBookDetail() {
		return BookDetail;
	}

	public void setBookDetail(String bookDetail) {
		BookDetail = bookDetail;
	}

	@Override
	public String toString() {
		return "Book [Bookid=" + Bookid + ", Bookname=" + bookname + ", Bookprice=" + Bookprice + ", BookDetail="
				+ BookDetail + "]";
	}

	@Override
	public int compareTo(Object o) {
		Book b = (Book) o;
		if (Integer.parseInt(this.Bookprice) > Integer.parseInt(b.Bookprice))
			return +1;
		else if (Integer.parseInt(this.Bookprice) < Integer.parseInt(b.Bookprice))
			return -1;
		else {
			return 0;
		}

	}

}
