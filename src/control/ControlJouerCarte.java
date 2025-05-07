package control;

import java.util.function.BiConsumer;

import entities.Carte;
import entities.EffetInstantane;
import entities.Pirate;

public class ControlJouerCarte {

	private ControlPartie controlPartie; 
	

	public ControlJouerCarte(ControlPartie controlPartie) {
		this.controlPartie = controlPartie;
	}



	public void appliquerEffet(int numJoueur, int numCarte) {
		Pirate origine = controlPartie.getPirateDuTour(); 
		Pirate cible = controlPartie.getAutrePirate(); 	
		EffetInstantane carteAJouer = (EffetInstantane) origine.getMain().get(numCarte); 
		
		carteAJouer.getEffet().accept(origine, cible);
	}

	

}
