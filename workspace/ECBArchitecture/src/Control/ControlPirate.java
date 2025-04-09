package Control;
import InterfaceNF.IPirate;

public class ControlPirate {
	
	public boolean verifierMainJoueur(IPirate joueur) {
		return joueur.getMain().size() == 4;
	}
	
	public boolean verifierPvJoueur(IPirate joueur) {
		return joueur.getPv() > 0;
	}
	
	public boolean verifierPrimeJoueur(IPirate joueur) {
		return joueur.getPrime() >= IPirate.PRIMEMAX;
	}
}
