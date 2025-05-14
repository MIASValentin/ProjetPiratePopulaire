package entities;

public class Partie {
	//Pirate dans la partie
	private Pirate joueur1, joueur2;
	private int nbTour = 0 ;
	
	public int getNbTour() {
		return nbTour;
	}
	
	public void setNbTour(int nbTour) {
		this.nbTour = nbTour; 
	}
	
	public Partie (Pirate joueur1, Pirate joueur2) {
		this.joueur1 = joueur1; 
		this.joueur2 = joueur2; 
	}
	
	
	
	public Pirate getJoueur(int numJoueur) {
		if(numJoueur == 1) return joueur1; 
		else return joueur2; 
	 }
	
	
}
