package banque;

import java.util.concurrent.ConcurrentHashMap;
/**
 * Banque.java
 * @author Thomas
 *
 */
public class Banque {
	private ConcurrentHashMap<String, Double> map;

	public Banque(){
		this.map = new ConcurrentHashMap<>();
	}
	
	public void depotDe(String username, double montant){
		Double solde = map.get(username);
		if(solde==null)
			return;
		solde += montant;
		map.put(username, solde);
	}
	
	public boolean retraitDe(String username, double montant){
		Double solde = map.get(username);
		if(solde==null)
			return false;
		if(solde - montant < 0)
			return false;
		else {
			solde -= montant;
			map.put(username, solde);
			return true;
		}
	}
	
	public Double valeurDuSolde(String username){
		
		Double solde = map.get(username);
		if(solde==null)
			return 0.0;
		
		return solde;
	}
	
	public boolean addUser(String username) {
		if(map.get(username)==null) {
			map.put(username, 0.0);
			return true;
		}
		return false;
	}
}
