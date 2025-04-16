package entities;

import java.util.List;
import java.util.Set;

public class Pirate {
	int pv = 10; 
	int prime = 0; 
	Carte bateau; 
	List<Carte> main; 
	List<Carte> deck;
	
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
	public void piocherCarte(Pioche pioche, int nbCarteAPiocher) {
		for(int i = 0; i < 4; i++) {
			this.main.add(pioche.donnerCarte()); 

		}
	}
	
//	public void jouerCarte(Carte carte);
//	public void choisirDeck(List<Carte> deck); 
	
	
	
	
	
}
