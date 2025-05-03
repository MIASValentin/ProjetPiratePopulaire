package Control;

import entities.FabriqueDeCarte;
import entities.Pioche;
import entities.Carte;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.stream.Stream;

import InterfaceNF.main;

public class ControlCreerDeck {

	static final int NB_CARTE_BUNDLE = 3;
	static final int NB_BUNDLE = 3;
	
    private Pioche pioche;
    private FabriqueDeCarte fabrique;

    public ControlCreerDeck(Pioche pioche, FabriqueDeCarte fabrique) {
        this.pioche = pioche;
        this.fabrique = fabrique;
    }

    public void creerDeck() {
        List<Carte> nouvellesCartes = fabrique.creerCartes();
        pioche.getCartes().addAll(nouvellesCartes);
    }
    
    public List<ArrayList<Carte>> creerBundle() {
    	
    	
    	Random rd = new Random(); 
    	List<Carte> nouvellesCartes = fabrique.creerCartes();
    	List<Carte> cartesBundle = Stream.generate(()->nouvellesCartes.
    			get(rd.nextInt(nouvellesCartes.size()-1)))
    			.limit(NB_CARTE_BUNDLE*NB_BUNDLE).toList();
    	
    	List<ArrayList<Carte>> rslt = new ArrayList<ArrayList<Carte>>();
    	rslt = Stream.of(new ArrayList<Carte>(), new ArrayList<Carte>(), new ArrayList<Carte>()).toList();
    	
    	for (int i=0; i<NB_BUNDLE*NB_CARTE_BUNDLE; i++) {
    		rslt.get(i/NB_BUNDLE).add(cartesBundle.get(i));
    	}
    	
    	return rslt;
    }
    
    public static void main(String[] args) {
    	
    }
}
