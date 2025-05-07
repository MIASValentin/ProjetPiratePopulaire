package entities;

public abstract class Carte {
    protected TypeCarte type;
    protected String nom; 
    protected String description; 

<<<<<<< HEAD
    //private int EffetJoueurPv; 
    //private int EffetJoueurPrime;

    //private int EffetAutreJoueurPv; 
    //private int EffetAutreJoueurPrime; 

    

    
  

=======
>>>>>>> 77b3eab9d2003edede2e220afb00983af0e94135
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