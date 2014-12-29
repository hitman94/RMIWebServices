package users;

import java.rmi.RemoteException;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.rpc.ServiceException;

import banquews.Banque;
import banquews.BanqueServiceLocator;

public class Users {
	
	private ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
	
	public boolean isValide(String usr, String pass) {
		String passmap = map.get(usr);
		if(passmap==null)
			return false;
		if(passmap.equals(pass))
			return true;
		return false;
	}
	
	public boolean createUser(String username, String password) {
		if (map.get(username) != null)
			return false;
		map.put(username, password);
		Banque b;
		try {
			b = new BanqueServiceLocator().getBanque();
			b.addUser(username);
		} catch (ServiceException | RemoteException e) {
			map.remove(username);
			return false;
		}

		return true;
	}

}
