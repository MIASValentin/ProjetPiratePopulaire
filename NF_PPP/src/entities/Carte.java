package entities;



import java.util.function.BiConsumer;

public abstract class Carte implements ApplyCard {
    private TypeCarte type;
    private String nom; 
    private String description; 

    //private int EffetJoueurPv; 
    //private int EffetJoueurPrime;

    //private int EffetAutreJoueurPv; 
    //private int EffetAutreJoueurPrime; 

    //BiFunction<Integer, Integer, Integer> applyEffetPv = (PvJoueur, effet) -> pvJoueur + effet;

    
    public abstract BiConsumer<Pirate, Pirate> apply();

    public TypeCarte getType() {
        return type;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }


}