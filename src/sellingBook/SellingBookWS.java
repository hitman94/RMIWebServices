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

import sellingBook.interfaceRMI.IBook;
import sellingBook.interfaceRMI.ILibraries;


public class SellingBookWS {
private ILibraries lib;
	public SellingBookWS() throws RemoteException, MalformedURLException {
		lib = ServerLibraries.getInstance();
	}
	
	public void test() throws NumberFormatException, RemoteException{
		lib.addBook(new Long("14254414"), "title", "Florian", new Double("124574"));
		IBook b = lib.getBook(new Long("14254414"));
		System.out.println(b.getAuthor());
	}
	
public static void serialize(String username, IBook[] basket) {
		
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
	
	public static IBook[] unserialize(String username) {

		ObjectInputStream ois = null;
		List<IBook> basket = new ArrayList<IBook>();

		try {
			final FileInputStream fichier = new FileInputStream(
					"users" + System.getProperty("file.separator") + username);
			ois = new ObjectInputStream(fichier);
			basket = (List<IBook>) ois.readObject();
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
		return (IBook[]) basket.toArray();
	}
}



