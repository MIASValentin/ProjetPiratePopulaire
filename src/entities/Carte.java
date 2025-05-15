package entities;

public abstract class Carte {
    protected TypeCarte type;
    protected String nom; 
    protected String description; 

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