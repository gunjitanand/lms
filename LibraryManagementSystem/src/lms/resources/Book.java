package lms.resources;

public class Book {
	
	
	private String title;
	private String author;
	private String issuerName;
	private boolean isBookIssued;
	
	public Book(String title, String author, String issuerName, boolean isBookIssued) {
		this.author = author;
		this.title = title;
		this.issuerName = issuerName;
		this.isBookIssued = isBookIssued;
	}	
	
	
	public boolean isBookIssued() {
		return isBookIssued;
	}

	public void setBookIssued(boolean isBookIssued) {
		this.isBookIssued = isBookIssued;
	}


	public String getIssuerName() {
		return issuerName;
	}

	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if(o instanceof Book && this.getTitle().equalsIgnoreCase(((Book)o).getTitle())) {
			return true;
		} else
			return false;
		
	
	}
	
	
	

}
