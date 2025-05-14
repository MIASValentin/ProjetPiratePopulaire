package test;

import entities.Deck;
import entities.Carte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestDeck {
    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    @Test
    void testInitialCarteCount() {
        assertEquals(12, deck.getNbCarte(), "Le deck initial doit contenir 12 cartes");
        assertEquals(12, deck.getCartes().size(), "La liste cartes doit être de taille 12");
    }

    @Test
    void testSetAndGetCartes() {
        List<Carte> newCartes = new ArrayList<>();
        deck.setCartes(newCartes);
        assertSame(newCartes, deck.getCartes(), "getCartes doit retourner la liste définie par setCartes");
        assertEquals(0, deck.getNbCarte(), "Le nombre de cartes doit refléter setCartes");
    }

    @Test
    void testDonnerCarte_removesCarteAndReturnsIt() {
        int initialSize = deck.getNbCarte();
        Carte c = deck.donnerCarte();
        assertNotNull(c, "donnerCarte ne doit pas retourner null tant que le deck n'est pas vide");
        assertEquals(initialSize - 1, deck.getNbCarte(), "Le nombre de cartes doit diminuer après donnerCarte");
        assertFalse(deck.getCartes().contains(c), "La carte retournée doit être supprimée du deck");
    }

    @Test
    void testDonnerCarte_whenEmpty() {
        int initialSize = deck.getNbCarte();
        for (int i = 0; i < initialSize; i++) {
            assertNotNull(deck.donnerCarte(), "Chaque appel doit retourner une carte jusqu'à épuisement");
        }
        assertEquals(0, deck.getNbCarte(), "Le deck doit être vide après avoir retiré toutes les cartes");
        assertNull(deck.donnerCarte(), "donnerCarte doit retourner null si le deck est vide");
    }

    @Test
    void testMelangerCartes_keepsSameCards() {
        List<Carte> original = new ArrayList<>(deck.getCartes());
        deck.melangerCartes();
        List<Carte> shuffled = deck.getCartes();
        assertEquals(12, shuffled.size(), "Le deck doit toujours contenir 12 cartes après melangerCartes");
        assertTrue(shuffled.containsAll(original), "Le deck après shuffle doit contenir toutes les cartes originales");
        assertEquals(new HashSet<>(original).size(), new HashSet<>(shuffled).size(),
            "Pas de duplicata après melangerCartes");
    }

    @Test
    void testCreerBundle_cardCountsAndBundles() {
        int nbCartesBundle = 2;
        int nbBundle = 3;
        var bundles = deck.creerBundle(nbCartesBundle, nbBundle);
        assertEquals(nbBundle, bundles.size(), "Le nombre de bundles doit correspondre au param nbBundle");
        int total = bundles.stream().mapToInt(List::size).sum();
        assertEquals(nbCartesBundle * nbBundle, total,
            "Le nombre total de cartes dans les bundles doit être nbCartesBundle*nbBundle");
        bundles.forEach(b -> assertNotNull(b, "Chaque bundle doit être non-nul"));
    }
}
