package entities;

import Control.ControlJouerCarte;

public class Main {
//	Pirate joueur1; 
//	Pirate joueur2; 
//	boolean tourJoueur = true;
//	int nbTour = 0; 
//	
////	public void tourDeJeu(); 
	
	
	public static boolean jouer_tour(Pirate player1, Pirate player2, Pioche pioche, ZoneCarte zoneCarte, int tour_joueur) {
		
		
		Pirate joueur; 
		if (tour_joueur == 1) joueur = player1; 
		else joueur = player2;
		
		String phrase_tour = String.format("Pirate %d veuillez jouer", tour_joueur); 
		System.out.println(phrase_tour);
		player1.afficherPirate();
		
		
		
		if(player1.getPv() <= 0) {
			System.out.println("Le pirate 1 est mort, le pirate 2 gagne");
			return true; 
					
		} else if (player2.getPv() <= 0) {
			System.out.println("Le pirate 2 est mort, le pirate 1 gagne");
			return true;
		}
		
		joueur.afficherMain();
		zoneCarte.afficherZoneDeJeu(tour_joueur);
		
		return false; 
	}
	
	
	public static void main(String[] args) {
		
		Pirate player1 = new Pirate(10, 50, 1); 
		Pirate player2 = new Pirate(10, 50, 1); 
		
		FabriqueDeCarte fabriqueCarte = new FabriqueDeCarte(); 
		Pioche pioche = new Pioche(fabriqueCarte.creerCartes()); 
		
		System.out.println("Dans la pioche il y a : \n");
		pioche.getCartes().forEach(carte -> {System.out.println(carte.getNom());} );
		System.out.println("\n");
		
		player1.piocherCarte(pioche, 4);
		player2.piocherCarte(pioche, 4);
		
		System.out.println("Je suis le joueur 1 et dans ma main il y a : \n");
		player1.afficherMain();
		System.out.println("\n");
		
		
		System.out.println("Je suis le joueur 2 et dans ma main il y a : \n");
		player2.afficherMain();
		System.out.println("\n");
		
		pioche.afficherPioche();
		
		
		
//		boolean play = true; 
//		while(play) {
//			if(player1.getPv() <= 0) {
//				play = false; 
//				System.out.println("Le pirate 1 est mort, le pirate 2 gagne");
//			} else if (player2.getPv() <= 0) {
//				play = false; 
//				System.out.println("Le pirate 2 est mort, le pirate 1 gagne");
//			}
//			
//			player1.afficherPirate();
//			
//			
//		}
		ZoneCarte zoneCarte = new ZoneCarte(); 
		
		jouer_tour(player1, player2, pioche, zoneCarte, 1); 		
	}
	
	
}
