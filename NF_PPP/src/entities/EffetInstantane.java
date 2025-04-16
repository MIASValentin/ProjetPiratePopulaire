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


	

    

}