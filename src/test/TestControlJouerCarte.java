package test;

import control.ControlJouerCarte;
import control.ControlPartie;
import entities.Partie;
import entities.Pirate;
import entities.EffetInstantane;
import entities.TypeCarte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestControlJouerCarte {
    private Pirate pirate1;
    private Pirate pirate2;
    private Partie partie;
    private ControlPartie controlPartie;
    private ControlJouerCarte controlJouerCarte;

    @BeforeEach
    void setUp() {
        pirate1 = new Pirate(10, 0, 1);
        pirate2 = new Pirate(10, 0, 2);
        partie = new Partie(pirate1, pirate2);
        controlPartie = new ControlPartie(partie);
        controlJouerCarte = new ControlJouerCarte(controlPartie);
    }

    @Test
    void testPiocherCarte_increasesMainSize() {
        // Pirate 1's turn by default (nbTour = 0 -> player 2), force to player 1
        partie.setNbTour(1);
        // initial hand
        assertEquals(0, pirate1.getMain().size(), "Main initiale vide");
        controlJouerCarte.piocherCarte(3);
        assertEquals(3, pirate1.getMain().size(), "Le pirate doit piocher le nombre de cartes demandé");
    }

    @Test
    void testAppliquerEffet_affectsStateAndRemovesCard() {
        // Préparer un effet qui augmente la prime du joueur actif de 2
        EffetInstantane effetPrime = new EffetInstantane(
                TypeCarte.SOUTIEN,
                "Gain Prime",
                "Ajoute 2 de prime.",
                (origine, cible) -> origine.changerPime(2)
        );
        // Mettre l'effet dans la main du joueur 1
        pirate1.getMain().add(effetPrime);
        // Forcer le tour au joueur 1
        partie.setNbTour(1);
        int primeAvant = pirate1.getPrime();

        controlJouerCarte.appliquerEffet(1);

        // Vérifie que l'effet a été appliqué
        assertEquals(primeAvant + 2, pirate1.getPrime(), "La prime du pirate doit augmenter de 2");
        // Vérifie que la carte a été retirée de la main
        assertFalse(pirate1.getMain().contains(effetPrime), "La carte jouée doit être retirée de la main");
    }

    @Test
    void testAppliquerEffet_affecteAdversaire() {
        // Préparer un effet qui inflige 4 PV à l'adversaire
        EffetInstantane effetDegats = new EffetInstantane(
                TypeCarte.ATTAQUE,
                "Canon",
                "Inflige 4 dégâts.",
                (origine, cible) -> cible.changerPv(-4)
        );
        pirate1.getMain().add(effetDegats);
        partie.setNbTour(1);
        int pvAdversaireAvant = pirate2.getPv();

        controlJouerCarte.appliquerEffet(1);

        assertEquals(pvAdversaireAvant - 4, pirate2.getPv(), "Le pirate adverse doit perdre 4 PV");
        assertFalse(pirate1.getMain().contains(effetDegats), "La carte jouée doit être retirée de la main");
    }

    @Test
    void testAppliquerEffet_multipleCardsOrder() {
        // Deux effets en main pour tester l'ordre
        EffetInstantane effet1 = new EffetInstantane(
                TypeCarte.SOUTIEN,
                "Prime1",
                "+1 prime.",
                (origine, cible) -> origine.changerPime(1)
        );
        EffetInstantane effet2 = new EffetInstantane(
                TypeCarte.SOUTIEN,
                "Prime2",
                "+2 prime.",
                (origine, cible) -> origine.changerPime(2)
        );
        pirate1.getMain().add(effet1);
        pirate1.getMain().add(effet2);
        partie.setNbTour(1);
        int primeAvant = pirate1.getPrime();

        // Jouer la deuxième carte (numCarte=2)
        controlJouerCarte.appliquerEffet(2);

        assertEquals(primeAvant + 2, pirate1.getPrime(), "La prime doit augmenter de 2 pour le second effet");
        // Vérifie que seul effet2 est retiré
        assertTrue(pirate1.getMain().contains(effet1), "La première carte doit toujours être présente");
        assertFalse(pirate1.getMain().contains(effet2), "La deuxième carte doit être retirée");
    }
}
