package test;

import control.ControlJouerCarte;
import control.ControlPartie;
import entities.EffetInstantane;
import entities.Pirate;
import entities.Deck;
import entities.TypeCarte;
import entities.Carte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControlJouerCarteTest {

    /**
     * Stub minimal de ControlPartie pour piloter
     * getPirateDuTour() et getAutrePirate() sans mock.
     */
    private static class FakePartie extends ControlPartie {
        private Pirate tour, autre;

        public void setTour(Pirate p) { this.tour = p; }
        public void setAutre(Pirate p) { this.autre = p; }

        @Override
        public Pirate getPirateDuTour() { return tour; }

        @Override
        public Pirate getAutrePirate() { return autre; }
    }

    private FakePartie partie;
    private ControlJouerCarte controller;

    @BeforeEach
    void setUp() {
        partie    = new FakePartie();
        controller = new ControlJouerCarte(partie);  // classe sous test :contentReference[oaicite:1]{index=1}
    }

    @Test
    void appliquerEffet_doitModifierLesPiratesEtRetirerLaCarte() {
        // 1) Préparer deux pirates et injecter dans la partie
        Pirate origine = new Pirate(10, 0, 1);
        Pirate cible   = new Pirate(5,  1, 2);
        partie.setTour(origine);
        partie.setAutre(cible);

        // 2) Créer une carte à effet instantané : +2 PV à l'origine, +3 prime à la cible
        EffetInstantane carte = new EffetInstantane(
            TypeCarte.ATTAQUE,
            "Test",
            "Description",
            (o, c) -> {
                o.changerPv(2);
                c.changerPrime(3);
            }
        );

        // 3) Mettre cette carte en main de l'origine
        List<Carte> main = new ArrayList<>();
        main.add(carte);
        origine.setMain(main);

        // 4) Appeler la méthode sous test
        controller.appliquerEffet(1);

        // 5) Vérifier les effets et la suppression de la carte
        assertEquals(12, origine.getPv(),   "Origine doit gagner 2 PV");
        assertEquals(4,  cible.getPrime(),  "Cible doit gagner 3 de prime");
        assertTrue(origine.getMain().isEmpty(), "La carte doit être retirée de la main");
    }

    @Test
    void piocherCarte_doitAjouterLeNombreCorrectDeCartes() {
        // 1) Pirate du tour avec un deck vide
        Pirate pirate = new Pirate(10, 0, 1);
        Deck deckVide = new Deck();
        pirate.setDeck(deckVide);
        partie.setTour(pirate);

        // 2) Taille avant pioche
        int tailleAvant = pirate.getDeck().getCartes().size();

        // 3) Appel sous test
        controller.piocherCarte(3);

        // 4) Vérifier que 3 cartes ont été ajoutées
        int tailleApres = pirate.getDeck().getCartes().size();
        assertEquals(tailleAvant + 3, tailleApres,
            "Après piocherCarte(3), le deck doit contenir 3 cartes supplémentaires");
    }
}
