package sellingBook;

import java.io.Serializable;


import sellingBook.interfaceRMI.IBook;

public class Book implements IBook, Serializable{

	private String title;
	private String author;
	private Long isbn;
	private Double price;
	

	public Book() {

	}
	
	public Book(
	           java.lang.Long ISBN,
	           java.lang.String author,
	           java.lang.Double price,
	           java.lang.String title) {
	           this.isbn = ISBN;
	           this.author = author;
	           this.price = price;
	           this.title = title;
	    }


	public Double getPrice() {
		return price;
	}

	public String getTitle() {
		return this.title;
	}
	public String getAuthor() {
		return this.author;
	}	
	public Long getISBN() {
		return this.isbn;
	}

}
