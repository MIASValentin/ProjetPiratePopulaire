package Control;

import entities.Pirate;
import entities.Carte;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ControlChoisirBundle {


    public ControlChoisirBundle() {
    }

    public void selectionnerBundle(List<ArrayList<Carte>> lBundle, Pirate joueur, int choix) {
        List<Carte> deck = joueur.getDeck();
        for(int i = 0; i<lBundle.get(i).size(); i++) {
        	deck.add(lBundle.get(choix).get(i));
        }
        joueur.setDeck(deck);
    }
}