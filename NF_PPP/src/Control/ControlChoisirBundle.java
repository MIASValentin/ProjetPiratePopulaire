package Control;

import entities.Pioche;
import entities.Pirate;
import entities.Carte;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ControlChoisirBundle {

    private Pioche pioche;

    public ControlChoisirBundle(Pioche pioche) {
        this.pioche = pioche;
        Stream<Carte> test;
    }

    public void selectionnerBundle(List<ArrayList<Carte>> bundles, Pirate pirate, int numChoix) {
        List<Carte> deck = pirate.getDeck();
        for(int i = 0; i<bundles.get(i).size(); i++) {
        	deck.add(bundles.get(numChoix).get(i));
        }
        pirate.setDeck(deck);
    }
}