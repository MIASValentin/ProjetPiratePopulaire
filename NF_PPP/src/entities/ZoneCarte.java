package entities;

import java.util.ArrayList;
import java.util.List;

public class ZoneCarte {
	private List<Carte> zoneJ1 = new ArrayList<>(); 
	private List<Carte> zoneJ2 = new ArrayList<>(); 
	
	public boolean put(int zone, Carte carte) {
		if(zone == 1) return zoneJ1.add(carte); 
		else if (zone == 2) return zoneJ2.add(carte);
		else return false; 
	}
	
	public void applyEffetInstantane(EffetInstantane ei, Pirate p1, Pirate p2) {
		ei.getEffet().accept(p1, p2);
	}
	
	 public void afficherZoneDeJeu(int numeroJoueur) {
	        System.out.println("===== Zone de Jeu =====");
	        List<Carte> zoneDeJeu; 
	        if(numeroJoueur == 1) zoneDeJeu = this.zoneJ1; else zoneDeJeu = this.zoneJ2;  

	        if (zoneDeJeu.isEmpty()) {
	            System.out.println("La zone de jeu est vide.");
	        } else {
	            int index = 1;
	            for (Carte carte : zoneDeJeu) {
	                System.out.println(carte);
	            }
	        }
	 }
	
}
