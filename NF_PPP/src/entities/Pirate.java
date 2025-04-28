package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import InterfaceNF.IPirate;

public class Pirate implements IPirate{
	int pv = 10; 
	int prime = 0; 
	Carte bateau; 
	List<Carte> main = new ArrayList<>();
	List<Carte> deck = new ArrayList<>();

	
	public int getPv() {
		return pv;
	}
	public void setPv(int pv) {
		this.pv = pv;
	}
	public int getPrime() {
		return prime;
	}
	public void setPrime(int prime) {
		this.prime = prime;
	}
	public Carte getBateau() {
		return bateau;
	}
	public void setBateau(Carte bateau) {
		this.bateau = bateau;
	}
	public List<Carte> getMain() {
		return main;
	}
	public void setMain(List<Carte> main) {
		this.main = main;
	}
	public List<Carte> getDeck() {
		return deck;
	}
	public void setDeck(List<Carte> deck) {
		this.deck = deck;
	}
	
	
	public void changerPv(int newValue) {
		this.pv = this.pv + newValue; 
	}
	
	public void changerPime(int newValue) {
		this.prime = this.prime + newValue; 
	}
	
	//pioche random selon modulo dans le deck
	@Override
	public void piocherCarte(Pioche pioche, int nbCarteAPiocher) {
	    for (int i = 0; i < nbCarteAPiocher; i++) {
	        Carte piochee = pioche.donnerCarte();
	        if (piochee != null) {
	            this.main.add(piochee);
	        }
	    }
	}
	
	public void afficherMain() {
	    System.out.println("Main du pirate :");
	    for (Carte c : main) {
	        System.out.println("- " + c.getNom());
	    }
	}

	@Override
	public void jouerCarte() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void choisirDeck() {
		// TODO Auto-generated method stub
		
	}
	public String getNom() {
		// TODO Auto-generated method stub
		return null;
	}

	
//	public void jouerCarte(Carte carte);
//	public void choisirDeck(List<Carte> deck); 
	
	
	
	
	
}
