package control;

import java.util.ArrayList;
import java.util.List;

import entities.Carte;
import entities.Deck;
import entities.Pirate;

public class ControlChoisirBundle {
	private Deck deck;
	public static final int NB_CARTES_BUNDLE = 3;
	public static final int NB_BUNDLES = 3;
	
	public ControlChoisirBundle(Deck deck) {
		this.deck = deck;
	}

	public void selectionnerBundle(List<ArrayList<Carte>> lBundle, Pirate joueur, int choix) {
		Deck deckJoueur = joueur.getDeck();
        List<Carte> cartes = deckJoueur.getCartes();
        cartes.addAll(lBundle.get(choix));
        joueur.setDeck(deckJoueur);
    }
	
	public List<ArrayList<Carte>> getBundles() {
		return deck.creerBundle(NB_CARTES_BUNDLE,NB_BUNDLES);
	}
}
