package sellingBook;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import sellingBook.interfaceRMI.ILibraries;

public class ServerLibraries {
	private static ILibraries instance;

	public static synchronized ILibraries getInstance(){
		if(instance==null){
		    System.out.println("Working Directory = " + System.getProperty("user.dir"));
			System.setProperty("java.security.policy", "sec.policy");
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new RMISecurityManager());
			}
			System.setProperty("java.rmi.server.codebase", "file://C:/Users/Florian/workspace-EE/ServerLibraries/bin/");
			System.setProperty("java.security.policy","sec.policy");
			System.setProperty("java.rmi.server.useCodebaseOnly ", "false");
			try {
				instance = (ILibraries) Naming.lookup("rmi://localhost/Libraries");
			} catch (MalformedURLException e1) {
				System.out.println("Malformed url");
				e1.printStackTrace();
			} catch (RemoteException e1) {
				System.out.println("Remote exeption sur le lookup");
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				System.out.println("Not bound exeption");
				e1.printStackTrace();
			}
		}
		return instance;
	}

}
