package scenario;

import entities.*; 
import control.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import boundary.*; 

public class Main {
	
	public static void lancerPartie() {
		Pirate joueur1 = new Pirate(10, 0, 1); 
		Pirate joueur2 = new Pirate(10, 0, 2); 
		
		Deck deckJoueur1 = new Deck(); 
		Deck deckJoueur2 = new Deck();
		Deck bundleAleatoire = new Deck();
		
		joueur1.setDeck(deckJoueur1);
		joueur2.setDeck(deckJoueur2);
		
		
		Partie partie = new Partie(joueur1, joueur2);
		
		ControlPartie controlPartie = new ControlPartie(partie); 
		ControlJouerCarte controlJouerCarte = new ControlJouerCarte(controlPartie); 
		ControlChoisirBundle controlChoisirBundle = new ControlChoisirBundle(bundleAleatoire);
		
		BoundaryPartie boundaryPartie = new BoundaryPartie(controlPartie); 
		BoundaryJouerCarte boundaryJouerCarte = new BoundaryJouerCarte(controlJouerCarte); 
		BoundaryChoisirBundle boundaryChoisirBundle = new BoundaryChoisirBundle(controlChoisirBundle);
		boundaryPartie.initPartie(); // pioche des 4 cartes
		
		Pirate joueurCourant;
		
		while(!boundaryPartie.estPartieFini()) {
			boundaryPartie.passerAuTourSuivant();
			joueurCourant = boundaryPartie.getJoueurCourant();
			
			// Affichage ATH joueur
			boundaryPartie.afficherTourJoueur(boundaryPartie.getTourJoueur());
			
			// Afficher stats joueur courant 
			boundaryPartie.afficherPirate();
			
			List<ArrayList<Carte>> lBundles = boundaryChoisirBundle.getBundles();
			//Affichage choix des bundles
			boundaryChoisirBundle.afficherBundle(lBundles);
			
			//Choisir bundles
			boundaryChoisirBundle.selectionnerBundle(lBundles, joueurCourant);
			
			//Mélanger les cartes
			boundaryPartie.melangerDeck();
			// Affichage main Joueur courant 
			boundaryPartie.afficherMain(boundaryPartie.getTourJoueur()); 
			
			// choix de la carte 
			int numCarte = boundaryJouerCarte.choisirCarte(); 
			
			// Validation de la carte 
			boundaryJouerCarte.appliquerEffet(numCarte); 
			
			// Subir les dégats
			
			if(boundaryPartie.getTourJoueur() == 1) boundaryPartie.subirDegat(1); else boundaryPartie.subirDegat(2); 
			
			// Afficher pirate
			boundaryPartie.afficherPirate();
			
			// Piocher une nouvelle carte à la fin du tour
			boundaryJouerCarte.piocherCarte(1);
		}	
		boundaryPartie.afficherGagnant();
	}
	
	public static void main(String[] args) {
		int choix = 0;
	    Scanner scanner = new Scanner(System.in);
		while(choix != 2) {
			System.out.println("Bienvenue dans le jeu Pirate:\n-1: lancer partie\n-2: quitter");
			choix = scanner.nextInt();
			if (choix < 1 || choix > 2) {
                System.out.println("Entrée invalide. Veuillez entrer 1 ou 2.");
            }
			if(choix == 1) {
				lancerPartie();
			}
		}
		System.out.println("Vous avez quitté le jeu.");
	}
		
}
