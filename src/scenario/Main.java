package scenario;

import entities.*; 
import control.*; 
import boundary.*; 

public class Main {
	
	public static void main(String[] args) {
		Pirate joueur1 = new Pirate(10, 0, 1); 
		Pirate Joueur2 = new Pirate(10, 0, 2); 
		
		Deck deckJoueur1 = new Deck(); 
		Deck deckJoueur2 = new Deck(); 
		
		joueur1.setDeck(deckJoueur1);
		Joueur2.setDeck(deckJoueur2);
		
		ZoneCarte zoneCarte = new ZoneCarte(); 
		
		Partie partie = new Partie(joueur1, Joueur2, zoneCarte);
		
		ControlPartie controlPartie = new ControlPartie(partie); 
		ControlJouerCarte controlJouerCarte = new ControlJouerCarte(); 
		
		BoundaryPartie boundaryPartie = new BoundaryPartie(controlPartie); 
		BoundaryJouerCarte boundaryJouerCarte = new BoundaryJouerCarte(controlJouerCarte); 
		
		boundaryPartie.initPartie();
		boundaryPartie.afficherMain(1);
		
//		while(!boundaryPartie.estPartieFini()) {
//			System.out.println("C'est au tour du Joueur " + String.valueOf(boundaryPartie.getTourJoueur()));
//		}
		
		boundaryPartie.passerAuTourSuivant();
		System.out.println("C'est au tour du Joueur " + String.valueOf(boundaryPartie.getTourJoueur()));
		
	}
	
}
