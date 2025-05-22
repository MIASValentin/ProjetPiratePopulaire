package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;
import java.util.Random;

public class DeckMutant2 {

	private List<Carte> cartes;
	private Random rd = new Random(); 

	
	public DeckMutant2() {
		List<Carte> allCards = creerCartes();
		Collections.shuffle(allCards);
		this.cartes = allCards.subList(0, 5); 
		
	}
	
	public int getNbCarte() {
	    return cartes.size();
	}

	
	public List<Carte> getCartes() {
		return cartes; 
	}
	
	public void setCartes(List<Carte> cartes) {
		this.cartes = cartes;
	}
	
	public void afficherPioche() {
	    System.out.println("Contenu de la pioche :");
	    for (Carte c : cartes) {
	        System.out.println("- " + c.getNom());
	    }
	}
	
	public List<ArrayList<Carte>> creerBundle(int nbCartesBundle, int nbBundle) {
    	List<Carte> nouvellesCartes = creerCartes();
    	List<Carte> cartesBundle = Stream.generate(()->nouvellesCartes.
    			get(rd.nextInt(nouvellesCartes.size()-1)))
    			.limit(nbCartesBundle*nbBundle).toList();
    	
    	List<ArrayList<Carte>> rslt = Stream.of(new ArrayList<Carte>(), new ArrayList<Carte>(), new ArrayList<Carte>()).toList();
    	
    	for (int i=0; i<nbCartesBundle*nbBundle; i++) {
    		rslt.get(i/nbBundle).add(cartesBundle.get(i));
    	}
    	
    	return rslt;
    }
	
	public Carte donnerCarte() {
	    if (!cartes.isEmpty()) { //changer isEmpty() en !isEmpty()
	        return null;
	    }
	    Carte carteAleatoire = cartes.get(ThreadLocalRandom.current().nextInt(cartes.size()));
	    cartes.remove(carteAleatoire); 
	    return carteAleatoire;
	}
	
	public List<Carte> creerCartes() {

        Carte discoursInspirant = new EffetInstantane(
            TypeCarte.POPULARITE,
            "Discours Inspirant",
            "Augmente votre prime de 1.",
            (joueur, adversaire) -> {
                joueur.changerPrime(1);
            }
        );

        Carte mainDeFer = new EffetInstantane(
            TypeCarte.POPULARITE,
            "Main de Fer",
            "Gagne 2 de prime, mais perd 1 point de vie.",
            (joueur, adversaire) -> {
                joueur.changerPv(-1);
                joueur.changerPrime(2);
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
                if (joueur.getPv() > 10) joueur.setPv(10); 
            }
        );

        Carte sabordage = new EffetInstantane(
            TypeCarte.ATTAQUE,
            "Sabordage",
            "Inflige 3 points de dégâts à l’adversaire, mais vous perdez 1 de prime.",
            (joueur, adversaire) -> {
                adversaire.changerPv(-3);
                joueur.changerPrime(-1);
            }
        );

        Carte carteAuTresor = new EffetInstantane(
            TypeCarte.POPULARITE,
            "Carte au Trésor",
            "Gagnez 3 de prime.",
            (joueur, adversaire) -> {
                joueur.changerPrime(3);
            }
        );

        Carte criDeGuerre = new EffetInstantane(
            TypeCarte.SOUTIEN,
            "Cri de Guerre",
            "Vous gagnez 1 PV et 1 de prime.",
            (joueur, adversaire) -> {
                joueur.changerPv(1);
                joueur.changerPrime(1);
                if (joueur.getPv() > 10) joueur.setPv(10); 
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
                adversaire.changerPrime(-1);
            }
        );

        Carte abordage = new EffetInstantane(
            TypeCarte.ATTAQUE,
            "Abordage",
            "Inflige 2 dégâts à l’adversaire et vole 1 de prime.",
            (joueur, adversaire) -> {
                adversaire.changerPv(-2);
                adversaire.changerPrime(-1);
                joueur.changerPrime(1);
            }
        );

      
        Carte tempeteEnMer = new EffetInstantane(
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
        
        Carte volDIdentite = new EffetInstantane(
        		TypeCarte.POPULARITE,
        		"Vol d'Identité",
        		"Copie la prime de votre adversaire et laisse la sienne à zéro",
        		(joueur, adversaire) -> {
        			joueur.setPrime(adversaire.getPrime());
        			adversaire.setPrime(0);
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
            tempeteEnMer,
            criDeLaKraken,
            volDIdentite
        ));
    }

	public void melangerCartes() {
		Collections.shuffle(cartes);
	}

	
}
