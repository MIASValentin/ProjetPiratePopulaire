package Control;

import entities.Pioche;
import entities.Pirate;
import entities.Carte;

public class ControlPiocherCarte {
    private Pioche pioche;

    public ControlPiocherCarte(Pioche pioche) {
        this.pioche = pioche;
    }

    /**
     * Permet à un pirate de piocher un certain nombre de cartes.
     */
    public void piocherCarte(Pirate pirate, int nbCartes) {
        for (int i = 0; i < nbCartes; i++) {
            Carte carte = pioche.donnerCarte();
            if (carte == null) {
                System.out.println("La pioche est vide !");
                break;
            }
            pirate.getMain().add(carte);
        }
    }
}