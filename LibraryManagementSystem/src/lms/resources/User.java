package lms.resources;

import java.util.List;

public class User {
	
	private String name;	
	private List<Book> book;
	private int bookCount;
	
	public User(String name, List<Book> book, int bookCount) {
		this.name = name;
		this.book = book;
		this.bookCount = bookCount;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getBookCount() {
		return bookCount;
	}
	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}


	public List<Book> getBook() {
		return book;
	}


	public void setBook(List<Book> book) {
		this.book = book;
	}
	
	
	
	
	

}
