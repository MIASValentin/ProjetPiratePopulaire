package control;

import java.util.List;

import entities.*;

public class ControlPartie {

    private Partie partie;
    private Pirate joueur1;
    private Pirate joueur2;

    private static final int PRIMEAATTEINDRE = 5;

    public ControlPartie(Partie partie) {
        this.partie = partie;
        this.joueur1 = partie.getJoueur(1);
        this.joueur2 = partie.getJoueur(2);
    }

    public boolean estPartieFini() {
        int pvJoueur1 = joueur1.getPv();
        int pvJoueur2 = joueur2.getPv();
        int primeJoueur1 = joueur1.getPrime();
        int primeJoueur2 = joueur2.getPrime();

        boolean pirateMort = pvJoueur1 <= 0 || pvJoueur2 <= 0;
        boolean primeAtteinte = primeJoueur1 >= PRIMEAATTEINDRE || primeJoueur2 >= PRIMEAATTEINDRE;

        return pirateMort || primeAtteinte;
    }

	public int getTourJoueur() {
		if (partie.getNbTour() % 2 == 1) return 1; 
		else return 2; 
 	}

	public void passerAuTourSuivant() {
		partie.setNbTour(partie.getNbTour() + 1);
		
	}

	public void initPartie() {
		joueur1.piocherCarte(4);
		joueur2.piocherCarte(4);
	}
	
	public List<Carte> getMain(int numJoueur) {
		if (numJoueur == 1) return joueur1.getMain(); 
		else return joueur2.getMain(); 
	}
	
	public Pirate getPirateNumJoueur(int numJoueur) {
		if(numJoueur == 1) return joueur1; 
		else return joueur2; 
	}
	

	public Pirate getPirateDuTour() {
		return getPirateNumJoueur(getTourJoueur()); 
	}
	
	public Pirate getAutrePirate() {
		if (getTourJoueur() == 1) return getPirateNumJoueur(2); 
		else return getPirateNumJoueur(1); 
	}

	public String afficherMain() {
		Pirate p = getPirateDuTour(); 
		return p.afficherMain(); 
	}

	public void jouerTourDeJeu() {
		// incrémenter le nombre de tour 
		partie.setNbTour(PRIMEAATTEINDRE);
		
	}

	public void afficherZoneDeCarte() {
		
	}

	public int getNumeroTour() {
		return partie.getNbTour(); 
	}
	
	public String afficherPirate() {
		return getPirateDuTour().afficherPirate();
	}

	public int getGagnant() {
		int gagnant;
		boolean gagnant1 = joueur2.getPv() <= 0 || joueur1.getPrime() >= PRIMEAATTEINDRE;
 		boolean gagnant2 = joueur1.getPv() <= 0 || joueur2.getPrime() >= PRIMEAATTEINDRE;
		if(gagnant2) {
			gagnant = 2;
		}
		else {
			gagnant = 1;
		}
		if(gagnant1 && gagnant2) {
			gagnant = 3;
		}
		return gagnant;
	}

	public void melangerDeck() {
		Pirate joueurCourant = getPirateDuTour();
		joueurCourant.melangerDeck();
		
	}


}
