package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.BiConsumer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.ZoneCarte;
import entities.EffetInstantane;
import entities.Pirate;
import entities.Carte;
import entities.TypeCarte;

public class TestZoneCarte {
    private ZoneCarte zone;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    private static class DummyCarte extends Carte {
        public DummyCarte() {
            this.type = TypeCarte.ATTAQUE;
            this.nom = "TestCarte";
            this.description = "Description Test";
        }
        @Override
        public String toString() {
            return this.nom;
        }
    }

    @BeforeEach
    public void setUp() {
        zone = new ZoneCarte();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testPutValidZones() {
        DummyCarte c1 = new DummyCarte();
        DummyCarte c2 = new DummyCarte();
        assertTrue(zone.put(1, c1), "put doit retourner true pour zone 1");
        assertTrue(zone.put(2, c2), "put doit retourner true pour zone 2");
    }

    @Test
    public void testPutInvalidZone() {
        DummyCarte c = new DummyCarte();
        assertFalse(zone.put(0, c), "put doit retourner false pour zone invalide 0");
        assertFalse(zone.put(3, c), "put doit retourner false pour zone invalide 3");
    }

    @Test
    public void testApplyEffetInstantaneAppliesBiConsumer() {
        Pirate p1 = new Pirate(5, 0, 1);
        Pirate p2 = new Pirate(5, 0, 2);
        BiConsumer<Pirate, Pirate> effect = (j1, j2) -> j1.setPrime(j1.getPrime() + 3);
        EffetInstantane ei = new EffetInstantane(TypeCarte.ATTAQUE, "EffetTest", "DescEffet", effect);
        zone.applyEffetInstantane(ei, p1, p2);
        assertEquals(3, p1.getPrime(), "applyEffetInstantane doit augmenter la prime de p1 de 3");
        assertEquals(0, p2.getPrime(), "applyEffetInstantane ne doit pas modifier p2 si non ciblé");
    }

    @Test
    public void testAfficherZoneDeJeuEmpty() {
        zone.afficherZoneDeJeu(1);
        String output = outContent.toString().trim();
        assertTrue(output.contains("===== Zone de Jeu ====="), "doit afficher l'en-tête de la zone");
        assertTrue(output.contains("La zone de jeu est vide."), "doit indiquer que la zone est vide");
    }

    @Test
    public void testAfficherZoneDeJeuNonEmpty() {
        DummyCarte c1 = new DummyCarte();
        DummyCarte c2 = new DummyCarte();
        zone.put(2, c1);
        zone.put(2, c2);
        zone.afficherZoneDeJeu(2);
        String output = outContent.toString().trim();
        String[] lines = output.split(System.lineSeparator());
        assertEquals("===== Zone de Jeu =====", lines[0].trim(), "la première ligne doit être l'en-tête");
        assertEquals("TestCarte", lines[1].trim(), "doit afficher le nom de la première carte");
        assertEquals("TestCarte", lines[2].trim(), "doit afficher le nom de la deuxième carte");
    }
}
