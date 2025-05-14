package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import control.ControlPartie;
import entities.Carte;
import entities.EffetInstantane;
import entities.Partie;
import entities.Pirate;
import entities.TypeCarte;
import entities.ZoneCarte;

public class TestControlPartie {
	private Pirate pirate1;
    private Pirate pirate2;
    private ZoneCarte zoneCarte;
    private Partie partie;
    private ControlPartie controlPartie;

    @BeforeEach
    void setUp() {
        pirate1 = new Pirate(10, 0, 1);
        pirate2 = new Pirate(10, 0, 2);
        zoneCarte = new ZoneCarte();

        partie = new Partie(pirate1, pirate2, zoneCarte);
        partie.setNbTour(1);
        controlPartie = new ControlPartie(partie);
    }

    @Test
    void testEstPartieFini_falseWhenAllNormal() {
        pirate1.setPv(10);
        pirate2.setPv(10);
        pirate1.setPrime(0);
        pirate2.setPrime(0);
        assertFalse(controlPartie.estPartieFini());
    }

    @Test
    void testEstPartieFini_pirate1Dead() {
        pirate1.setPv(0);
        assertTrue(controlPartie.estPartieFini());
    }

    @Test
    void testEstPartieFini_pirate2Dead() {
        pirate2.setPv(0);
        assertTrue(controlPartie.estPartieFini());
    }

    @Test
    void testEstPartieFini_primeJoueur1Atteinte() {
        pirate1.setPrime(5);
        assertTrue(controlPartie.estPartieFini());
    }

    @Test
    void testEstPartieFini_primeJoueur2Atteinte() {
        pirate2.setPrime(5);
        assertTrue(controlPartie.estPartieFini());
    }

    @Test
    void testGetGagnant_gagnant1ByPv() {
        pirate2.setPv(0);
        assertEquals(1, controlPartie.getGagnant());
    }

    @Test
    void testGetGagnant_gagnant1ByPrime() {
        pirate1.setPrime(5);
        assertEquals(1, controlPartie.getGagnant());
    }

    @Test
    void testGetGagnant_gagnant2ByPv() {
        pirate1.setPv(0);
        assertEquals(2, controlPartie.getGagnant());
    }

    @Test
    void testGetGagnant_gagnant2ByPrime() {
        pirate2.setPrime(5);
        assertEquals(2, controlPartie.getGagnant());
    }


    @Test
    void testGetTourJoueurOddAndEven() {
        partie.setNbTour(1);
        assertEquals(1, controlPartie.getTourJoueur());

        partie.setNbTour(2);
        assertEquals(2, controlPartie.getTourJoueur());
    }

    @Test
    void testPasserAuTourSuivant() {
        int before = partie.getNbTour();
        controlPartie.passerAuTourSuivant();
        assertEquals(before + 1, partie.getNbTour());
    }

    @Test
    void testInitPartie() {
        pirate1.getMain().clear();
        pirate2.getMain().clear();
        controlPartie.initPartie();
        assertEquals(4, pirate1.getMain().size());
        assertEquals(4, pirate2.getMain().size());
    }

    @Test
    void testGetMainAndGetPirateNumJoueur() {
        Carte carte = new EffetInstantane(TypeCarte.ATTAQUE, "X", "Test", (j1, j2) -> {});
        pirate1.getMain().add(carte);
        assertEquals(1, controlPartie.getMain(1).size());
        assertEquals(0, controlPartie.getMain(2).size());

        assertEquals(pirate1, controlPartie.getPirateNumJoueur(1));
        assertEquals(pirate2, controlPartie.getPirateNumJoueur(2));
    }

    @Test
    void testGetPirateDuTourAndAutrePirate() {
        partie.setNbTour(1);
        assertEquals(pirate1, controlPartie.getPirateDuTour());
        assertEquals(pirate2, controlPartie.getAutrePirate());

        partie.setNbTour(2);
        assertEquals(pirate2, controlPartie.getPirateDuTour());
        assertEquals(pirate1, controlPartie.getAutrePirate());
    }

    @Test
    void testAfficherMainEtAfficherPirate() {
        pirate1.getMain().add(new EffetInstantane(TypeCarte.SOUTIEN, "Carte Y", "Desc", (j1, j2) -> {}));
        partie.setNbTour(1);

        String main = controlPartie.afficherMain();
        assertTrue(main.contains("Carte Y"));

        String info = controlPartie.afficherPirate();
        assertTrue(info.contains("PV"));
        assertTrue(info.contains("Prime"));
    }

    @Test
    void testJouerTourDeJeu() {
        controlPartie.jouerTourDeJeu();
        assertEquals(5, controlPartie.getNumeroTour());
    }

    @Test
    void testGetNumeroTour() {
        partie.setNbTour(3);
        assertEquals(3, controlPartie.getNumeroTour());
    }

    @Test
    void testMelangerDeck() {
        controlPartie.melangerDeck();
        assertTrue(true);
    }
    
    @Test
    void testGetGagnant_egalite_gagnant1Et2SontVrais() {
        pirate1.setPv(0);
        pirate2.setPv(0);

        int gagnant = controlPartie.getGagnant();
        assertEquals(3, gagnant, "Les deux pirates sont morts : egalite (gagnant = 3)");
    }
    
    @Test
    void testGetGagnant_egalite_parPrime() {
        pirate1.setPrime(5);
        pirate2.setPrime(5);

        int gagnant = controlPartie.getGagnant();
        assertEquals(3, gagnant, "Les deux pirates ont atteint la prime : egalite (gagnant = 3)");
    }
}
