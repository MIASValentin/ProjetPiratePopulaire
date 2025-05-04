package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Control.ControlPiocherCarte;
import entities.Pioche;
import entities.Pirate;
import entities.Carte;
import entities.TypeCarte;

class TestControlPiocherCarte {
    private ControlPiocherCarte control;
    private Pioche pioche;
    private Pirate pirate;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    /**
     * Une petite classe de test qui hérite de Carte
     * et fournit un toString() pour l’affichage.
     */
    private static class TestCarte extends Carte {
        TestCarte(TypeCarte type, String nom) {
            this.type = type;
            this.nom  = nom;
            this.description = "";
        }
        @Override
        public String toString() {
            return nom;
        }
    }

    @BeforeEach
    void setUp() {
        // Construire une pioche de 3 cartes
        List<Carte> cartes = new ArrayList<>();
        cartes.add(new TestCarte(TypeCarte.ATTAQUE,      "A1"));
        cartes.add(new TestCarte(TypeCarte.POPULARITE,  "P1"));
        cartes.add(new TestCarte(TypeCarte.SOUTIEN,     "S1"));

        pioche  = new Pioche(new ArrayList<>(cartes));
        control = new ControlPiocherCarte(pioche);
        pirate  = new Pirate(5, 0, 1);

        // Capturer la sortie console
        originalOut = System.out;
        outContent  = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        // Rétablir la sortie standard
        System.setOut(originalOut);
    }

    @Test
    void testPiocherCarte_Normal() {
        // Piocher 2 cartes : main = 2, pioche restante = 1
        control.piocherCarte(pirate, 2);
        assertEquals(2, pirate.getMain().size(),
            "Le pirate doit piocher 2 cartes");
        assertEquals(1, pioche.getNbCarte(),
            "Il doit rester 1 carte dans la pioche après 2 pioches");
    }

    @Test
    void testPiocherCarte_PlusQueDisponible() {
        // Piocher plus que le nombre de cartes disponibles
        control.piocherCarte(pirate, 5);
        assertEquals(3, pirate.getMain().size(),
            "Le pirate doit piocher toutes les cartes disponibles");

        // Vérifier qu’un message « La pioche est vide ! » a été émis
        String console = outContent.toString();
        assertTrue(console.contains("La pioche est vide !"),
            "Doit afficher un message lorsque la pioche se vide");
    }

    @Test
    void testPiocherCarte_NegativeCount() {
        int tailleAvant = pirate.getMain().size();
        control.piocherCarte(pirate, -1);
        assertEquals(tailleAvant, pirate.getMain().size(),
            "Aucune carte ne doit être piochée pour un nombre négatif");
        assertTrue(outContent.toString().isEmpty(),
            "Pas de message d’erreur pour un count négatif");
    }

    @Test
    void testPiocherCarte_NullPirate() {
        assertThrows(NullPointerException.class,
            () -> control.piocherCarte(null, 1),
            "Passer un pirate null doit lancer NullPointerException");
    }
}
