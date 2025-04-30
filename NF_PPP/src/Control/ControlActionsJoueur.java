package Control;

import entities.Carte;
import entities.Pioche;
import entities.Pirate;

import java.util.List;

public class ControlActionsJoueur {
    
    public void changerPv(Pirate joueur, int value) {
        joueur.setPv(joueur.getPv() + value);
    }
    
    public void changerPrime(Pirate joueur, int value) {
        joueur.setPrime(joueur.getPrime() + value);
    }

    public void piocherCarte(Pirate joueur, Pioche pioche, int nbCarteAPiocher) {
        List<Carte> main = joueur.getMain();
        for (int i = 0; i < nbCarteAPiocher; i++) {
            Carte piochee = pioche.donnerCarte();
            if (piochee != null) {
                main.add(piochee);
            }
        }
    }
}
