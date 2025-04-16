package entities;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Pioche {
	
	private List<Carte> cartes; 
	
	
	
	public Pioche(List<Carte> cartes) {
		this.cartes = cartes; 
	}
	
	public int getNbCarte() {
	    return cartes.size();
	}

	
	public List<Carte> getCartes() {
		return cartes; 
	}
	
	public void afficherPioche() {
	    System.out.println("Contenu de la pioche :");
	    for (Carte c : cartes) {
	        System.out.println("- " + c.getNom());
	    }
	}
	
	public Carte donnerCarte() {
	    if (cartes.isEmpty()) {
	        return null; // ou lève une exception selon ton design
	    }
	    Carte carteAleatoire = cartes.get(ThreadLocalRandom.current().nextInt(cartes.size()));
	    cartes.remove(carteAleatoire); 
	    return carteAleatoire;
	}

	
}
