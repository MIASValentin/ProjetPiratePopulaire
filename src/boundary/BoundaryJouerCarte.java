package boundary;

import java.util.Scanner;

import control.*;

public class BoundaryJouerCarte {
	private ControlJouerCarte controlJouerCarte;

	public BoundaryJouerCarte(ControlJouerCarte controlJouerCarte) {
		this.controlJouerCarte = controlJouerCarte;
	}

	public int choisirCarte() {
	    Scanner scanner = new Scanner(System.in);
	    int choix = 0;

	    while (choix < 1 || choix > 4) {
	        System.out.print("Choisissez une carte à jouer (1 à 4) : ");
	        if (scanner.hasNextInt()) {
	            choix = scanner.nextInt();
	            if (choix < 1 || choix > 4) {
	                System.out.println("Entrée invalide. Veuillez entrer un nombre entre 1 et 4.");
	            }
	        } else {
	            System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
	            scanner.next(); // on ignore l'entrée non valide
	        }
	    }

	    return choix;
	}
	
	public void appliquerEffet(int numJoueur, int numCarte) {
    	controlJouerCarte.appliquerEffet(numJoueur, numCarte); 
    }
	
}
