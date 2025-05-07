package boundary;

import java.util.ArrayList;
import java.util.List;

import control.ControlChoisirBundle;
import entities.Carte;
import entities.Pirate;

public class BoundaryChoisirBundle {

	public void selectionnerBundle(List<ArrayList<Carte>> lBundle, Pirate joueur, int  choix) {
		System.out.println("vous avez choisi le bundle "+ choix +
				", nous allons maintenant l'ajouter a votre deck !");
		ControlChoisirBundle t = new ControlChoisirBundle();
		t.selectionnerBundle(lBundle, joueur, choix);
	};
}
