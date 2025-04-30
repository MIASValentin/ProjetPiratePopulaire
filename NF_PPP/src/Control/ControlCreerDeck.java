package Control;

import entities.FabriqueDeCarte;
import entities.Pioche;
import entities.Carte;
import java.util.List;

public class ControlCreerDeck {

    private Pioche pioche;
    private FabriqueDeCarte fabrique;

    public ControlCreerDeck(Pioche pioche, FabriqueDeCarte fabrique) {
        this.pioche = pioche;
        this.fabrique = fabrique;
    }

    public void creerDeck() {
        List<Carte> nouvellesCartes = fabrique.creerCartes();
        pioche.getCartes().addAll(nouvellesCartes);
    }
}
