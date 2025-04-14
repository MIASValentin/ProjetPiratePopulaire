import java.util.Arrays;
import java.util.List;

public class FabriqueDeCarte {
	//modifier creer carte pour creer selon les paramètres
    private List<Carte> creerCartes() {//ajouter carte.s selon param varargs
        return Arrays.asList(
            new Carte(
                "Discours Inspirant",
                "Augmente votre popularité de 1.",
                (joueur, adversaire) -> joueur.ajouterPopularite(1)
            ),
            new Carte(
                "Main de Fer",
                "Gagne 2 popularité, mais perd 1 point de vie.",
                (joueur, adversaire) -> {
                    joueur.ajouterPopularite(2);
                    joueur.ajouterVie(-1);
                }
            ),
            new Carte(
                "Bombe Artisanale",
                "Fait perdre 2 points de vie à l'adversaire, mais vous coûte 1 point de vie.",
                (joueur, adversaire) -> {
                    adversaire.ajouterVie(-2);
                    joueur.ajouterVie(-1);
                }
            )
        );
    }
    
    public List<Carte> creerDeck(){
    	//TODO
    };
    
}
