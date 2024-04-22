package com.nik.Util;

import java.util.Comparator;

import com.nik.User.Book;

public class BookCompare {

	public static class CompareByNameAsc implements Comparator<Book> {
		@Override
		public int compare(Book o1, Book o2) {
			return o1.getBookname().compareTo(o2.getBookname());
		}
	}
	
	public static class CompareByNameDesc implements Comparator<Book> {
		@Override
		public int compare(Book o1, Book o2) {
			return o2.getBookname().compareTo(o1.getBookname());
		}
	}

}
