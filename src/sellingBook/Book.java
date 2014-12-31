package sellingBook;

import java.io.Serializable;
import java.rmi.RemoteException;

import sellingBook.interfaceRMI.IBook;

public class Book implements IBook, Serializable{

	private static final long serialVersionUID = -8117130946963122801L;

	private java.lang.Long ISBN;

    private java.lang.String author;

    private java.lang.Double price;

    private java.lang.String title;
    
    private java.lang.Integer stock;
	

	public Book() {
	

	}
	
	
	
	public Book(
	           java.lang.Long ISBN,
	           java.lang.String author,
	           java.lang.Double price,
	           java.lang.String title,
	           java.lang.Integer NbExemplaire) {
	           this.ISBN = ISBN;
	           this.author = author;
	           this.price = price;
	           this.title = title;
	           this.stock = NbExemplaire;
	    }
	
	public void setPrice(java.lang.Double price) {
		this.price = price;
	}
	
	public void setAuthor(java.lang.String author) {
		this.author = author;
	}
	
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	
	public void setISBN(java.lang.Long iSBN) {
		ISBN = iSBN;
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
		return this.ISBN;
	}



	@Override
	public int getStock() throws RemoteException {
		return this.stock;
	}



	@Override
	public void setStock(java.lang.Integer stock) throws RemoteException {
		this.stock = stock;
	}

}
