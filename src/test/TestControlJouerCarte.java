package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import control.ControlJouerCarte;
import control.ControlPartie;
import entities.EffetInstantane;
import entities.Partie;
import entities.Pirate;
import entities.TypeCarte;
import entities.ZoneCarte;

public class TestControlJouerCarte {
	private Pirate pirate1;
    private Pirate pirate2;
    private Partie partie;
    private ControlPartie controlPartie;
    private ControlJouerCarte controlJouerCarte;

    @BeforeEach
    void setUp() {
        // Initialiser deux pirates
        pirate1 = new Pirate(10, 0, 1);
        pirate2 = new Pirate(10, 0, 2);

        // Creer une zone de cartes vide
        ZoneCarte zoneCarte = new ZoneCarte();

        // Creer une Partie avec les deux pirates et la zone
        partie = new Partie(pirate1, pirate2, zoneCarte);
        partie.setNbTour(1); // tour impair -> c'est pirate1 qui joue

        // Creer les controleurs
        controlPartie = new ControlPartie(partie);
        controlJouerCarte = new ControlJouerCarte(controlPartie);
    }

    @Test
    void testAppliquerEffet() {
        // Creer une carte qui enleve 2 PV a l'adversaire
        EffetInstantane attaque = new EffetInstantane(
            TypeCarte.ATTAQUE,
            "Coup rapide",
            "Inflige 2 degats a l'adversaire.",
            (j1, j2) -> j2.changerPv(-2)
        );

        // Ajouter la carte a la main du pirate1
        pirate1.getMain().add(attaque);

        // Verifier les PV de l'adversaire avant l'effet
        assertEquals(10, pirate2.getPv());

        // Jouer la carte
        controlJouerCarte.appliquerEffet(1);

        // Verifier que l'effet a ete applique et que la carte a ete retiree de la main
        assertEquals(8, pirate2.getPv(), "Le pirate 2 devrait perdre 2 PV.");
        assertFalse(pirate1.getMain().contains(attaque), "La carte jouee doit etre retiree de la main.");
    }

    @Test
    void testPiocherCarte() {
        // Nombre de cartes avant la pioche
        int cartesAvant = pirate1.getMain().size();

        // Piocher une carte
        controlJouerCarte.piocherCarte(1);

        // Nombre de cartes apres la pioche
        int cartesApres = pirate1.getMain().size();

        assertEquals(cartesAvant + 1, cartesApres, "Le pirate devrait avoir 1 carte de plus apres la pioche.");
    }
}
