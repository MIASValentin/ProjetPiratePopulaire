package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import InterfaceNF.ICarte;
import InterfaceNF.IFabriqueDeCarte;


public class FabriqueDeCarte implements IFabriqueDeCarte{

    // Crée un ensemble de cartes selon les paramètres
    public List<Carte> creerCartes() {

        Carte discoursInspirant = new EffetInstantane(
            TypeCarte.ATTAQUE,
            "Discours Inspirant",
            "Augmente votre prime de 1.",
            (joueur, adversaire) -> {
                joueur.changerPime(1);
            }
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

        Carte tonneauDeRhum = new EffetInstantane(
            TypeCarte.SOUTIEN,
            "Tonneau de Rhum",
            "Restaure 3 points de vie.",
            (joueur, adversaire) -> {
                joueur.changerPv(3);
            }
        );

        Carte sabordage = new EffetInstantane(
            TypeCarte.ATTAQUE,
            "Sabordage",
            "Inflige 3 points de dégâts à l’adversaire, mais vous perdez 1 de prime.",
            (joueur, adversaire) -> {
                adversaire.changerPv(-3);
                joueur.changerPime(-1);
            }
        );

        Carte carteAuTresor = new EffetInstantane(
            TypeCarte.SOUTIEN,
            "Carte au Trésor",
            "Gagnez 3 de prime.",
            (joueur, adversaire) -> {
                joueur.changerPime(3);
            }
        );

        Carte criDeGuerre = new EffetInstantane(
            TypeCarte.SOUTIEN,
            "Cri de Guerre",
            "Vous gagnez 1 PV et 1 de prime.",
            (joueur, adversaire) -> {
                joueur.changerPv(1);
                joueur.changerPime(1);
            }
        );

        Carte tirDeCanon = new EffetInstantane(
            TypeCarte.ATTAQUE,
            "Tir de Canon",
            "Inflige 4 points de dégâts à l'adversaire.",
            (joueur, adversaire) -> {
                adversaire.changerPv(-4);
            }
        );

        Carte chantDesSirenes = new EffetInstantane(
            TypeCarte.SOUTIEN,
            "Chant des Sirènes",
            "L’adversaire perd 1 point de prime, envoûté par un chant mystérieux.",
            (joueur, adversaire) -> {
                adversaire.changerPime(-1);
            }
        );

        Carte abordage = new EffetInstantane(
            TypeCarte.ATTAQUE,
            "Abordage",
            "Inflige 2 dégâts à l’adversaire et vole 1 de prime.",
            (joueur, adversaire) -> {
                adversaire.changerPv(-2);
                adversaire.changerPime(-1);
                joueur.changerPime(1);
            }
        );

      
        Carte tempêteEnMer = new EffetInstantane(
            TypeCarte.ATTAQUE,
            "Tempête en Mer",
            "Tous les pirates perdent 1 point de vie.",
            (joueur, adversaire) -> {
                joueur.changerPv(-1);
                adversaire.changerPv(-1);
            }
        );
     

        Carte criDeLaKraken = new EffetInstantane(
            TypeCarte.ATTAQUE,
            "Cri du Kraken",
            "Fait perdre 3 PV à l’adversaire. Vous perdez 1 PV sous la puissance de l'invocation.",
            (joueur, adversaire) -> {
                adversaire.changerPv(-3);
                joueur.changerPv(-1);
            }
        );

        return new ArrayList<>(Arrays.asList(
            discoursInspirant,
            mainDeFer,
            bombeArtisanale,
            tonneauDeRhum,
            sabordage,
            carteAuTresor,
            criDeGuerre,
            tirDeCanon,
            chantDesSirenes,
            abordage,
            tempêteEnMer,
            criDeLaKraken
        ));
    }

	@Override
	public List<ICarte> creerDeck() {
		// TODO
		return null;
	}

	@Override
	public void ajouterBundle(List<ICarte> bundle) {
		// TODO Auto-generated method stub
		
	}

}
