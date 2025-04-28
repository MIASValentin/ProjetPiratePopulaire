package Control;

import entities.Pioche;
import entities.Pirate;
import entities.Carte;

public class ControlPiocherCarte {

    private Pioche pioche;
    private Pirate[] pirates; // Liste des pirates (joueurs)

    public ControlPiocherCarte(Pioche pioche, Pirate[] pirates) {
        this.pioche = pioche;
        this.pirates = pirates;
    }

    public void piocherCarte(int i) {
        if (i < 0 || i >= pirates.length) {
            System.out.println("Erreur : pirate inexistant.");
            return;
        }

        Carte cartePiochee = pioche.piocher();
        if (cartePiochee == null) {
            System.out.println("La pioche est vide !");
            return;
        }

        pirates[i].ajouterCarteMain(cartePiochee);
        System.out.println("Le pirate " + i + " a pioché : " + cartePiochee.getNom());
    }
}
