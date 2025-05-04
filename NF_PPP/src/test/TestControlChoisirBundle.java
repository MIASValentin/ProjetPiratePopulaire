package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Control.ControlChoisirBundle;
import entities.Pirate;
import entities.Carte;
import entities.TypeCarte;

/**
 * Tests unitaires pour ControlChoisirBundle
 */
class TestControlChoisirBundle {
    private ControlChoisirBundle control;
    private Pirate pirate;
    private List<ArrayList<Carte>> bundles;

    /**
     * DummyCarte pour instancier facilement des Cartes
     */
    private static class DummyCarte extends Carte {
        DummyCarte(TypeCarte type, String nom) {
            this.type = type;
            this.nom  = nom;
            this.description = "";
        }
        @Override
        public String toString() { return nom; }
    }

    @BeforeEach
    void setUp() {
        control = new ControlChoisirBundle();
        pirate = new Pirate(5, 0, 1);
        // deck de base avec une carte
        pirate.getDeck().clear();
        pirate.getDeck().add(new DummyCarte(TypeCarte.ATTAQUE, "Base"));

        bundles = new ArrayList<>();
    }

    @Test
    void testSelectionnerBundle_Normal() {
        // bundle0 de deux cartes, bundle1 d'une carte
        ArrayList<Carte> bundle0 = new ArrayList<>();
        bundle0.add(new DummyCarte(TypeCarte.BATEAU,     "B1"));
        bundle0.add(new DummyCarte(TypeCarte.POPULARITE, "P1"));
        ArrayList<Carte> bundle1 = new ArrayList<>();
        bundle1.add(new DummyCarte(TypeCarte.ATTAQUE,    "A1"));
        bundles.add(bundle0);
        bundles.add(bundle1);

        control.selectionnerBundle(bundles, pirate, 0);
        List<Carte> deck = pirate.getDeck();

        // deck initial + 2 cartes du bundle
        assertEquals(1 + bundle0.size(), deck.size(),
            "Le deck doit s'agrandir du nombre de cartes du bundle sélectionné");
        assertTrue(deck.containsAll(bundle0),
            "Le deck doit contenir toutes les cartes du bundle 0");
    }

    @Test
    void testSelectionnerBundle_EmptyBundle() {
        ArrayList<Carte> empty = new ArrayList<>();
        bundles.add(empty);

        control.selectionnerBundle(bundles, pirate, 0);
        // deck reste inchangé
        assertEquals(1, pirate.getDeck().size(),
            "Le deck ne doit pas changer si le bundle est vide");
    }

    @Test
    void testSelectionnerBundle_InvalidChoice_Negative() {
        bundles.add(new ArrayList<>());
        assertThrows(IndexOutOfBoundsException.class,
            () -> control.selectionnerBundle(bundles, pirate, -1),
            "Un choix négatif doit lancer IndexOutOfBoundsException");
    }

    @Test
    void testSelectionnerBundle_InvalidChoice_TooHigh() {
        bundles.add(new ArrayList<>());
        assertThrows(IndexOutOfBoundsException.class,
            () -> control.selectionnerBundle(bundles, pirate, bundles.size()),
            "Un choix hors borne doit lancer IndexOutOfBoundsException");
    }

    @Test
    void testSelectionnerBundle_NullBundles() {
        assertThrows(NullPointerException.class,
            () -> control.selectionnerBundle(null, pirate, 0),
            "Bundles null doit lancer NullPointerException");
    }

    @Test
    void testSelectionnerBundle_NullPirate() {
        ArrayList<Carte> bundle = new ArrayList<>();
        bundles.add(bundle);
        assertThrows(NullPointerException.class,
            () -> control.selectionnerBundle(bundles, null, 0),
            "Pirate null doit lancer NullPointerException");
    }
}
