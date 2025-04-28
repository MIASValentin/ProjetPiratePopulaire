package InterfaceNF;

import java.util.List;

public interface IFabriqueDeCarte {
	public List<ICarte> creerDeck();
	
	public void ajouterBundle(List<ICarte> bundle);
}
