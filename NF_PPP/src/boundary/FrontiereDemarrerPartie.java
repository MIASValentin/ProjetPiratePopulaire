package boundary;

import java.util.List;

import Control.ControlCreerDeck;
import Control.ControlDonnerCarte;
import InterfaceNF.ICarte;
import InterfaceNF.IPirate;
import entities.Carte;
import entities.Pirate;

public class FrontiereDemarrerPartie {
	private ControlDonnerCarte controlDonnerCarte;
	private ControlCreerDeck controlCreerDeck;
	private FrontiereTourDeJeu frontiereTourDeJeu;
    private Pirate joueur1;
    private Pirate joueur2;
	
	public FrontiereDemarrerPartie(ControlDonnerCarte controlDonnerCarte, ControlCreerDeck controlCreerDeck, FrontiereTourDeJeu frontiereTourDeJeu) {
	    this.controlDonnerCarte = controlDonnerCarte;
	    this.controlCreerDeck = controlCreerDeck;
	    this.frontiereTourDeJeu = frontiereTourDeJeu;
	}
	
	public void lancerPartie() {
		System.out.println("Bienvenue dans cette partie !");
		controlCreerDeck.creerDeck();
		// au lieu de controlDonnerCarte.donnerCarte(4);
		controlDonnerCarte.donnerCarte(joueur1, 4);
		controlDonnerCarte.donnerCarte(joueur2, 4);
		frontiereTourDeJeu.tourDeJeu();
	}
	
    public void afficherEtatJoueurs(Pirate joueur1, Pirate joueur2) {
        System.out.println("=== ÉTAT DES JOUEURS ===");
        System.out.printf("Pirate 1 : %d PV | %d prime%n",
                           joueur1.getPv(), joueur1.getPrime());
        System.out.printf("Pirate 2 : %d PV | %d prime%n",
        		joueur2.getPv(), joueur2.getPrime());
        System.out.println();
    }
	
	public void afficherMain(Pirate joueur) {
        System.out.println("Main de " + joueur.getNom() + " :");
        List<Carte> main = joueur.getMain();
        for (int i = 0; i < main.size(); i++) {
            Carte carte = main.get(i);
            System.out.printf("[%d] %s : %s (%s)\n", i + 1, carte.getNom(), carte.getDescription(), carte.getEffet());
        }
    }
	
	public int demanderChoixCarte() {
        int choix;
        do {
            choix = Clavier.entrerEntier("Choisissez une carte à jouer (1-4) : ");
        } while (choix < 1 || choix > 4);
        return choix - 1;
    }
	
	public void afficherFin(String gagnant) {
        System.out.println("\n Le jeu est terminé ! Le gagnant est : " + gagnant + " !");
    }
}
