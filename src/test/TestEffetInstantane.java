package test;

import entities.EffetInstantane;
import entities.Pirate;
import entities.TypeCarte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.*;

public class TestEffetInstantane {

    private EffetInstantane effet;
    private BiConsumer<Pirate, Pirate> biConsumer;

    @BeforeEach
    void setUp() {
        // Effet : augmente 2 PV du joueur 1 et enlève 1 PV du joueur 2
        biConsumer = (j1, j2) -> {
            j1.changerPv(2);
            j2.changerPv(-1);
        };
        effet = new EffetInstantane(TypeCarte.ATTAQUE, "TestEffet", "Description test.", biConsumer);
    }

    @Test
    void testGetEffet_returnsSameFunction() {
        assertSame(biConsumer, effet.getEffet(), "getEffet doit retourner la même fonction passée au constructeur");
    }

    @Test
    void testUtiliser_appliesEffect() {
        Pirate pirate1 = new Pirate(10, 0, 1);
        Pirate pirate2 = new Pirate(10, 0, 2);
        int pv1Avant = pirate1.getPv();
        int pv2Avant = pirate2.getPv();

        effet.utiliser(pirate1, pirate2);

        assertEquals(pv1Avant + 2, pirate1.getPv(), "Le joueur 1 doit gagner 2 PV après utilisation de l'effet");
        assertEquals(pv2Avant - 1, pirate2.getPv(), "Le joueur 2 doit perdre 1 PV après utilisation de l'effet");
    }

    @Test
    void testToString_containsNameAndDescription() {
        String str = effet.toString();
        assertTrue(str.contains("TestEffet"), "toString doit inclure le nom de la carte");
        assertTrue(str.contains("Description test."), "toString doit inclure la description de la carte");
        assertEquals("TestEffet : Description test.", str, "toString doit suivre le format 'nom : description'");
    }
}
