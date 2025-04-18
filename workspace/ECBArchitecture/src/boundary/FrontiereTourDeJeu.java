package boundary;

import Control.ControlChoisirBundle;
import Control.ControlCreerDeck;
import Control.ControlEffetTour;
import Control.ControlFinDeJeu;
import Control.ControlJouerCarte;
import Control.ControlMelangerCarte;
import Control.ControlPiocherCarte;

public class FrontiereTourDeJeu {
	private ControlPiocherCarte controlPiocherCarte;
	private ControlJouerCarte controlJouerCarte;
	private ControlCreerDeck controlCreerDeck;
	private ControlFinDeJeu controlFinDeJeu;
	private ControlChoisirBundle controlChoisirBundle;
	private ControlMelangerCarte controlMelangerCarte;
	private ControlEffetTour controlEffetTour;
	int tour = 0;
	int nbCarteJouee = 0;
	boolean bateauJoue = false;
	
	public FrontiereTourDeJeu(
			ControlPiocherCarte controlPiocherCarte,
			ControlJouerCarte controlJouerCarte,
			ControlCreerDeck controlCreerDeck,
			ControlFinDeJeu controlFinDeJeu,
			ControlChoisirBundle controlChoisirBundle,
			ControlMelangerCarte controlMelangerCarte,
			ControlEffetTour controlEffetTour) 
	{
		this.controlPiocherCarte = controlPiocherCarte;
		this.controlJouerCarte = controlJouerCarte;
		
	}
	
	public void tourDeJeu() {
		while(controlFinDeJeu.conditionsVictoire()) {
			controlCreerDeck.creerBundle();
			System.out.println("Choisir un bundle :");
			controlChoisirBundle.selectionnerBundle();
			controlMelangerCarte.melangerDeck();
			controlPiocherCarte.piocherCarte(4);
			controlEffetTour.appliquerEffetDebut();
			while(nbCarteJouee < 2) {
				System.out.println("Action(s) restante(s): " + (2-nbCarteJouee));
				controlJouerCarte.jouerCarte();
				nbCarteJouee++;
			}
			while(!bateauJoue) {
				System.out.println("Jouer un bateau");
				controlJouerCarte.jouerBateau();
				bateauJoue = true;
			}
			controlEffetTour.appliquerEffetFin();
			controlMelangerCarte.melangerMain();
			tour++;
			nbCarteJouee = 0;
			bateauJoue = false;
			System.out.println("Fin de tour");
		}
		
		System.out.println("Est le gagnant est : " + controlFinDeJeu.afficherGagnant());
	}

}
