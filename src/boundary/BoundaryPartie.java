package boundary;

import control.ControlPartie;
import entities.Carte;
import entities.Pirate;

public class BoundaryPartie {
	private ControlPartie controlPartie;
	
	public BoundaryPartie(ControlPartie controlPartie) {
		this.controlPartie = controlPartie;
	}


	public boolean estPartieFini() {
		return controlPartie.estPartieFini(); 
	}
	
	public int getTourJoueur() {
		return controlPartie.getTourJoueur(); 
	}
	
	public void passerAuTourSuivant() {
		controlPartie.passerAuTourSuivant(); 
	}


	public void initPartie() {
		controlPartie.initPartie(); 
		
	}


	public void afficherMain(int numJoueur) {
	    System.out.println("Joueur " + numJoueur + " : dans ma main il y a : ");
	    for (Carte c : controlPartie.getMain(numJoueur)) {
	        System.out.println(c.toString());
	    }
	}
	
	public void afficherTourJoueur(int numJoueur) {
		controlPartie.afficherTourJoueur(numJoueur); 
	}


	public void subirDegat(int degat) {
		System.out.println("Aie !");
	}
	
}
