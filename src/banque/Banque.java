package banque;

import java.util.concurrent.ConcurrentHashMap;
/**
 * Banque.java
 * @author Thomas
 *
 */
public class Banque {
	private Double solde;
	private ConcurrentHashMap<String, Double> map;

	public Banque(){
		this.solde = 0.0;
		this.map = new ConcurrentHashMap<>();
	}
	
	public void depotDe(String username, double montant){
		solde = map.get(username);
		solde += montant;
		map.put(username, solde);
	}
	
	public boolean retraitDe(String username, double montant){
		solde = map.get(username);
		if(solde - montant < 0)
			return false;
		else {
			solde -= montant;
			map.put(username, solde);
			return true;
		}
	}
	
	public double valeurDuSolde(String username){
		return map.get(username);
	}
}
