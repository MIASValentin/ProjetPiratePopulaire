package entities;

import java.util.ArrayList;
import java.util.List;

public class Pirate {
	int pv = 10; 
	int prime = 0; 
	int numeroJoueur;
	Carte bateau; 
	List<Carte> main = new ArrayList<>();
	Deck deck = new Deck(); 
 
	
	
	public Pirate(int pv, int prime, int numeroJoueur) {
		super();
		this.pv = pv;
		this.prime = prime;
		this.numeroJoueur = numeroJoueur;
	}
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
	public int getNum() {
		return numeroJoueur;
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
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
	
	public void changerPv(int newValue) {
		this.pv = this.pv + newValue; 
	}
	
	public void changerPime(int newValue) {
		this.prime = this.prime + newValue; 
	}
	
	//pioche random selon modulo dans le deck
	public void piocherCarte(int nbCarteAPiocher) {
	    for (int i = 0; i < nbCarteAPiocher; i++) {
	        Carte piochee = deck.donnerCarte();
	        if (piochee != null) {
	            this.main.add(piochee);
	        }
	        deck.getCartes().remove(piochee); 
	    }
	    
	    
	}
	
	public String afficherMain() {
	    String s = ("Main du pirate " + this.numeroJoueur + "\n");
	    for (Carte c : main) {
	        s += ("-" + c + "\n");
	    }
	    return s;
	}
	
	public String afficherPirate() { 
        return ("Pirate " + this.numeroJoueur + " | PV : " + this.pv + " | Prime : " + this.prime);
    }
	public void melangerDeck() {
		deck.melangerCartes();
	}

	
//	public void jouerCarte(Carte carte);
//	public void choisirDeck(List<Carte> deck); 
	
	
	
	
	
}
