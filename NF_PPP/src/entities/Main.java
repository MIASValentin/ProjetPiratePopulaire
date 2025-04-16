package entities;

public class Main {
//	Pirate joueur1; 
//	Pirate joueur2; 
//	boolean tourJoueur = true;
//	int nbTour = 0; 
//	
////	public void tourDeJeu(); 
	
	public static void main(String[] args) {
		
		Pirate player1 = new Pirate(); 
		Pirate player2 = new Pirate(); 
		
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
		
	}
	
	
}
