package entities;

import java.util.function.BiConsumer;

public class EffetInstantane extends Carte{

    private BiConsumer<Pirate, Pirate> effet; 

    public EffetInstantane(TypeCarte type, String nom, String description, BiConsumer<Pirate, Pirate> effet) {
        this.effet = effet;
        super.type = type;
        super.nom = nom;
        super.description = description;
    }
    
    
    public void utiliser(Pirate j1, Pirate j2) {
    	this.effet.accept(j1, j2); 
    }
    
    public BiConsumer<Pirate, Pirate> getEffet() {
    	return this.effet; 
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return super.nom + " : " + super.description; 
    }


	

    

}
