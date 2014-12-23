package banque;

public class Compte {
	private Integer solde;
	
	public Compte(){
		this.solde = 0;
	}
	
	public Compte(int montant){
		this.solde = montant;
	}
	
	public void depotDe(int montant){
		solde += montant;
	}
	
	public boolean retraitDe(int montant){
		if(solde - montant < 0)
			return false;
		else {
			solde -= montant;
			return true;
		}
	}
	
	public int valeurDuSolde(){
		return solde;
	}
}
