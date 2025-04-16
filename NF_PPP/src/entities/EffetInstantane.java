package entities;

import java.util.function.BiConsumer;

public class EffetInstantane extends Carte{

    private BiConsumer<Pirate, Pirate> effet; 

    public EffetInstantane(TypeCarte type, String nom, String description, BiConsumer<Pirate, Pirate> effet) {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void apply(Pirate j1, Pirate j2) {
        // TODO Auto-generated method stub

    }

    @Override
    public BiConsumer<Pirate, Pirate> apply() {
        // TODO Auto-generated method stub
        return null;
    }

}