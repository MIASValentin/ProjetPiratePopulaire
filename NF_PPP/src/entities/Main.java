package entities;

public class Main {
	Pirate joueur1; 
	Pirate joueur2; 
	boolean tourJoueur = true;
	int nbTour = 0; 
	
//	public void tourDeJeu(); 
	
	public static void main(String[] args) {
		
		Pirate player1 = new Pirate(); 
		Pirate player2 = new Pirate(); 
		
		FabriqueDeCarte fabriqueCarte = new FabriqueDeCarte(); 
		Pioche pioche = new Pioche(fabriqueCarte.creerCartes()); 
		
		
		
	}
	
	
}
