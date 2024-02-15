package modele;
/**
* La classe carte permet de décrire une carte du jeu avec toutes ses caractéristique. 
* la methode usePouvoir et computerUsePouvoir son des méthodes qui seront surchargées lors de la création de chaque carte 
* car pour chaque carte on a un pouvoir différent, c'est le principe des classes anonymes 
* @author diffo diffo brian- Adrake Dorcas 
* 
*
*/
import java.io.Serializable;

public class Carte implements Serializable{
	/**
	 * couleur: la couleur de la carte 
	 * nbPoint: le nombre de point d'une carte
	 * description: la description d'une carte
	 * pouvoir: la description du pouvoir d'une carte
	 * path: le chemin vers l'image de la carte correspondante*/
	private static final long serialVersionUID = 1L;
	private EnumCouleur couleur;
	private int nbPoint;
	private String description;
	public String path;
	private String pouvoir;
	/**instanciation d'une nouvelle caret 
	 * @param couleur, nbpOInt, description, path, pouvoir */
	public Carte(EnumCouleur couleur, int nbPoint, String description, String path, String pouvoir) {
		this.couleur=couleur;
		this.nbPoint=nbPoint;
		this.description=description;
		this.path=path;
		this.pouvoir=pouvoir;
			}
	
	
	/**cette méthode permet de récupérer la couleur d'une carte qui est de type enum
	 * @return couleur*/
	public EnumCouleur getCouleur() {
		return this.couleur;
	}
	
	
	public String getPouvoir() {
		return this.pouvoir;
	}
	
	
	/**cette méthode renvoit le nombre de point d'une carte
	 * @return nbPoint*/
	public int getnbPoint() {
		return this.nbPoint;
	}
	
	
	/**cette méthode renvoie le nom d'une carte
	 * @return description*/
	public String getDescription() {
		return this.description;
	}
	
	
	public String toString() {
		//return "description: "+this.description+", pouvoir: "+", nbPoint: "+this.nbPoint;
		return ">"+this.description+"<";
	}
	
	/**cette méthode permet à un joueur physique d'utiliser le pouvoir d'une carte */
	public void usePouvoir(Joueur user, Joueur victim) {
		System.out.println("i am using it");
		
	}
	
	
	/**cette méthode permet au joueur virtuel d'utiliser le pouvoir d'une carte */
	public void computerUsePouvoir(JoueurVirtuel user, Joueur victim) {
		System.out.println("la machine utilise un pouvoir ");
	}
	
	
	
	
	

}
