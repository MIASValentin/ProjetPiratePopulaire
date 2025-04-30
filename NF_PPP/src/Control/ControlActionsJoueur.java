package Control;

import entities.Carte;
import entities.Pioche;
import entities.Pirate;

//Classe Test
public class ControlActionsJoueur {
	private Pirate joueur; 
	private int numeroJoueur; 
	private Pioche pioche; 
	
	
	public void changerPv(int value) {
		joueur.setPv(joueur.getPv() + value);
	}
	
	public void changerPime(int value) {
		joueur.setPrime(joueur.getPrime() + value);
	}
	
	public void piocherCarte(Pioche pioche, int nbCarteAPiocher) {
		 for (int i = 0; i < nbCarteAPiocher; i++) {
		        Carte piochee = pioche.donnerCarte();
		        if (piochee != null) {
		            this.main.add(pioche);
		        }
		    }
	}
	
	
	public void piocherCarte(Pioche pioche, int nbCarteAPiocher) {
	   
	}
	
}
