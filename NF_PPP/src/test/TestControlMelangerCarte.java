package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Control.ControlMelangerCarte;
import entities.Pioche;
import entities.Pirate;
import entities.Carte;
import entities.EffetInstantane;
import entities.TypeCarte;

public class TestControlMelangerCarte {
    private ControlMelangerCarte ctrl;
    private Pioche pioche;
    private Pirate p1;
    private Pirate p2;

    /**
     * Carte de test concrète (hérite de EffetInstantane pour pouvoir instancier).
     */
    private static class DummyCarte extends EffetInstantane {
        public DummyCarte(TypeCarte type, String nom) {
            super(type, nom, "", (a, b) -> {});
        }
        @Override
        public String toString() {
            return getNom();
        }
    }

    @BeforeEach
    public void setUp() {
        // Construire la pioche avec 3 cartes distinctes
        List<Carte> cartes = new ArrayList<>();
        cartes.add(new DummyCarte(TypeCarte.ATTAQUE,     "A"));
        cartes.add(new DummyCarte(TypeCarte.POPULARITE,  "P"));
        cartes.add(new DummyCarte(TypeCarte.SOUTIEN,     "S"));
        pioche  = new Pioche(new ArrayList<>(cartes));

        // Deux pirates avec chacun une main
        p1 = new Pirate(5, 0, 1);
        p2 = new Pirate(5, 0, 2);
        // On donne toutes les cartes à p1, et une seule à p2
        p1.getMain().addAll(cartes);
        p2.getMain().add(cartes.get(1));

        ctrl = new ControlMelangerCarte(pioche, new Pirate[]{p1, p2});
    }

    @Test
    public void testMelangerDeck_ElementsUnchanged() {
        // Capturer l’état avant mélange
        List<Carte> before = new ArrayList<>(pioche.getCartes());

        // Mélanger
        ctrl.melangerDeck();

        // Vérifier la taille et la présence de tous les éléments
        List<Carte> after = pioche.getCartes();
        assertEquals(before.size(), after.size(),
            "Le nombre de cartes dans la pioche ne doit pas changer après mélange");
        assertTrue(after.containsAll(before),
            "Toutes les cartes initiales doivent rester dans la pioche après mélange");
    }

    @Test
    public void testMelangerMain_ElementsUnchangedForEachPirate() {
        // Capturer l’état des mains avant mélange
        List<Carte> main1Before = new ArrayList<>(p1.getMain());
        List<Carte> main2Before = new ArrayList<>(p2.getMain());

        // Mélanger les mains
        ctrl.melangerMain();

        // Vérifier p1
        List<Carte> main1After = p1.getMain();
        assertEquals(main1Before.size(), main1After.size(),
            "La taille de la main de p1 doit rester identique après mélange");
        assertTrue(main1After.containsAll(main1Before),
            "p1 doit toujours contenir les mêmes cartes après mélange");

        // Vérifier p2
        List<Carte> main2After = p2.getMain();
        assertEquals(main2Before.size(), main2After.size(),
            "La taille de la main de p2 doit rester identique après mélange");
        assertTrue(main2After.containsAll(main2Before),
            "p2 doit toujours contenir les mêmes cartes après mélange");
    }
}
