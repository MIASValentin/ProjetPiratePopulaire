package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import control.ControlChoisirBundle;
import entities.Carte;
import entities.Deck;
import entities.Pirate;

public class TestControlChoisirBundle {
	private Deck sourceDeck;
    private ControlChoisirBundle control;
    private Pirate pirate;

    @BeforeEach
    void setUp() {
        sourceDeck = new Deck();
        control = new ControlChoisirBundle(sourceDeck);

        // Exemple : 10 points de vie, 0 prime, ID 1
        pirate = new Pirate(10, 0, 1);
        pirate.setDeck(new Deck());
        pirate.getDeck().setCartes(new ArrayList<>());
    }

    @Test
    void testGetBundlesRetourneStructureCorrecte() {
        List<ArrayList<Carte>> bundles = control.getBundles();

        assertEquals(3, bundles.size(), "Devrait retourner 3 paquets de cartes");

        for (ArrayList<Carte> bundle : bundles) {
            assertEquals(3, bundle.size(), "Chaque paquet devrait contenir 3 cartes");
        }
    }

    @Test
    void testSelectionnerBundleAjouteCartesAuDeckDuPirate() {
        List<ArrayList<Carte>> bundles = control.getBundles();
        int tailleInitiale = pirate.getDeck().getCartes().size();

        control.selectionnerBundle(bundles, pirate, 1); // choisir le bundle à l'index 1

        int nouvelleTaille = pirate.getDeck().getCartes().size();
        assertEquals(tailleInitiale + 3, nouvelleTaille, "Le deck du pirate devrait contenir 3 cartes supplémentaires après la sélection");

        List<Carte> cartesPirate = pirate.getDeck().getCartes();
        assertTrue(cartesPirate.containsAll(bundles.get(1)), "Le deck du pirate devrait contenir toutes les cartes du bundle sélectionné");
    }
}
