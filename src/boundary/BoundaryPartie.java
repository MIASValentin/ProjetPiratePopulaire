package boundary;

import control.ControlJouerCarte;
import control.ControlPartie;
import entities.Carte;
import entities.Pirate;

public class BoundaryPartie {
	private ControlPartie controlPartie;
	private ControlJouerCarte controlJouerCarte; 
	
	public BoundaryPartie(ControlPartie controlPartie, ControlJouerCarte controlJouerCarte) {
		this.controlPartie = controlPartie;
		this.controlJouerCarte = controlJouerCarte;
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
	        System.out.println(String.valueOf(controlPartie.getMain(numJoueur).indexOf(c) + 1) + "----" + c.toString());
	    }
	    System.out.println();
	}
	
	  public void afficherTourJoueur(int numJoueur) {
		  	int numeroTour = controlPartie.getNumeroTour(); 
	        String titre = " TOUR " + numeroTour + " - " + "Joueur " + String.valueOf(numJoueur) + " ";
	        int largeur = titre.length() + 4;

	        String bordure = "╔" + "═".repeat(largeur) + "╗";
	        String milieu = "║  " + titre + "  ║";
	        String bas = "╚" + "═".repeat(largeur) + "╝";

	        System.out.println();
	        System.out.println(bordure);
	        System.out.println(milieu);
	        System.out.println(bas);
	        System.out.println();
	        
	    
	    }


	public void subirDegat(int degat) {
		System.out.println("Aie !");
	}
	
	
	
	public void afficherPirate() {
		System.out.println(controlPartie.afficherPirate());
		System.out.println();
	}
	
}
