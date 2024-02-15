package modele;
/**
* la classe pouvoirCarte permet d'avoir la description du pouvoir de chaque carte 
* elle contient des méthode qui renvoient à chaque fois une chaine de caractère qui esr le pouvoir de la carte
* @author diffo diffo brian- Adrake Dorcas 
* 
*
*/

import java.io.Serializable;

public class PouvoirCarte implements Serializable {
	/**instanciation d'un objet pouvoir carte */
	public PouvoirCarte() {
		
	}
	/**
	 * pour chacune des méthode , elles ont le nom de la carte corresponante et retourne la description du pouvoir de la carte */
	
	public String transmigration() {
		String pouvoir="Placez dans votre Main n’importequelle\r\n"
				+ "carte de votre Vie Future.";
		return pouvoir;
	}
	
	public String reveBrise() {
		String pouvoir="Placez la première carte de la Vie Future\r\n"
				+ "d'un rival sur la vôtre.";
		return pouvoir;
		
	}
		
	public String duperie() {
		String pouvoir="Regardez 3 cartes de la Main d’un\r\n"
				+ "rival ; ajoutez-en une à votre Main.";
		return pouvoir;
	}
	
	public String vol() {
		String pouvoir="Placez dans votre Main\r\n"
				+ "l’Oeuvre Exposée d'un rival.";
		return pouvoir;
	}
	
	public String lendemain() {
		String pouvoir="Puisez une carte à la Source.\r\n"
				+ "Vous pouvez ensuite jouer une autre carte.";
		return pouvoir;
	}
	
	public String crise() {
		String pouvoir="Le rival de votre choix défausse\r\n"
				+ "une de ses Oeuvres";
		return pouvoir;
	}
	
	public String coupOeil() {
		String pouvoir="Regardez la Main d’un rival.\r\n"
				+ "Vous pouvez ensuite jouer une autre carte";
		return pouvoir;
	}
	
	public String destinee() {
		String pouvoir="Regardez les 3 premières cartes de la\r\n"
				+ "Source ; ajoutez-en jusqu’à 2 à votre\r\n"
				+ "Vie Future. Replacez le reste dans\r\n"
				+ "l'ordre souhaité";
		return pouvoir;
	}
	
	public String longevite() {
		String pouvoir="ajoutez deux cartes de la source à la pile de votre rival";
		return pouvoir;
	}
	
	public String voyage() {
		String pouvoir="Puisez 3 cartes à la Source.\r\n"
				+ "Vous pouvez ensuite jouer une autre carte.";
		return pouvoir;
	}
	
	public String semis() {
		String pouvoir="Puisez 2 cartes à la Source, puis placez\r\n"
				+ "sur votre Vie Future\r\n"
				+ "2 cartes de votre Main";
		return pouvoir;
	}
	
	
	




}
