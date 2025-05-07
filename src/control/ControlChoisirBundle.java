package control;

import java.util.ArrayList;
import java.util.List;

import entities.Carte;
import entities.Deck;
import entities.Pirate;

public class ControlChoisirBundle {

	public void selectionnerBundle(List<ArrayList<Carte>> lBundle, Pirate joueur, int choix) {
		Deck deck = joueur.getDeck();
        List<Carte> cartes = deck.getCartes();
        cartes.addAll(lBundle.get(choix));
        joueur.setDeck(deck);
    }
}
