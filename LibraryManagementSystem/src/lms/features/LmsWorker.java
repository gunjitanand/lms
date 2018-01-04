package lms.features;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import lms.resources.Book;
import lms.resources.Library;
import lms.resources.User;

public class LmsWorker {
	
	static int maxNofBooksUserIssued = 5;
	
	
	public static void main(String args[]) {		
		
		Library lib = new Library();		
		
		//add books to library
		Book book1 = new Book("Animal Farm", "Oscar Wilde", null, false);
		Book book2 = new Book("Hit Refresh", "Satya Nadella", null, false);
		
		addBooksToSystem(lib, book1, book2);
		
		//add users to system
		User user1 = new User("Neil Wagner", null, 0);
		User user2 = new User("Glen Anthony", null, 0);
		
		addUsersToSystem(lib, user1, user2);
		
		//issue a book to the user
		String bookToBeIssued = "Hit Refresh";
		String issuerName = "Glen Anthony"; 
		issueBook(lib, bookToBeIssued, issuerName);
		
		//return the book to library
		String bookToBeReturned = "Hit Refresh";
		returnBook(lib, bookToBeReturned);
		
		//search a book by name
		String bookToBeSearched = "Animal Farm";
		searchBook(lib, bookToBeSearched);
		
		//search a user by userName
		String userTobeSearched = "Glen Anthony";
		serachUser(lib, userTobeSearched);
		
	}

	private static User serachUser(Library lib, String userTobeSearched) {
		User userInLib = null;
		
		for(User user : lib.getUser()) {
			if(user.getName().equalsIgnoreCase(userTobeSearched)) {
				userInLib = user;
				break;
			}
		}
		
		return userInLib;
		
	}

	private static Book searchBook(Library lib, String bookToBeSearched) {
		
		Book bookInLib = null;
		
		for(Book book : lib.getBook()) {
			if(book.getTitle().equalsIgnoreCase(bookToBeSearched)) {
				bookInLib = book;
				break;
			}
		}
		
		return bookInLib;
		
	}

	private static void returnBook(Library lib, String bookToBeReturned) {
		Book bookLib = null;
		String issuerName = null;
		User userLib = null;
		for (Book book : lib.getBook()) {
			if(book.getTitle().equalsIgnoreCase(bookToBeReturned)) {
				bookLib = book;
				issuerName = book.getIssuerName();
				break;
			}
		}
		bookLib.setBookIssued(false);
		
		for(User user : lib.getUser()) {
			if(user.getName().equalsIgnoreCase(issuerName)) {
				userLib = user;
				break;
			}
		}
		userLib.setBookCount(userLib.getBookCount() - 1);
		
		Iterator<Book> iteratorBook = userLib.getBook().iterator();
		
		while(iteratorBook.hasNext()) {
			Book b = iteratorBook.next();
			
			if(b.getTitle().equalsIgnoreCase(bookToBeReturned)) {
				b= null;
				break;
			}
		}
		
		//userLib.getBook().remove(bookLib);
		
		removeAndAddToLib(lib, bookLib, userLib);
		
	}

	private static void issueBook(Library lib, String bookToBeIssued, String issuerName) {
		// check whether the book exists in library or is already issued
		boolean isBookInLib = false;
		boolean isUserELigibleForBook = false;
		Book bookLib = null;
		User userLib = null;
		
		for (Book book : lib.getBook()) {
			if (book.getTitle().equalsIgnoreCase(bookToBeIssued) && !book.isBookIssued()) {
				isBookInLib = true;
				bookLib = book;
				break;
			}
		}

		// check whether the user has not exceeded the number of books to be
		// issued
		if (isBookInLib) {
			for (User user : lib.getUser()) {
				if (user.getName().equalsIgnoreCase(issuerName) && user.getBookCount() < maxNofBooksUserIssued) {
					isUserELigibleForBook = true;
					userLib = user;
					break;

				}
			}
		}

		// issue the book

		if (isBookInLib && isUserELigibleForBook) {

			bookLib.setBookIssued(true);
			bookLib.setIssuerName(issuerName);
			if (userLib.getBook() != null && !userLib.getBook().isEmpty()) {
				userLib.getBook().add(bookLib);
			} else {
				userLib.setBook(Arrays.asList(bookLib));
			}

			userLib.setBookCount(userLib.getBookCount() + 1);

			removeAndAddToLib(lib, bookLib, userLib);

		}

	}

	private static void removeAndAddToLib(Library lib, Book bookLib, User userLib) {
		Iterator<Book> iteratorBook = lib.getBook().iterator();

		while (iteratorBook.hasNext()) {
			Book b = iteratorBook.next();
			if (b.getTitle().equalsIgnoreCase(bookLib.getTitle())) {
				b.setBookIssued(bookLib.isBookIssued());
				b.setIssuerName(bookLib.getIssuerName());
				break;
			}
		}
		

		Iterator<User> iteratorUser = lib.getUser().iterator();

		while (iteratorUser.hasNext()) {
			User user = iteratorUser.next();
			if (user.getName().equalsIgnoreCase(userLib.getName())) {
				user.setBookCount(userLib.getBookCount());
				user.setBook(userLib.getBook());
				break;
			}
		}
		
	}

	private static void addUsersToSystem(Library lib, User ...users) {
		
		List<User> usersList = Arrays.asList(users);

		lib.setUser(usersList);
		
	}

	private static void addBooksToSystem(Library lib, Book ...books) {

		List<Book> bookList = Arrays.asList(books);

		lib.setBook(bookList);

	}

}
