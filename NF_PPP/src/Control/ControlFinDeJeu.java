package Control;

import entities.Pirate;
import InterfaceNF.IPirate;


public class ControlFinDeJeu {
    private Pirate[] pirates;

    public ControlFinDeJeu(Pirate[] pirates) {
        this.pirates = pirates;
    }

    /**
     * Retourne true si l'un des pirates a atteint 0 PV ou la prime max.
     */
    public boolean conditionsVictoire() {
        for (Pirate p : pirates) {
            if (p.getPv() <= 0 || p.getPrime() >= IPirate.PRIMEMAX) {
                return true;
            }
        }
        return false;
    }

    /**
     * Détermine et retourne l'identité du pirate gagnant sous forme de chaîne.
     */
    public String afficherGagnant() {
        Pirate gagnant = pirates[0];
        for (Pirate p : pirates) {
            if (p.getPv() > gagnant.getPv() ||
                (p.getPv() == gagnant.getPv() && p.getPrime() > gagnant.getPrime())) {
                gagnant = p;
            }
        }
        // On récupère l'indice dans le tableau pour afficher le numéro de pirate
        int index = java.util.Arrays.asList(pirates).indexOf(gagnant) + 1;
        return "Pirate " + index + " (PV : " + gagnant.getPv() + ", Prime : " + gagnant.getPrime() + ")";
    }
}
