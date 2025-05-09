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
		ControlJouerCarte controlJouerCarte = new ControlJouerCarte(controlPartie); 
		
		BoundaryPartie boundaryPartie = new BoundaryPartie(controlPartie, controlJouerCarte); 
		BoundaryJouerCarte boundaryJouerCarte = new BoundaryJouerCarte(controlJouerCarte); 
		
		boundaryPartie.initPartie(); // pioche des 4 cartes
		
		
		while(!boundaryPartie.estPartieFini()) {
			boundaryPartie.passerAuTourSuivant();
			
			
			// Affichage ATH joueur
			boundaryPartie.afficherTourJoueur(boundaryPartie.getTourJoueur());
			
			// Afficher stats joueur courant 
			boundaryPartie.afficherPirate();
			
			// Affichage main Joueur courant 
			boundaryPartie.afficherMain(boundaryPartie.getTourJoueur()); 
			
			// choix de la carte 
			int numCarte = boundaryJouerCarte.choisirCarte(); 
			
			// Validation de la carte 
			boundaryJouerCarte.appliquerEffet(boundaryPartie.getTourJoueur(), numCarte); 
			
			// Subir les dégats 4
			
			if(boundaryPartie.getTourJoueur() == 1) boundaryPartie.subirDegat(1); else boundaryPartie.subirDegat(2); 
			
			// Afficher pirate
			boundaryPartie.afficherPirate();
			
			// Piocher une nouvelle carte à la fin du tour
			boundaryJouerCarte.piocherCarte(1);
		}
		
		
		
	}
	
}
