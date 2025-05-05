package entities;

public class Partie {
	//Pirate dans la partie
	private Pirate joueur1, joueur2;
	private int nbTour = 0 ;
	private ZoneCarte zoneCarte; 
	
	public int getNbTour() {
		return nbTour;
	}
	
	public void setNbTour(int nbTour) {
		this.nbTour = nbTour; 
	}
	
	public Partie (Pirate joueur1, Pirate joueur2, ZoneCarte zoneCarte) {
		this.joueur1 = joueur1; 
		this.joueur2 = joueur2; 
		this.zoneCarte = zoneCarte; 
	}
	
	
	
	public Pirate getJoueur(int numJoueur) {
		if(numJoueur == 1) return joueur1; 
		else return joueur2; 
	 }
	
}
