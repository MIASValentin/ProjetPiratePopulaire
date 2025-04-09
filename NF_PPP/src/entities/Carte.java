package entities;

public abstract class Carte implements ApplyCard {
	private TypeCarte type;
	private String nom; 
	private String description; 
	
	private int EffetJoueurPv; 
	private int EffetJoueurPrime;
	
	private int EffetAutreJoueurPv; 
	private int EffetAutreJoueurPrime; 
	
	BiFunction<Integer, Integer, Integer> applyEffetPv = (PvJoueur, effet) -> pvJoueur + effet;  
	
	ApplyCard apply = (Pirate j1, Pirate j2) -> {
		j1.setPv(applyEffetPv());
		j1.setPrime(EffetJoueurPrime); 
		j2.setPv(EffetAutreJoueurPv);
		j2.setPrime(EffetAutreJoueurPrime);
	}; 
	
	
}
