package entities;

public class Partie {
	//Pirate dans la partie
	private Pirate joueur1, joueur2;
	
	//compteur de tour -> selon pair ou impair donne le joueur actif
	private int nbTour = 0 ;
	private Deck pioche = new Deck(); 
	
	public Partie (Pirate joueur1, Pirate joueur2) {
		this.joueur1 = new Pirate(10, 20, 1); 
		this.joueur2 = new Pirate(10, 20, 2); 
		
	}
	
}
