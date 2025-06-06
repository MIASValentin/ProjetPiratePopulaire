package entities;

import java.util.ArrayList;
import java.util.List;

public class PirateMutant1 {
	int pv = 10; 
	int prime = 0; 
	int numeroJoueur;
	List<Carte> main = new ArrayList<>();
	Deck deck = new Deck(); 
 
	
	
	public PirateMutant1(int pv, int prime, int numeroJoueur) {
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
	
	public void changerPrime(int newValue) {
		this.prime = this.prime + newValue; 
	}
	
	//pioche random selon modulo dans le deck
	public void piocherCarte(int nbCarteAPiocher) {
	    for (int i = 0; i <= nbCarteAPiocher; i++) {  //changement de < a <=
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
	
}
