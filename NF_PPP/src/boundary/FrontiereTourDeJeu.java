package boundary;

import Control.*;
import InterfaceNF.IPirate;
import entities.Pirate;

public class FrontiereTourDeJeu {

    private ControlPiocherCarte controlPiocherCarte;
    private ControlJouerCarte controlJouerCarte;
    private ControlCreerDeck controlCreerDeck;
    private ControlFinDeJeu controlFinDeJeu;
    private ControlChoisirBundle controlChoisirBundle;
    private ControlMelangerCarte controlMelangerCarte;
    private ControlEffetTour controlEffetTour;

    private FrontiereDemarrerPartie frontiereDemarrerPartie;
    private Pirate joueur1;
    private Pirate joueur2;

    public FrontiereTourDeJeu(
            ControlPiocherCarte controlPiocherCarte,
            ControlJouerCarte controlJouerCarte,
            ControlCreerDeck controlCreerDeck,
            ControlFinDeJeu controlFinDeJeu,
            ControlChoisirBundle controlChoisirBundle,
            ControlMelangerCarte controlMelangerCarte,
            ControlEffetTour controlEffetTour,
            FrontiereDemarrerPartie frontiereDemarrerPartie,
            Pirate joueur1,
            Pirate joueur2) 
    {
        this.controlPiocherCarte = controlPiocherCarte;
        this.controlJouerCarte = controlJouerCarte;
        this.controlCreerDeck = controlCreerDeck;
        this.controlFinDeJeu = controlFinDeJeu;
        this.controlChoisirBundle = controlChoisirBundle;
        this.controlMelangerCarte = controlMelangerCarte;
        this.controlEffetTour = controlEffetTour;
        this.frontiereDemarrerPartie = frontiereDemarrerPartie;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
    }

    public void tourDeJeu() {
        int tour = 1;

        while (!controlFinDeJeu.conditionsVictoire()) {
            System.out.println("TOUR " + tour + ":");

            Pirate joueurCourant = (tour % 2 == 1) ?  joueur1 : joueur2;

            //Phase de debut
            controlCreerDeck.creerBundle();
            controlChoisirBundle.selectionnerBundle();
            controlMelangerCarte.melangerDeck();
            controlPiocherCarte.piocherCarte(joueurCourant, 4);
            controlEffetTour.appliquerEffetDebut();

            int cartesJouees = 0;
            boolean bateauJoue = false;
            boolean finDuTour = false;

            while (!finDuTour) {
                System.out.println("\n--- Menu du tour ---");
                System.out.println("1. Afficher l'etat des joueurs");
                System.out.println("2. Afficher ma main");
                System.out.println("3. Jouer une carte (" + cartesJouees + "/2 jouees)");
                System.out.println("4. Jouer un bateau ");
                System.out.println("5. Passer le tour");

                int choix = Clavier.entrerEntier("Choix : ");

                switch (choix) {
                    case 1:
                        frontiereDemarrerPartie.afficherEtatJoueurs(joueur1, joueur2);
                        break;
                    case 2:
                        frontiereDemarrerPartie.afficherMain(joueurCourant);
                        break;
                    case 3:
                        if (cartesJouees < 2) {
                            controlJouerCarte.jouerCarte(joueurCourant);
                            controlPiocherCarte.piocherCarte(joueurCourant, 1);
                            cartesJouees++;
                        } else {
                            System.out.println("Vous avez deja joue 2 cartes.");
                        }
                        break;
                    case 4:
                        if (!bateauJoue && cartesJouees >= 2) {
                            controlJouerCarte.jouerBateau(joueurCourant);
                            bateauJoue = true;
                        } else if (bateauJoue) {
                            System.out.println("Le bateau a deja ete joue.");
                        } else {
                            System.out.println("Vous devez jouer 2 cartes avant de jouer un bateau.");
                        }
                        break;
                    case 5:
                        finDuTour = true;
                        break;
                    default:
                        System.out.println("Choix invalide.");
                }
            }

            //Phase de fin
            controlEffetTour.appliquerEffetFin();
            controlMelangerCarte.melangerMain();
            controlPiocherCarte.piocherCarte(joueurCourant, 4);
            tour++;
        }

        System.out.println("Le jeu est termine !");
        String gagnant = controlFinDeJeu.afficherGagnant();
        frontiereDemarrerPartie.afficherFin(gagnant);
    }
}
