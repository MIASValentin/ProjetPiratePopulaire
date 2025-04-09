package InterfaceNF;

import java.util.List;

public interface IPirate {
	public static int PRIMEMAX = 1000000;
	
	public List<ICarte> getMain();
	public int getPv();
	public int getPrime();
}
