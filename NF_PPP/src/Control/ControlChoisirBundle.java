package Control;

import entities.Pioche;
import entities.Pirate;
import entities.Carte;
import java.util.ArrayList;
import java.util.List;

public class ControlChoisirBundle {

    private Pioche pioche;

    public ControlChoisirBundle(Pioche pioche) {
        this.pioche = pioche;
    }

    public void selectionnerBundle(Pirate pirate, int nbCartes) {
        List<Carte> bundle = new ArrayList<>();
        for (int i = 0; i < nbCartes; i++) {
            Carte carte = pioche.donnerCarte();
            if (carte != null) {
                bundle.add(carte);
            }
        }
        pirate.getDeck().addAll(bundle);
    }
}