package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Pirate;

public class TestPirate {
    private Pirate pirate1;
    private Pirate pirate2;
    private Pirate piratedead;
    private Pirate piratewin;
    private Pirate tropvivant;
    private Pirate troppopulaire;

    private List<LambdaTestStructure> listeTest = new ArrayList<>();

    public class LambdaTestStructure {
        protected Pirate pirate;
        protected Object result;

        public LambdaTestStructure(Pirate pirate, Object result) {
            this.pirate = pirate;
            this.result = result;
        }
    }

    @BeforeEach
    public void setUp() {
        pirate1 = new Pirate(5, 0, 1);
        pirate2 = new Pirate(5, 0, 2);
        piratedead = new Pirate(0, 0, 0);
        piratewin = new Pirate(5, 5, 0);
        tropvivant = new Pirate(10, 0, 0);
        troppopulaire = new Pirate(5, 10, 0);
        listeTest.clear();
    }

    @Test
    public void testGettersSetters() {
        listeTest.add(new LambdaTestStructure(pirate1, new Object[]{5, 0, 1}));
        listeTest.add(new LambdaTestStructure(pirate2, new Object[]{5, 0, 2}));
        listeTest.add(new LambdaTestStructure(piratedead, new Object[]{0, 0, 0}));
        listeTest.add(new LambdaTestStructure(piratewin, new Object[]{5, 5, 0}));
        listeTest.add(new LambdaTestStructure(tropvivant, new Object[]{10, 0, 0}));
        listeTest.add(new LambdaTestStructure(troppopulaire, new Object[]{5, 10, 0}));

        Consumer<LambdaTestStructure> consumerTest = (LambdaTestStructure elt) -> {
            assertEquals(((Object[]) elt.result)[0], elt.pirate.getPv());
            assertEquals(((Object[]) elt.result)[1], elt.pirate.getPrime());
            assertEquals(((Object[]) elt.result)[2], elt.pirate.getNum());
        };

        listeTest.stream().forEach(consumerTest);

        pirate1.setPv(7);
        pirate1.setPrime(3);
        assertEquals(7, pirate1.getPv());
        assertEquals(3, pirate1.getPrime());
    }

    @Test
    public void testChangerPvEtPrime() {
        pirate1.changerPv(-2);
        pirate1.changerPime(5);
        assertEquals(3, pirate1.getPv());
        assertEquals(5, pirate1.getPrime());

        tropvivant.changerPv(5);
        troppopulaire.changerPime(-3);
        assertEquals(15, tropvivant.getPv());
        assertEquals(7, troppopulaire.getPrime());
    }
}
