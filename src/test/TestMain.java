package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Main;
import entities.Pirate;
import entities.Pioche;
import entities.ZoneCarte;

public class TestMain {
    private Pirate player1;
    private Pirate player2;
    private Pioche pioche;
    private ZoneCarte zone;
    private final PrintStream standardOut = System.out;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        player1 = new Pirate(5, 0, 1);
        player2 = new Pirate(5, 0, 2);
        pioche = new Pioche(new ArrayList<>());
        zone = new ZoneCarte();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testJouerTour_Player1DeadAtStart() {
        player1.setPv(0);
        boolean result = Main.jouer_tour(player1, player2, pioche, zone, 1);
        assertTrue(result, "Si le joueur 1 est mort, jouer_tour doit renvoyer true");
        String out = outputStream.toString();
        assertTrue(out.contains("Pirate 1 veuillez jouer"), "Doit demander au pirate 1 de jouer");
        assertTrue(out.contains("Le pirate 1 est mort, le pirate 2 gagne"), "Doit annoncer la mort du pirate 1");
    }

    @Test
    public void testJouerTour_Player2DeadAtStart() {
        player2.setPv(0);
        boolean result = Main.jouer_tour(player1, player2, pioche, zone, 1);
        assertTrue(result, "Si le joueur 2 est mort, jouer_tour doit renvoyer true");
        String out = outputStream.toString();
        assertTrue(out.contains("Pirate 1 veuillez jouer"), "Doit demander au pirate 1 de jouer");
        assertTrue(out.contains("Le pirate 2 est mort, le pirate 1 gagne"), "Doit annoncer la mort du pirate 2");
    }

    @Test
    public void testJouerTour_ValidTurn_Pirate1() {
        boolean result = Main.jouer_tour(player1, player2, pioche, zone, 1);
        assertFalse(result, "Si aucun pirate mort, jouer_tour doit renvoyer false");
        String out = outputStream.toString();
        assertTrue(out.contains("Pirate 1 veuillez jouer"), "Doit demander au pirate 1 de jouer");
        assertTrue(out.contains("Pirate 1 | PV : 5 | Prime : 0"), "Doit afficher l'état du pirate 1");
        assertTrue(out.contains("Main du pirate 1"), "Doit afficher la main du pirate 1");
    }

    @Test
    public void testJouerTour_ValidTurn_Pirate2() {
        outputStream.reset();
        boolean result = Main.jouer_tour(player1, player2, pioche, zone, 2);
        assertFalse(result, "Si aucun pirate mort et c'est au tour du pirate 2, jouer_tour doit renvoyer false");
        String out = outputStream.toString();
        assertTrue(out.contains("Pirate 2 veuillez jouer"), "Doit demander au pirate 2 de jouer");
        assertTrue(out.contains("Pirate 1 | PV : 5 | Prime : 0"), "Doit afficher l'état du pirate 1 au début du tour");
        assertTrue(out.contains("Main du pirate 2"), "Doit afficher la main du pirate 2");
    }

    @Test
    public void testJouerTour_InvalidTurnNumber_ActsAsPlayer2() {
        outputStream.reset();
        boolean result = Main.jouer_tour(player1, player2, pioche, zone, 3);
        assertFalse(result, "tour_joueur autre que 1 doit être traité comme pour le joueur 2");
        String out = outputStream.toString();
        assertTrue(out.contains("Pirate 3 veuillez jouer"), "Doit afficher Pirate 3 veuillez jouer");
        assertTrue(out.contains("Main du pirate 2"), "Doit afficher la main du pirate 2");
    }

    @Test
    public void testJouerTour_NullArguments() {
        assertAll(
            () -> assertThrows(NullPointerException.class, () -> Main.jouer_tour(null, player2, pioche, zone, 1)),
            () -> assertThrows(NullPointerException.class, () -> Main.jouer_tour(player1, null, pioche, zone, 1)),
            () -> assertThrows(NullPointerException.class, () -> Main.jouer_tour(player1, player2, pioche, null, 1))
        );
    }
}
