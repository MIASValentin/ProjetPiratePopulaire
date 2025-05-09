package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Pirate;
import entities.Carte;
import entities.Deck;
import entities.TypeCarte;

public class TestPirate {
    private Pirate pirate;
    private List<Carte> cartesPioche;
    private final PrintStream standardOut = System.out;
    private ByteArrayOutputStream outputStream;

    private static class TestCarte extends Carte {
        public TestCarte(TypeCarte type, String nom, String description) {
            this.type = type;
            this.nom = nom;
            this.description = description;
        }
        @Override
        public String toString() {
            return nom;
        }
    }

    @BeforeEach
    void setUp() {
        pirate = new Pirate(5, 2, 1);
        cartesPioche = new ArrayList<>();
        cartesPioche.add(new TestCarte(TypeCarte.ATTAQUE, "A1", "Attaque 1"));
        cartesPioche.add(new TestCarte(TypeCarte.POPULARITE, "P1", "Popularité 1"));
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void testGettersAndSetters() {
        // PV, Prime, Num
        assertEquals(5, pirate.getPv());
        assertEquals(2, pirate.getPrime());
        assertEquals(1, pirate.getNum());

        pirate.setPv(8);
        pirate.setPrime(4);
        assertEquals(8, pirate.getPv());
        assertEquals(4, pirate.getPrime());

        // Bateau
        assertNull(pirate.getBateau());
        TestCarte bateau = new TestCarte(TypeCarte.BATEAU, "B1", "Bateau");
        pirate.setBateau(bateau);
        assertEquals(bateau, pirate.getBateau());

        // Main
        assertTrue(pirate.getMain().isEmpty());
        List<Carte> newMain = new ArrayList<>();
        newMain.add(bateau);
        pirate.setMain(newMain);
        assertEquals(newMain, pirate.getMain());

        // Deck
        assertTrue(pirate.getDeck().getCartes().isEmpty());
        //List<Carte> newDeck = new ArrayList<>();
        Deck newDeck = new Deck();
        newDeck.setCartes(new ArrayList<Carte>());
        pirate.setDeck(newDeck);
        assertEquals(newDeck, pirate.getDeck());
    }

    @Test
    void testChangerPvEtPrime() {
        pirate.changerPv(-3);
        pirate.changerPime(5);
        assertEquals(2, pirate.getPv());
        assertEquals(7, pirate.getPrime());
    }

    @Test
    void testPiocherCarte() {
        Deck pioche = new Deck();
        pioche.setCartes(new ArrayList<Carte>());
        pirate.getMain().clear();
        pirate.piocherCarte(1);
        assertEquals(1, pirate.getMain().size(), "Doit piocher une carte");
        // Piocher plus que disponible
        pirate.piocherCarte(5);
        assertEquals(cartesPioche.size(), pirate.getMain().size(), "Ne doit pas dépasser le nombre de cartes disponibles");
    }

    @Test
    void testAfficherMain() {
        pirate.getMain().clear();
        pirate.getMain().add(cartesPioche.get(0));
        pirate.getMain().add(cartesPioche.get(1));
        pirate.afficherMain();
        String[] lines = outputStream.toString().trim().split(System.lineSeparator());
        assertEquals("Main du pirate 1", lines[0]);
        assertEquals("-A1", lines[1]);
        assertEquals("-P1", lines[2]);
    }

    @Test
    void testAfficherPirate() {
        outputStream.reset();
        pirate.afficherPirate();
        String expected = "Pirate 1 | PV : 5 | Prime : 2";
        assertEquals(expected, outputStream.toString().trim());
    }
}
