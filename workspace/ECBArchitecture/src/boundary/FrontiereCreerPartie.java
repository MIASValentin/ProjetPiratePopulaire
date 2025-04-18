package boundary;

import Control.ControlCreerDeck;
import Control.ControlCreerPirate;
import Control.ControlDonnerCarte;

public class FrontiereCreerPartie {
	private ControlCreerPirate controlCreerPirate = new ControlCreerPirate();
	private ControlDonnerCarte controlDonnerCarte = new ControlDonnerCarte();
	private ControlCreerDeck controlCreerDeck = new ControlCreerDeck();
	private FrontiereTourDeJeu frontiereTourDeJeu;
	
	public void lancerPartie() {
		System.out.println("Bienvenue dans cette partie !");
		controlCreerPirate.creerPirate();
		controlCreerDeck.creerDeck();
		controlDonnerCarte.donnerCarte(4);
		frontiereTourDeJeu = new FrontiereTourDeJeu(controlCreerPirate);
		frontiereTourDeJeu.tourDeJeu();
	}
}
