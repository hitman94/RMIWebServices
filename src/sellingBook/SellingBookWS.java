package sellingBook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.HttpContext;

import sellingBook.interfaceRMI.IBook;
import sellingBook.interfaceRMI.ILibraries;


public class SellingBookWS {
	private ILibraries lib;
	public SellingBookWS() throws RemoteException, MalformedURLException {
		lib = ServerLibraries.getInstance();
		System.out.println("test!!!!!");
	}
	
	
	// Methode de test à la con
	public void test() throws NumberFormatException, RemoteException{
//		lib.addBook(new Long("14254414"), "title", "Florian", new Double("124574"));
//		IBook b = lib.getBook(new Long("14254414"));
//		System.out.println(b.getAuthor());
		
	}

	public Book[] getBooksThatContain(String title) throws RemoteException{
		List<IBook> books;
		books = lib.getBooksThatContain(title);
		return (Book[]) books.toArray();
	}
	
	
	public Book getBookByISBN(Long ISBN) throws RemoteException{
		return (Book) lib.getBook(ISBN);
	}
	public Book getBookByTitle(String title) throws RemoteException{
		return (Book) lib.getBook(title);
	}
	
	public Book removeBook(Long ISBN) throws RemoteException {
		return (Book) lib.removeBook(ISBN);
	}
	
	
	public Book[] getBooksByAuthor(String author) throws RemoteException{
		List<IBook> books;
		books = lib.getBooksByAuthor(author);
		return (Book[]) books.toArray();
	}
	

	public void addBook(Long isbn,String title,String authour,Double price) throws RemoteException {
		lib.addBook(isbn, title, authour, price);
	}
	
	
	public Book[] getAllBooks() throws RemoteException {
		List<IBook> books;
		books = lib.getAllBooks();
		return (Book[]) books.toArray();
		
	}
	
	public void serialize(String username, Book[] basket) {

		ObjectOutputStream oos = null;

		System.out.println("Working Directory = " + System.getProperty("user.dir"));

		try {
			File dirUsers = new File("users");
			dirUsers.mkdir();
			final FileOutputStream fichier = new FileOutputStream(
					dirUsers + System.getProperty("file.separator") + username);
			oos = new ObjectOutputStream(fichier);
			oos.writeObject(basket);
			oos.flush();
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.flush();
					oos.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public Book[] unserialize(String username) {

		ObjectInputStream ois = null;
		List<Book> basket = new ArrayList<Book>();

		try {
			final FileInputStream fichier = new FileInputStream(
					"users" + System.getProperty("file.separator") + username);
			ois = new ObjectInputStream(fichier);
			basket = (List<Book>) ois.readObject();
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}
		return (Book[]) basket.toArray();
	}
}



