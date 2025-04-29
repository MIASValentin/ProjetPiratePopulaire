package Control;

import InterfaceNF.IPirate;

public class ControlJouerCarte {

    /**
     * Joue une carte classique pour le pirate courant.
     */
    public void jouerCarte(IPirate joueurCourant) {
        joueurCourant.jouerCarte();
    }

    /**
     * Joue une carte "bateau" pour le pirate courant.
     */
    public void jouerBateau(IPirate joueurCourant) {
        // On réutilise la même logique que pour une carte classique,
        // l'implémentation de jouerCarte gérant le type bateau.
        joueurCourant.jouerCarte();
    }
}
