package boundary;

import Control.ControlDonnerCarte;
import entities.Pirate;
import entities.Carte;
import java.util.List;

public class FrontiereDonnerCarte {

    private ControlDonnerCarte controlDonnerCarte;

    public FrontiereDonnerCarte(ControlDonnerCarte controlDonnerCarte) {
        this.controlDonnerCarte = controlDonnerCarte;
    }

    public void donnerCartes(Pirate pirate, int nbCartes) {
        controlDonnerCarte.donnerCarte(pirate, nbCartes);

        System.out.println("Cartes données à " + pirate.getNum() + " :");
        List<Carte> main = pirate.getMain();
        for (int i = 0; i < main.size(); i++) {
            Carte carte = main.get(i);
            System.out.println("- " + carte.getNom() + " : " + carte.getDescription());
        }
    }
}