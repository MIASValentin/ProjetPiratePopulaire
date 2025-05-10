package boundary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import control.ControlChoisirBundle;
import entities.Carte;
import entities.Pirate;

public class BoundaryChoisirBundle {
	private ControlChoisirBundle controlChoisirBundle;
	private Scanner scanner = new Scanner(System.in);
	
	public BoundaryChoisirBundle(ControlChoisirBundle controlChoisirBundle) {
		this.controlChoisirBundle = controlChoisirBundle;
	}

	public void selectionnerBundle(List<ArrayList<Carte>> lBundles, Pirate joueur) {
		int choix = scanner.nextInt();
		while(choix < 1 || choix > ControlChoisirBundle.NB_BUNDLES) {
			System.out.println("Entrée invalide, veuillez choisir un bundle (1-" + ControlChoisirBundle.NB_BUNDLES + ")");
		}
		System.out.println("vous avez choisi le bundle "+ choix +
				", nous allons maintenant l'ajouter a votre deck !");
		controlChoisirBundle.selectionnerBundle(lBundles, joueur, choix-1);
	}
	
	public void afficherBundle(List<ArrayList<Carte>> lBundles) {
		System.out.println("Voici les trois bundles: choisisser en 1 !");
		for(int i = 0; i < lBundles.size();i++) {
			System.out.println(afficherBundle(lBundles.get(i),i));
		}
	}
	
	public List<ArrayList<Carte>> getBundles() {
		return controlChoisirBundle.getBundles();
	}
	
	private String afficherBundle(List<Carte> bundle, int numBundle) {
		StringBuilder s = new StringBuilder();
		s.append("Bundle " + (numBundle+1) + "\n");
	    for (Carte c : bundle) {
	        s.append("-" + c + "\n");
	    }
	    return s.toString();
	}
}
