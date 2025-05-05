// TestCarte.java
package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entities.Carte;
import entities.TypeCarte;

public class TestCarte {

    private static class DummyCarte extends Carte {
        public DummyCarte(TypeCarte type, String nom, String description) {
            this.type = type;
            this.nom = nom;
            this.description = description;
        }
    }

    @Test
    void testGetters() {
        TypeCarte t = TypeCarte.POPULARITE;
        String name = "MaCarte";
        String desc = "Description de la carte";
        DummyCarte c = new DummyCarte(t, name, desc);

        assertEquals(t, c.getType(), "getType doit retourner le type passé au constructeur");
        assertEquals(name, c.getNom(), "getNom doit retourner le nom passé au constructeur");
        assertEquals(desc, c.getDescription(), "getDescription doit retourner la description passée au constructeur");
    }

    @Test
    void testGetEffet_DefaultNull() {
        DummyCarte c = new DummyCarte(TypeCarte.ATTAQUE, "Test", "Desc");
        assertNull(c.getEffet(), "Par défaut, getEffet() renvoie null (stub)");
    }
}
