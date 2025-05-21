package test;

import entities.Carte;
import entities.Deck;
import entities.EffetInstantane;
import entities.Pirate;
import entities.PirateMutant1;
import entities.TypeCarte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestPirateMutant1 {
    private PirateMutant1 pirate;

    @BeforeEach
    void setUp() {
        pirate = new PirateMutant1(10, 0, 1);
    }

    @Test
    void testInitialState() {
        assertEquals(10, pirate.getPv(), "PV initial doit être 10");
        assertEquals(0, pirate.getPrime(), "Prime initial doit être 0");
        assertEquals(1, pirate.getNum(), "Numéro de joueur doit être celui passé en constructeur");
        assertNotNull(pirate.getMain(), "La main ne doit pas être nulle");
        assertTrue(pirate.getMain().isEmpty(), "La main doit être vide au début");
        assertNotNull(pirate.getDeck(), "Le deck ne doit pas être nul");
    }

    @Test
    void testChangerPv() {
        pirate.changerPv(5);
        assertEquals(15, pirate.getPv(), "PV doit augmenter de 5");
        pirate.changerPv(-3);
        assertEquals(12, pirate.getPv(), "PV doit diminuer de 3");
    }

    @Test
    void testChangerPime() {
        pirate.changerPrime(4);
        assertEquals(4, pirate.getPrime(), "Prime doit augmenter de 4");
        pirate.changerPrime(-2);
        assertEquals(2, pirate.getPrime(), "Prime doit diminuer de 2");
    }

    @Test
    void testSetAndGetMain() {
        List<Carte> mains = Arrays.asList(
            new EffetInstantane(TypeCarte.SOUTIEN, "E1", "Desc1", (j1, j2) -> {}),
            new EffetInstantane(TypeCarte.ATTAQUE, "E2", "Desc2", (j1, j2) -> {})
        );
        pirate.setMain(new ArrayList<>(mains));
        assertEquals(mains, pirate.getMain(), "getMain doit retourner la liste passée à setMain");
    }

    @Test
    void testSetAndGetDeck() {
        Deck newDeck = new Deck();
        pirate.setDeck(newDeck);
        assertSame(newDeck, pirate.getDeck(), "getDeck doit retourner le deck défini par setDeck");
    }

    @Test
    void testPiocherCarteReducesDeckAndFillsMain() {
        Deck deckBefore = pirate.getDeck();
        int deckSize = deckBefore.getNbCarte();
        int draw = 5;
        pirate.piocherCarte(draw);
        assertEquals(draw, pirate.getMain().size(), "Main doit contenir exactement le nombre de cartes piochées");
        assertEquals(deckSize - draw, deckBefore.getNbCarte(), "Le deck doit perdre le nombre de cartes piochées");
    }

    @Test
    void testPiocherCarteOverdraw() {
        Deck deckBefore = pirate.getDeck();
        int deckSize = deckBefore.getNbCarte();
        int draw = deckSize + 5;
        pirate.piocherCarte(draw);
        assertEquals(deckSize, pirate.getMain().size(), "La main doit contenir le nombre de cartes initiales du deck");
        assertEquals(0, deckBefore.getNbCarte(), "Le deck doit être vide après avoir pioché toutes les cartes");
    }

    @Test
    void testAfficherPirate() {
        pirate.setPv(7);
        pirate.setPrime(3);
        String s = pirate.afficherPirate();
        assertEquals("Pirate 1 | PV : 7 | Prime : 3", s, "Le format doit être 'Pirate X | PV : Y | Prime : Z'");
    }

    @Test
    void testMelangerDeckKeepsSameCards() {
        Deck d = pirate.getDeck();
        List<Carte> before = new ArrayList<>(d.getCartes());
        pirate.melangerDeck();
        List<Carte> after = d.getCartes();
        assertEquals(before.size(), after.size(), "Le deck ne doit pas changer de taille après mélange");
        assertTrue(after.containsAll(before) && before.containsAll(after), "Le deck doit contenir les mêmes cartes après mélange");
        assertEquals(new HashSet<>(before).size(), new HashSet<>(after).size(), "Pas de duplicata après mélange");
    }
}
