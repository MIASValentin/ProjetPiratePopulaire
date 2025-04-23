package boundary;

import java.util.List;

import Control.ControlCreerDeck;
import Control.ControlDonnerCarte;
import InterfaceNF.ICarte;
import InterfaceNF.IPirate;

public class FrontiereDemarrerPartie {
	private ControlDonnerCarte controlDonnerCarte = new ControlDonnerCarte();
	private ControlCreerDeck controlCreerDeck = new ControlCreerDeck();
	private FrontiereTourDeJeu frontiereTourDeJeu;
	
	public FrontiereDemarrerPartie(ControlDonnerCarte controlDonnerCarte, ControlCreerDeck controlCreerDeck, FrontiereTourDeJeu frontiereTourDeJeu) {
	    this.controlDonnerCarte = controlDonnerCarte;
	    this.controlCreerDeck = controlCreerDeck;
	    this.frontiereTourDeJeu = frontiereTourDeJeu;
	}
	
	public void lancerPartie() {
		System.out.println("Bienvenue dans cette partie !");
		controlCreerDeck.creerDeck();
		controlDonnerCarte.donnerCarte(4);
		frontiereTourDeJeu.tourDeJeu();
	}
	
	public void afficherEtatJoueurs(IPirate joueur1, IPirate joueur2) {
        System.out.println("=== ÉTAT DES JOUEURS ===");
        System.out.printf("Joueur 1: %d vie | %d popularité\n", joueur1.getPointsVie(), joueur1.getPopularite());
        System.out.printf("Joueur 2: %d vie | %d popularité\n", joueur2.getPointsVie(), joueur2.getPopularite());
        System.out.println();
    }
	
	public void afficherMain(IPirate joueur) {
        System.out.println("Main de " + joueur.getNom() + " :");
        List<ICarte> main = joueur.getMain();
        for (int i = 0; i < main.size(); i++) {
            ICarte carte = main.get(i);
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
