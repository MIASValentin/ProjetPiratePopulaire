package entities;



import java.util.function.BiConsumer;

public abstract class Carte {
    protected TypeCarte type;
    protected String nom; 
    protected String description; 

    //private int EffetJoueurPv; 
    //private int EffetJoueurPrime;

    //private int EffetAutreJoueurPv; 
    //private int EffetAutreJoueurPrime; 

    //BiFunction<Integer, Integer, Integer> applyEffetPv = (PvJoueur, effet) -> pvJoueur + effet;

    
  

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