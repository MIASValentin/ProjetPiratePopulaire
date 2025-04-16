package entities;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Pioche {
	private int nbCarte; 
	private List<Carte> cartes; 
	
	public Pioche(List<Carte> cartes) {
		this.cartes = cartes; 
		this.nbCarte = cartes.size(); 
	}
	
	public Carte donnerCarte() {
		 Carte carteAleatoire = cartes.get(ThreadLocalRandom.current().nextInt(cartes.size()));
		 return carteAleatoire; 
	}
	
}
