package sellingBook;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

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
}



