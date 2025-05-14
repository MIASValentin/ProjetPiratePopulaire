package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import entities.*;

/**
 * Tests unitaires pour la classe Deck couvrant une stratégie robuste & forte :
 * - États du deck (null, vide, taille=1, deck par défaut)
 * - Méthodes getNbCarte, getCartes, setCartes, creerBundle, donnerCarte
 */
public class TestDeck {

    /** Sous-classe concrète de Carte pour tests personnalisés */
    private static class DummyCarte extends Carte {
        DummyCarte(TypeCarte type, String nom, String description) {
            this.type = type;
            this.nom = nom;
            this.description = description;
        }
    }

    /**
     * État par défaut : deck initial de 12 cartes
     */
    @Test
    void testDefaultDeckProperties() {
        Deck deck = new Deck();
        assertNotNull(deck.getCartes(), "La liste de cartes ne doit pas être nulle");
        assertEquals(12, deck.getNbCarte(), "Le deck par défaut doit contenir 12 cartes");
    }

    /**
     * Couverture forte & robuste de creerBundle() sur deck par défaut
     */
    @Test
    void testCreerBundleDefaultDeck() {
        Deck deck = new Deck();
        List<ArrayList<Carte>> bundles = deck.creerBundle(3,3);
        assertEquals(3, bundles.size(), "Il doit y avoir 3 bundles");
        for (List<Carte> bundle : bundles) {
            assertEquals(3, bundle.size(), "Chaque bundle doit contenir 3 cartes");
            for (Carte c : bundle) {
                assertNotNull(c, "Chaque carte dans le bundle ne doit pas être nulle");
            }
        }
    }

    /**
     * Donner carte depuis un deck non vide (taille>1)
     */
    @Test
    void testDonnerCarteDefaultDeck() {
        Deck deck = new Deck();
        int initialSize = deck.getNbCarte();
        Carte drawn = deck.donnerCarte();
        assertNotNull(drawn, "Une carte doit être retournée pour un deck non vide");
        assertEquals(initialSize - 1, deck.getNbCarte(), "Le deck doit perdre une carte");
    }

    /**
     * Donner carte depuis un deck à un seul élément
     */
    @Test
    void testDonnerCarteSingleCard() {
        Deck deck = new Deck();
        DummyCarte dummy = new DummyCarte(TypeCarte.ATTAQUE, "N", "D");
        List<Carte> list = new ArrayList<>();
        list.add(dummy);
        deck.setCartes(list);
        assertEquals(1, deck.getNbCarte(), "Le deck doit contenir exactement 1 carte avant tirage");
        Carte drawn = deck.donnerCarte();
        assertSame(dummy, drawn, "La carte retournée doit être celle présente");
        assertEquals(0, deck.getNbCarte(), "Le deck doit être vide après un tirage");
    }

    /**
     * Tirer une carte d'un deck vide
     */
    @Test
    void testDonnerCarteEmptyDeck() {
        Deck deck = new Deck();
        deck.setCartes(new ArrayList<>());
        assertEquals(0, deck.getNbCarte(), "Le deck doit être vide");
        assertNull(deck.donnerCarte(), "Null attendu pour un deck vide");
    }

    /**
     * Setter de cartes à null et comportement en aval
     */
    @Test
    void testSetCartesNull() {
        Deck deck = new Deck();
        deck.setCartes(null);
        assertThrows(NullPointerException.class, () -> deck.getNbCarte(),
            "Une NullPointerException est attendue si la liste de cartes est null");
    }
}
