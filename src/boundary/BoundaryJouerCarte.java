package boundary;

import java.util.InputMismatchException;
import java.util.Scanner;

import control.*;

public class BoundaryJouerCarte {
	private ControlJouerCarte controlJouerCarte;

	public BoundaryJouerCarte(ControlJouerCarte controlJouerCarte) {
		this.controlJouerCarte = controlJouerCarte;
	}

	public int choisirCarte() {
	    int choix = 0;
	    Scanner scanner = new Scanner(System.in);
		while(choix < 1 || choix > ControlChoisirBundle.NB_BUNDLES) {
			try {
				choix = scanner.nextInt();
			} catch(InputMismatchException exception) {
				scanner.nextLine();
			}
			if (choix < 1 || choix > 4) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entre 1 et 4.");
            }
		}
		return choix;
	}
	
	public void appliquerEffet(int numCarte) {
    	controlJouerCarte.appliquerEffet(numCarte); 
    }
	
	public void piocherCarte(int numCarteAPiocher) {
		controlJouerCarte.piocherCarte(numCarteAPiocher); 
	}
	
}