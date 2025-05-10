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
	
	public Pirate getJoueurCourant() {
		return controlPartie.getPirateDuTour();
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
	        String titre = " TOUR " + numeroTour + " - " + "Joueur " + numJoueur + " ";
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
		System.out.println("Aie ! (-" + degat + ")");
	}
	
	
	
	public void afficherPirate() {
		System.out.println(controlPartie.afficherPirate());
		System.out.println();
	}
	
	public void afficherGagnant() {
		int gagnant = controlPartie.getGagnant();
		if(gagnant == 3) {
			System.out.println("Il y a eu égalité...");
		}
		else {
			System.out.println("Le gagnant est : le joueur " + gagnant + "!");
		}
	}
	public void melangerDeck() {
		controlPartie.melangerDeck();
	}
}
