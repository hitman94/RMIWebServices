package users;

import java.util.concurrent.ConcurrentHashMap;

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
	
	public boolean createUser(String username,String password) {
		if(map.get(username)!=null)
			return false;
		map.put(username, password);
		return true;
	}

}
