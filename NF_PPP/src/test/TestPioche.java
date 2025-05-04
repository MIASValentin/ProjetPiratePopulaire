package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Pioche;
import entities.Carte;
import entities.TypeCarte;

public class TestPioche {
    private Pioche pioche;
    private List<Carte> originalCartes;
    private final PrintStream standardOut = System.out;
    private ByteArrayOutputStream outputStream;

    private static class TestCarte extends Carte {
        public TestCarte(TypeCarte type, String nom, String description) {
            this.type = type;
            this.nom = nom;
            this.description = description;
        }
    }

    @BeforeEach
    public void setUp() {
        originalCartes = new ArrayList<>();
        originalCartes.add(new TestCarte(TypeCarte.ATTAQUE, "A1", "Attaque 1"));
        originalCartes.add(new TestCarte(TypeCarte.POPULARITE, "P1", "Popularité 1"));
        originalCartes.add(new TestCarte(TypeCarte.BATEAU, "B1", "Bateau 1"));
        pioche = new Pioche(new ArrayList<>(originalCartes));
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testDonnerCarteSequence() {
        Set<Carte> returned = new HashSet<>();
        int initialSize = pioche.getNbCarte();
        assertEquals(originalCartes.size(), initialSize, "Le nombre initial de cartes doit correspondre");

        for (int i = 0; i < initialSize; i++) {
            Carte c = pioche.donnerCarte();
            assertNotNull(c, "La carte retournée ne doit pas être null avant épuisement");
            assertTrue(originalCartes.contains(c), "La carte retournée doit provenir de la liste initiale");
            returned.add(c);
            assertEquals(initialSize - i - 1, pioche.getNbCarte(), "Le nombre de cartes doit diminuer après un tirage");
        }
        assertEquals(originalCartes.size(), returned.size(), "Chaque carte doit être retournée exactement une fois");
        assertNull(pioche.donnerCarte(), "La pioche vide doit retourner null");
        assertEquals(0, pioche.getNbCarte(), "Une pioche vide doit contenir 0 carte");
    }

    @Test
    public void testDonnerCarteEmptyPioche() {
        Pioche empty = new Pioche(new ArrayList<>());
        assertNull(empty.donnerCarte(), "Une pioche sans carte doit retourner null");
        assertEquals(0, empty.getNbCarte(), "Une pioche vide doit contenir 0 carte");
    }

    @Test
    public void testGetCartesReturnsList() {
        List<Carte> cartes = pioche.getCartes();
        assertNotNull(cartes, "getCartes ne doit pas retourner null");
        assertEquals(originalCartes.size(), cartes.size(), "La liste retournée doit avoir la même taille que la liste initiale");
        assertTrue(cartes.containsAll(originalCartes), "La liste retournée doit contenir toutes les cartes initiales");

        // Vérifier que modifier la liste retournée impacte la pioche
        cartes.clear();
        assertEquals(0, pioche.getNbCarte(), "Clear sur la liste retournée doit vider la pioche");
    }

    @Test
    public void testAfficherPioche() {
        pioche.afficherPioche();
        String output = outputStream.toString().trim();
        String[] lines = output.split(System.lineSeparator());

        assertTrue(lines.length > 0, "Affichage doit contenir au moins une ligne");
        assertEquals("Contenu de la pioche :", lines[0], "Première ligne doit indiquer le titre de la pioche");
        for (int i = 1; i < lines.length; i++) {
            String expectedPrefix = "- ";
            assertTrue(lines[i].startsWith(expectedPrefix), "Chaque ligne doit commencer par '- '");
            String cardName = lines[i].substring(expectedPrefix.length());
            assertTrue(originalCartes.stream().anyMatch(c -> c.getNom().equals(cardName)),
                       "La carte affichée doit exister dans la pioche initiale");
        }
    }
}
