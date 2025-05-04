package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import entities.FabriqueDeCarte;
import entities.Carte;
import entities.TypeCarte;
import entities.EffetInstantane;
import InterfaceNF.ICarte;

public class TestFabriqueDeCarte {

    @Test
    public void testCreerCartes_NotNullAndSize() {
        FabriqueDeCarte fab = new FabriqueDeCarte();
        List<Carte> cartes = fab.creerCartes();
        assertNotNull(cartes, "La liste retournée par creerCartes ne doit pas être null");
        assertEquals(13, cartes.size(), "La fabrique doit créer exactement 13 cartes");
    }

    @Test
    public void testCreerCartes_TypeDistribution() {
        FabriqueDeCarte fab = new FabriqueDeCarte();
        List<Carte> cartes = fab.creerCartes();
        long nbAttaque    = cartes.stream().filter(c -> c.getType() == TypeCarte.ATTAQUE).count();
        long nbSoutien    = cartes.stream().filter(c -> c.getType() == TypeCarte.SOUTIEN).count();
        long nbPopularite = cartes.stream().filter(c -> c.getType() == TypeCarte.POPULARITE).count();
        assertEquals(8, nbAttaque,    "Doit y avoir 8 cartes de type ATTAQUE");
        assertEquals(4, nbSoutien,    "Doit y avoir 4 cartes de type SOUTIEN");
        assertEquals(1, nbPopularite, "Doit y avoir 1 carte de type POPULARITE");
    }

    @Test
    public void testCreerCartes_UniqueNames() {
        FabriqueDeCarte fab = new FabriqueDeCarte();
        List<Carte> cartes = fab.creerCartes();
        Set<String> noms = cartes.stream()
                                  .map(Carte::getNom)
                                  .collect(Collectors.toSet());
        assertEquals(cartes.size(), noms.size(), "Chaque carte doit avoir un nom unique");
    }

    @Test
    public void testCreerCartes_ContainsExpectedCardNames() {
        FabriqueDeCarte fab = new FabriqueDeCarte();
        Set<String> noms = fab.creerCartes().stream()
                                      .map(Carte::getNom)
                                      .collect(Collectors.toSet());
        assertTrue(noms.contains("Discours Inspirant"), "Doit contenir 'Discours Inspirant'");
        assertTrue(noms.contains("Vol d'Identité"),     "Doit contenir 'Vol d'Identité'");
    }

    @Test
    public void testCreerDeck_NotImplemented() {
        FabriqueDeCarte fab = new FabriqueDeCarte();
        List<ICarte> deck = fab.creerDeck();
        assertNull(deck, "creerDeck n'est pas implémenté et doit renvoyer null");
    }

    @Test
    public void testCreerCartes_NoBateauCards() {
        FabriqueDeCarte fab = new FabriqueDeCarte();
        long nbBateau = fab.creerCartes().stream()
                          .filter(c -> c.getType() == TypeCarte.BATEAU)
                          .count();
        assertEquals(0, nbBateau, "Aucune carte de type BATEAU ne doit être créée");
    }

    @Test
    public void testCreerCartes_OrderFirstAndLast() {
        FabriqueDeCarte fab = new FabriqueDeCarte();
        List<Carte> cartes = fab.creerCartes();
        assertEquals("Discours Inspirant", cartes.get(0).getNom(), "La première carte doit être 'Discours Inspirant'");
        assertEquals("Vol d'Identité",      cartes.get(cartes.size() - 1).getNom(),
                     "La dernière carte doit être 'Vol d'Identité'");
    }

    @Test
    public void testCreerCartes_AllInstancesEffetInstantane() {
        FabriqueDeCarte fab = new FabriqueDeCarte();
        List<Carte> cartes = fab.creerCartes();
        for (Carte c : cartes) {
            assertTrue(c instanceof EffetInstantane, "Chaque carte doit être une instance de EffetInstantane");
        }
    }

    @Test
    public void testCreerCartes_ModificationDoesNotAffectNewInstance() {
        FabriqueDeCarte fab = new FabriqueDeCarte();
        List<Carte> first = fab.creerCartes();
        first.clear();
        assertEquals(0, first.size(), "La liste modifiée doit refléter la suppression");
        List<Carte> second = fab.creerCartes();
        assertEquals(13, second.size(), "Une nouvelle invocation doit recréer la liste complète");
    }
}
