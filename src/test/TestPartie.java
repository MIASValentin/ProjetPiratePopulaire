package test;

import entities.Partie;
import entities.Pirate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestPartie {

    private Pirate pirate1;
    private Pirate pirate2;
    private Partie partie;

    @BeforeEach
    void setUp() {
        // Crée deux pirates avec des valeurs par défaut
        pirate1 = new Pirate(10, 0, 1);
        pirate2 = new Pirate(10, 0, 2);

        // Initialise la partie avec les deux pirates
        partie = new Partie(pirate1, pirate2);
    }

    @Test
    void testInitialNbTour() {
        assertEquals(0, partie.getNbTour(), "Le nombre de tours initial devrait etre 0.");
    }

    @Test
    void testSetNbTour() {
        partie.setNbTour(5);
        assertEquals(5, partie.getNbTour(), "Le nombre de tours devrait etre mis a jour a 5.");
    }

    @Test
    void testGetJoueur1() {
        assertEquals(pirate1, partie.getJoueur(1), "getJoueur(1) doit retourner pirate1.");
    }

    @Test
    void testGetJoueur2() {
        assertEquals(pirate2, partie.getJoueur(2), "getJoueur(2) doit retourner pirate2.");
    }
}