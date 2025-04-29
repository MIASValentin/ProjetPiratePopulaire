package Control;

import entities.Pioche;
import entities.Pirate;
import java.util.Collections;
import java.util.List;

public class ControlMelangerCarte {
    private Pioche pioche;
    private Pirate[] pirates;

    public ControlMelangerCarte(Pioche pioche, Pirate[] pirates) {
        this.pioche = pioche;
        this.pirates = pirates;
    }

    /**
     * Mélange la pioche principale.
     */
    public void melangerDeck() {
        Collections.shuffle(pioche.getCartes());
    }

    /**
     * Mélange la main de chaque pirate en fin de tour.
     */
    public void melangerMain() {
        for (Pirate p : pirates) {
            Collections.shuffle(p.getMain());
        }
    }
}
