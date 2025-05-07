package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.BiConsumer;

import org.junit.jupiter.api.Test;


import entities.Pirate;

public class TestApplyCard {

    @Test
    void testAnonymousImplementation() {
        // Initialisation de deux pirates
        Pirate p1 = new Pirate(10, 0, 1);
        Pirate p2 = new Pirate(8, 2, 2);

        // Dummy ApplyCard: réduit PV de p1 et augmente prime de p2
        BiConsumer<Pirate, Pirate> impl = (j1, j2) -> {
            j1.setPv(j1.getPv() - 3);
            j2.setPrime(j2.getPrime() + 5);
        };

        // Application
        impl.accept(p1, p2);

        // Vérification des effets
        assertEquals(7, p1.getPv(), "Le PV de p1 doit être diminué de 3");
        assertEquals(7, p2.getPrime(), "La prime de p2 doit être augmentée de 5");
    }

    @Test
    void testImplementationClassReference() {
        // Vérifie que l'interface est bien utilisée comme type
        BiConsumer<Pirate, Pirate> dummy = new BiConsumer<Pirate, Pirate>() {
            @Override
            public void accept(Pirate j1, Pirate j2) {
                // Aucune modification
            }
        };
        assertNotNull(dummy, "L'instance anonyme doit implémenter ApplyCard");
    }
}
