package Control;

import entities.Pirate;
import entities.Pioche;
import entities.Carte;
import java.util.List;

public class ControlDonnerCarte {

    private Pioche pioche;

    public ControlDonnerCarte(Pioche pioche) {
        this.pioche = pioche;
    }

    public void donnerCarte(Pirate pirate, int nbCartes) {
        for (int i = 0; i < nbCartes; i++) {
            Carte carte = pioche.donnerCarte();
            if (carte != null) {
                pirate.getMain().add(carte);
            }
        }
    }
}