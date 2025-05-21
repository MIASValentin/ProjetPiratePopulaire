package test;

import control.ControlChoisirBundle;
import entities.Carte;
import entities.Deck;
import entities.Pirate;
import entities.TypeCarte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControlChoisirBundleTest {

    private ControlChoisirBundle controller;
    private List<List<Carte>> fakeBundles;

    /**
     * Stub de Deck pour injecter des bundles factices sans dépendre de l'implémentation réelle.
     */
    private static class FakeDeck extends Deck {
        private final List<List<Carte>> bundles;

        FakeDeck(List<List<Carte>> bundles) {
            super();
            this.bundles = bundles;
        }

        @Override
        public List<ArrayList<Carte>> creerBundle(int nbCartes, int nbBundles) {
            List<ArrayList<Carte>> result = new ArrayList<>();
            for (List<Carte> b : bundles) {
                result.add(new ArrayList<>(b));
            }
            return result;
        }
    }

    /**
     * Classe de test concrète pour Carte (abstraite dans le projet).
     */
    private static class FakeCarte extends Carte {
        FakeCarte(String nom) {
            this.type = TypeCarte.ATTAQUE;
            this.nom = nom;
            this.description = "";
        }
    }

    @BeforeEach
    void setUp() {
        // Préparation de bundles factices
        fakeBundles = new ArrayList<>();
        for (int i = 0; i < ControlChoisirBundle.NB_BUNDLES; i++) {
            List<Carte> bundle = new ArrayList<>();
            for (int j = 0; j < ControlChoisirBundle.NB_CARTES_BUNDLE; j++) {
                bundle.add(new FakeCarte("C" + i + j));
            }
            fakeBundles.add(bundle);
        }
        // Création du contrôleur avec stub Deck
        controller = new ControlChoisirBundle(new FakeDeck(fakeBundles));
    }

    @Test
    void testGetBundles() {
        List<ArrayList<Carte>> bundles = controller.getBundles();

        assertNotNull(bundles, "getBundles ne doit pas retourner null");
        assertEquals(ControlChoisirBundle.NB_BUNDLES, bundles.size(),
                "Doit générer exactement NB_BUNDLES bundles");
        for (int i = 0; i < bundles.size(); i++) {
            assertEquals(ControlChoisirBundle.NB_CARTES_BUNDLE, bundles.get(i).size(),
                    "Chaque bundle doit contenir NB_CARTES_BUNDLE cartes");
            assertTrue(bundles.get(i).containsAll(fakeBundles.get(i)),
                    "Le bundle retourné doit correspondre au bundle factice");
        }
    }

    @Test
    void testSelectionnerBundleValid() {
        Pirate pirate = new Pirate(10, 0, 1);
        Deck initialDeck = new Deck();
        pirate.setDeck(initialDeck);

        assertFalse(pirate.getDeck().getCartes().isEmpty(), "Deck initial ne doit pas être vide");

        controller.selectionnerBundle(controller.getBundles(), pirate, 1);

        List<Carte> cartesJoueur = pirate.getDeck().getCartes();
    }

    @Test
    void testSelectionnerBundleNullListThrows() {
        Pirate pirate = new Pirate(10, 0, 1);
        pirate.setDeck(new Deck());
        assertThrows(NullPointerException.class,
                () -> controller.selectionnerBundle(null, pirate, 0),
                "Doit lever NullPointerException si la liste de bundles est nulle");
    }

    @Test
    void testSelectionnerBundleIndexOutOfRangeThrows() {
        Pirate pirate = new Pirate(10, 0, 1);
        pirate.setDeck(new Deck());

        List<ArrayList<Carte>> bundles = controller.getBundles();
        assertThrows(IndexOutOfBoundsException.class,
                () -> controller.selectionnerBundle(bundles, pirate, -1),
                "Doit lever IndexOutOfBoundsException pour indice négatif");
        assertThrows(IndexOutOfBoundsException.class,
                () -> controller.selectionnerBundle(bundles, pirate, bundles.size()),
                "Doit lever IndexOutOfBoundsException pour indice hors borne");
    }
}
