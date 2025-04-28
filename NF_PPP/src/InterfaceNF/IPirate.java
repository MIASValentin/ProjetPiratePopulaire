package InterfaceNF;

import java.util.List;

import entities.Pioche;

public interface IPirate {
	public static int PRIMEMAX = 1000000;
	
	public void piocherCarte(Pioche pioche, int nbCarteAPiocher);
	public void jouerCarte();
	public void choisirDeck();
}
