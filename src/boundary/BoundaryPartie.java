package boundary;

import control.ControlPartie;
import entities.Pirate;

public class BoundaryPartie {
	//LancerPartie, 
	private Pirate joueur1; 
	private Pirate joueur2;
	
	private ControlPartie controlPartie;

	public BoundaryPartie(Pirate joueur1, Pirate joueur2, ControlPartie controlPartie) {
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.controlPartie = controlPartie;
	} 
	
	public void LancerPartie () {
		
	}
	
	
}
