package sellingBook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.impl.io.OptionalDataException;
import com.sun.net.httpserver.HttpContext;

import sellingBook.interfaceRMI.IBook;
import sellingBook.interfaceRMI.ILibraries;


public class SellingBookWS {
	private ILibraries lib;
	public SellingBookWS() throws RemoteException, MalformedURLException {

		lib = ServerLibraries.getInstance();
	}
	
	
	// Methode de test tr�s utile
	public void test() throws NumberFormatException, RemoteException{
		lib.addBook(new Long("14254414"), "title", "Florian", new Double("124574"),new Integer(3));
		lib.addBook(new Long("1111"), "Tartuffe", "Moliere", new Double("13.99"),new Integer(1));
		lib.addBook(new Long("12356"), "Hamlett", "Shakespeare", new Double("10.59"),new Integer(10));
		lib.addBook(new Long("5698"), "Les miserables", "Victor Hugo", new Double("15.79"),new Integer(5));
		lib.addBook(new Long("458521"), "Mes problemes", "Racine", new Double("14.69"),new Integer(6));
		lib.addBook(new Long("13655478"), "Mes amies, mes amours mes emmerdes", "Herve villard", new Double("16.69"),new Integer(2));
		System.out.println("lol");
		
	}

	public Book[] getBooksThatContain(String title) throws RemoteException{
		List<IBook> books;
		books = lib.getBooksThatContain(title);
		Book[] toReturn = new Book[books.size()];
		int i=0;
		for(IBook b : books){
			toReturn[i] = new Book(b.getISBN(), b.getAuthor(), b.getPrice(), b.getTitle(), b.getStock());
			i++;
		}
		return toReturn;
	}
	
	
	public Book getBookByISBN(Long ISBN) throws RemoteException{
		IBook b = lib.getBook(ISBN);
		return  new Book(b.getISBN(), b.getAuthor(), b.getPrice(), b.getTitle(), b.getStock());
	}
	public Book getBookByTitle(String title) throws RemoteException{
		IBook b = lib.getBook(title);
		return new Book(b.getISBN(), b.getAuthor(), b.getPrice(), b.getTitle(), b.getStock());
	}
	
	public Book removeBook(Long ISBN) throws RemoteException {
		IBook b =  lib.removeBook(ISBN);
		 return new Book(b.getISBN(), b.getAuthor(), b.getPrice(), b.getTitle(), b.getStock());
	}
	
	
	public Book[] getBooksByAuthor(String author) throws RemoteException{
		List<IBook> books;
		books = lib.getBooksByAuthor(author);
		Book[] toReturn = new Book[books.size()];
		int i=0;
		for(IBook b : books){
			toReturn[i] = new Book(b.getISBN(), b.getAuthor(), b.getPrice(), b.getTitle(), b.getStock());
			i++;
		}
		return toReturn;
	}
	

	public void addBook(Long isbn,String title,String authour,Double price,Integer NbExemplaire) throws RemoteException {
		lib.addBook(isbn, title, authour, price,NbExemplaire);
	}
	
	
	public Book[] getAllBooks() throws RemoteException {
		List<IBook> books;
		books = lib.getAllBooks();
		Book[] toReturn = new Book[books.size()];
		int i=0;
		for(IBook b : books){
			toReturn[i] = new Book(b.getISBN(), b.getAuthor(), b.getPrice(), b.getTitle(), b.getStock());
			i++;
		}
		return toReturn;
	}
	
	public void serialize(String username, Book[] basket) {

		ObjectOutputStream oos = null;
		FileOutputStream fichier = null;
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		
		File dirUsers = new File("users");
		if(!dirUsers.exists())
			dirUsers.mkdir();
		
		if(basket==null) {
			File userbasket = new File(dirUsers +  System.getProperty("file.separator") + username);
			userbasket.delete();
		}
		try {
			
			
			fichier = new FileOutputStream(
					dirUsers + System.getProperty("file.separator") + username);
			oos = new ObjectOutputStream(fichier);
			oos.writeObject(basket);
			oos.flush();
		} catch (final IOException e) {
			return ;
		} finally {
			try {
				if(fichier!=null)
					fichier.close();
				if (oos != null) {
					oos.flush();
					oos.close();
				}
			} catch (final IOException ex) {
				return ;
			}
		}
	}

	public Book[] unserialize(String username) {

		ObjectInputStream ois = null;
		Book[] basket = null;
		FileInputStream fichier = null;
		System.out.println("Ouverture du fichier "+System.getProperty("user.dir") + System.getProperty("file.separator")+
					"users" + System.getProperty("file.separator") + username);
		try {
			 fichier = new FileInputStream( System.getProperty("user.dir") + System.getProperty("file.separator")+
					"users" + System.getProperty("file.separator") + username);
			
			ois = new ObjectInputStream(fichier);
			basket = (Book[]) ois.readObject();
		
		
		} catch (OptionalDataException e) {
			
			return null;
		
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		} finally {
			try {
				if(fichier!=null)
					fichier.close();
				if (ois != null) {
					ois.close();
				}
			} catch (final IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return basket;
	}
}



