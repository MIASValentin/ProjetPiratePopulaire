package test;

import control.ControlPartie;
import entities.Partie;
import entities.Pirate;
import entities.Carte;
import entities.TypeCarte;
import entities.EffetInstantane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestControlPartie {
    private ControlPartie controlPartie;
    private Pirate pirate1;
    private Pirate pirate2;
    private Partie partie;

    @BeforeEach
    void setUp() {
        // Initialisation de deux pirates avec PV à 10 et prime à 0
        pirate1 = new Pirate(10, 0, 1);
        pirate2 = new Pirate(10, 0, 2);
        partie = new Partie(pirate1, pirate2);
        controlPartie = new ControlPartie(partie);
    }

    @Test
    void testEstPartieFini_initial() {
        assertFalse(controlPartie.estPartieFini(), "La partie ne doit pas être finie au démarrage");
    }

    @Test
    void testEstPartieFini_pvZero() {
        pirate1.setPv(0);
        assertTrue(controlPartie.estPartieFini(), "La partie devrait être finie si un pirate a 0 PV");
    }

    @Test
    void testEstPartieFini_primeAtteinte() {
        pirate2.setPrime(5);
        assertTrue(controlPartie.estPartieFini(), "La partie devrait être finie si un pirate atteint la prime de 5");
    }

    @Test
    void testGetTourJoueur_initial() {
        // nbTour initial = 0 (pair)
        assertEquals(2, controlPartie.getTourJoueur(), "Tour 2 pour un nombre de tours pair");
    }

    @Test
    void testGetTourJoueur_afterOneTour() {
        partie.setNbTour(1);
        assertEquals(1, controlPartie.getTourJoueur(), "Tour 1 pour un nombre de tours impair");
    }

    @Test
    void testPasserAuTourSuivant() {
        int initial = partie.getNbTour();
        controlPartie.passerAuTourSuivant();
        assertEquals(initial + 1, partie.getNbTour(), "La méthode doit incrémenter le nombre de tours");
    }

    @Test
    void testInitPartie() {
        controlPartie.initPartie();
        assertEquals(4, pirate1.getMain().size(), "Chaque joueur doit piocher 4 cartes");
        assertEquals(4, pirate2.getMain().size(), "Chaque joueur doit piocher 4 cartes");
    }

    @Test
    void testGetMain_player() {
        controlPartie.initPartie();
        List<Carte> main1 = controlPartie.getMain(1);
        List<Carte> main2 = controlPartie.getMain(2);
        assertEquals(pirate1.getMain(), main1, "Récupération de la main du joueur 1");
        assertEquals(pirate2.getMain(), main2, "Récupération de la main du joueur 2");
    }

    @Test
    void testGetPirateNumJoueur() {
        assertEquals(pirate1, controlPartie.getPirateNumJoueur(1));
        assertEquals(pirate2, controlPartie.getPirateNumJoueur(2));
    }

    @Test
    void testGetPirateDuTour_and_getAutrePirate() {
        partie.setNbTour(1);
        assertEquals(pirate1, controlPartie.getPirateDuTour(), "Le pirate du tour impair doit être le joueur 1");
        assertEquals(pirate2, controlPartie.getAutrePirate(), "L'autre pirate doit être le joueur 2");
    }

    @Test
    void testAfficherMain() {
        // Préparation : vider la main puis piocher
        pirate1.getMain().clear();
        controlPartie.initPartie();
        partie.setNbTour(1);
        String affichage = controlPartie.afficherMain();
        assertTrue(affichage.contains("Main du pirate 1"), "L'affichage doit mentionner le rôle et numéro du pirate");
        assertTrue(affichage.split("\n").length >= 2, "L'affichage doit lister au moins une carte");
    }

    @Test
    void testGetNumeroTour() {
        partie.setNbTour(7);
        assertEquals(7, controlPartie.getNumeroTour(), "Le numéro de tour doit correspondre à l'état de la partie");
    }

    @Test
    void testGetGagnant_variousScenarios() {
        // Victoire par prime du joueur 1
        pirate1.setPrime(5);
        assertEquals(1, controlPartie.getGagnant(), "Le joueur 1 atteint la prime => gagnant 1");

        // Réinitialiser
        setUp();
        // Victoire par PV à 0 du joueur 2
        pirate2.setPv(0);
        assertEquals(1, controlPartie.getGagnant(), "Le joueur 2 à 0 PV => gagnant 1");

        // Égalité par deux victoires simultanées
        setUp();
        pirate1.setPv(0);
        pirate2.setPv(0);
        assertEquals(3, controlPartie.getGagnant(), "égalité (3)");
    }

    @Test
    void testMelangerDeck_noException() {
        partie.setNbTour(1);
        assertDoesNotThrow(() -> controlPartie.melangerDeck(), "Le mélange du deck ne doit pas lancer d'exception");
    }
}
