package entities;

import java.util.Arrays;
import java.util.List;

public class FabriqueDeCarte {
    //modifier creer carte pour creer selon les paramètres
    public List<Carte> creerCartes() {

        Carte discoursInspirant = new EffetInstantane(
            TypeCarte.ATTAQUE,
            "Discours Inspirant",
            "Augmente votre prime de 1.",
            (joueur, adversaire) -> {joueur.changerPime(1);} 
        );

        Carte mainDeFer = new EffetInstantane(
            TypeCarte.ATTAQUE,
            "Main de Fer",
            "Gagne 2 de prime, mais perd 1 point de vie.",
            (joueur, adversaire) -> {
                joueur.changerPv(-1);
                joueur.changerPime(2);
            }
        );

        Carte bombeArtisanale = new EffetInstantane(
            TypeCarte.ATTAQUE,
            "Bombe Artisanale",
            "Fait perdre 2 points de vie à l'adversaire, mais vous coûte 1 point de vie.",
            (joueur, adversaire) -> {
                adversaire.changerPv(-2);
                joueur.changerPv(-1);
            }
        );

        return Arrays.asList(discoursInspirant, mainDeFer, bombeArtisanale);
    }


//    public List<Carte> creerDeck(){
//        //TODO
//    };

}