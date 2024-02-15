package modele;
/**
* La classe joueur permet de modéliser toutes les information d'un joueur virtuel ou physique et des action communes
* qu'ils peuvent exécuter.
*
* celle si est observable par notre interface graphiue pour pouvoir afficher ces informations en temps voulu
*
* @author diffo diffo brian- Adrake Dorcas 
* 
*
*/

import java.io.Serializable;
import java.util.*;

public class Joueur extends Observable implements Serializable{
	/**
	 * nomJoueur: représente le nom d'un joueur 
	 * main: représente les cartes constituant la main d'un joueur 
	 * pile , oeuvre , vieFuture:  ensembles des cartes de la  pile , la vie future et l'oeuvre du joueur 
	 * etatJoueur: le niveau du joueur sur l'échelle karmique
	 * etatSuivant: le prochain niveau que le joueur doit atteindre
	 * objectif: le nombre de point nécessaire pour passer au niveau suivant
	 * havePlayed: booléen qui dit si un joueur a joué ou non 
	 * canPlay: booléen qui dit si un joueur peut jouer ou non*/
	private static final long serialVersionUID = 1L;
	final private String nomJoueur;
	public ArrayList<Carte> main;
	protected LinkedList<Carte> pile;
	protected ArrayList<Carte> oeuvre;
	protected ArrayList<Carte> vieFuture;
	protected EnumEtat etat=EnumEtat.Bousier;
	protected EnumEtat etatJoueur=etat.Bousier;
	protected EnumEtat etatSuivant=etat.serpent;
	protected int objectif=4;
	protected boolean winned=false;
	public List<AnneauxKarmic> listeAnneaux=new ArrayList<AnneauxKarmic>();
	
	protected boolean havePlayed=false;   //variable qui correpsond à  si un joeur a fini son tour ou non
	protected boolean canPlay=true;  //correspond à si le joueur peut jouer son tour ou non
	
	

	/**instancie un nouveau joueur
	 * @param nomJoueur le nom du joueur créé */
	public Joueur(String nomJoueur) {
		this.nomJoueur=nomJoueur;
		this.etatJoueur=EnumEtat.Bousier;
		this.main= new ArrayList<Carte>();
		this.pile=new LinkedList<Carte>();
		this.oeuvre=new ArrayList<Carte>();
		this.vieFuture=new ArrayList<Carte>();
		
	}
	
	
	/** cette méthode permet de renvoyer la valeur de l'attribut winned du joueur
	 * @return l'attribut winned*/
	public boolean getWinned() {
		return this.winned;
	}
	
	
	
	/**cette méthode change la valeur de l'attribut winned*/
	public void setWinned() {
		this.winned=true;
	}
	/**setter de nomJoueur */
	public String getName() {
		return this.nomJoueur;
	}
	
	
	/**cette méthode  permet de récuperer l'attribut objectif (le nombre de points pour changer d'etat
	 * @return objectif */
	public int getObjectif() {
		return this.objectif;
	}
	
	/**récuperer l'état du joueur
	 * @return etatJoueur le niveau du joueur */
	public EnumEtat getstate() {
		return this.etatJoueur;
	}
	
	
	/**récupérer la main d'un joueur
	 * @return main , retiurne la main du joueur*/
	public List<Carte> getMain(){
		return this.main;
	}
	
	
	/**récupérer la pile d'un joueur
	 * @return pile*/
	public List<Carte> getPile(){
		return this.pile;
	}
	
	
	/**récupérer la vie future d'un joueur
	 * @return vieFuture*/
	public List<Carte> getVieFuture(){
		return this.vieFuture;
	}
	
	
	/**récupérer l'oeuvre d'un joueur
	 * @return l'oeuvre */
	public List<Carte> getOeuvre(){
		return this.oeuvre;
	}
	
	
	
	/**récupérer la valeur de l'attribut havePlayed*/
	public boolean gethavePlayed() {
		return this.havePlayed;
	}
	/**récupérer la valeur de l'attribut canPlay*/
	public boolean getcanPlay() {
		return this.canPlay;
	}
	
	
	/**modifier la valeur de l'attribut havePlayed*/
	public void sethavePlayed(boolean arg) {
		this.havePlayed=arg;
	}
	
	
	
	
	/**change la valeur de l'attribut canPlay
	 * @param boolean*/
	public void setcanPlay(boolean arg) {
		this.canPlay=arg;
	}
	
	
	
	
	/**change la valeur de l'attribut objectif
	 * */
	public void setObjectif() {
		this.objectif+=1;
	}
	
	public void setMain(List<Carte> listeCarte) {
		this.main.addAll(listeCarte);
	}
	
	
//=========================================================recuperation d'éléments dans les decks du joueur===================================//
	
	
	/**cette méthode permet de récupérer une carte dans la main d'un joueur pour un indice i donné 
	 * @param indice de la carte 
	 * @return la carte à l'indice précisé*/
	public Carte recupererCarteMain(int i) {
		Carte carte;
		carte=this.main.get(i);
		this.main.remove(i);
		this.setChanged();
		this.notifyObservers();
		return carte;
	}
	
	
	
	/** cette méthode permet de récupérer une carte dans la pile du joueur pour un indice donné
	 * @param indice de la carte voulue
	 * @return la carte pour l'indice précisé */
	public Carte recupererCartePile(int i) {
		Carte carte;
		carte=this.pile.get(i);
		this.pile.remove(i);
		return carte;
	}
	
	
	/**cette méthode permet de récupérer une carte dans l'oeuvre d'un joueur pour un indice donné
	 * @param indice de la carte voulue
	 * @return la carte pour l'indice précisé  */
	public Carte recupererCarteOeuvre(int i) {
		Carte carte;
		carte=this.oeuvre.get(i);
		this.oeuvre.remove(i);
		this.setChanged();
		this.notifyObservers();
		return carte;
	}
	
	
	/**cette méthode permet de récupérer une carte dans la vie future d'un joueur pour un indice donné 
	 * @param indice de la carte voulue
	 * @return la carte pour l'indice précisé */
	public Carte recupererCarteVieFuture(int i) {
		Carte carte;
		carte=this.vieFuture.get(i);
		this.vieFuture.remove(i);
		this.setChanged();
		this.notifyObservers();
		return carte;
	}
	
	
	/**cette méthode permet de rétirer une carte de la vie future du joueur (carte spécifiée)
	 * @param indice de la carte a retirer
	 **/
	public void removeCarteVieFuture(Carte carte) {
		this.vieFuture.remove(carte);
		this.setChanged();
		this.notifyObservers();
	}
	
	
	/**cette méthode permet de vide toute l'oeuvre d'un joueur*/
	public void defausserOeuvre() {
		ArrayList<Carte> list=this.oeuvre;
		this.oeuvre.removeAll(list);
	}
	
//===========================================================ajout d'éléments dans les différents decks d'un joueur==============================//
	/**cette méthode permet d'ajouter une carte passée en paramètre dans la main du joueur 
	 * @param la carte à ajouter*/
	public  void ajouterCarteMain(Carte carte) {
		this.main.add(carte);
		this.setChanged();
		this.notifyObservers();
		
	}
	
	/**cette méthode permet d'ajouter une carte passée en paramètre dans la pile du joueur 
	 * @param la carte à ajouter*/
	public void ajouterCartePile(Carte carte) {
		this.pile.add(carte);
	}
	
	
	/**cette méthode permet d'ajouter une collection de carte passée en paramètre à  la pile d'un joueur 
	 * @param la liste de carte à ajouter*/
	public void ajouterCartePile(List<Carte> list) {
		this.pile.addAll(list);
	}
	
	
	/**cette méthode permet d'ajouter une carte passée en paramètre à l'oeuvre du joueur 
	 * @param la carte à ajouter*/
	public void ajouterCarteOeuvre(Carte carte) {
		this.oeuvre.add(carte);
	}
	
	/**cette méthode permet d'ajouter une carte dans la vie future  d'un joueur  , la carte passée en paramètre 
	 * @param la carte à ajouter*/
	public void ajouterCarteVieFuture(Carte carte) {
		this.vieFuture.add(carte);
	}
	
	
	/**cette méthode permet à  un joueur de rétirer une carte dans la pile et de l'ajouter à sa main
	 * @return la carte piochée dans la pile*/
	public Carte piocherPile() {
		Carte carte=this.pile.poll();
		return carte;
		//this.setChanged();
		//this.notifyObservers();
	}
	
	
	/**cette méthode permet d'ajouter à la main d'un joueur une liste de carte passée en paramètre*
	 * @param la liste de  carte à ajouter*/
	public void ajouterCarteMain(List<Carte> list) {
		this.main.addAll(list);
		this.setChanged();
		this.notifyObservers();
		
		
	}
	
	/**méthode qui permet d'enlever la carte passée en paramètre de la main du joueur 
	 * @param la carte à retirer */
	public void retirerCarteMain(Carte carte) {
		this.main.remove(carte);
		this.setChanged();
		this.notifyObservers();
	}

//======================================================methodes correspondant aux différents jeux possibles==================================//
	
	/**cette méthode permet  à  un joueur de jouer la carte passée en paramètre pour sa vie future
	 * @param carte que le joueur veut jouer
	 * @return carte jouee*/
	public Carte jouerVieFuture(Carte carteJoue) {
		this.main.remove(carteJoue);
		this.vieFuture.add(carteJoue);
		
		return carteJoue;
	}
	/**cette méthode permet  à  un joueur de jouer la carte passée en paramètre pour son oeuvre
	 * @param carte que le joueur veut jouer
	 * @return carte jouee*/
	public Carte jouerOeuvre(Carte carteJoue) {
		
		this.main.remove(carteJoue);
		this.oeuvre.add(carteJoue);
		//this.setChanged();
		//this.notifyObservers();
		return carteJoue;
	}
	
	/**cette méthode appele la méthode jouer pouvoir de la carte passée en paramètre pour un jeux pouvoir
	 * @param carte que le joueur veut jouer
	 */
	public Carte jouerPouvoir(Carte carteJoue, Joueur victim, Joueur user) {
		this.main.remove(carteJoue);
		carteJoue.usePouvoir(user, victim);
		
		return carteJoue;
	}

//===========================================méthode support pour la stratégie de jeux========================================================//
	
	/** calcul du nombre de point par couleur pour l'oeuvre d'un joueur
	 * @return map<couleur, nbPoint> le nombre de point pour chaque couleur*/
	public Map<String, Integer> calculPointOeuvre() {
		/**
		 * it :iterrateur de l'oeuvre du joueur
		 * rouge, bleu, vert, mosaique: le nombre de point pour les couleurs correspondantes*/
		Iterator<Carte> it=this.oeuvre.iterator();
		int rouge=0;
		int bleu=0;
		int vert=0;
		int mosaique=0;
		while(it.hasNext()) {
			Carte carte=it.next();
			if(carte.getCouleur()==EnumCouleur.Bleu) {
				bleu+=carte.getnbPoint();
			}
			if(carte.getCouleur()==EnumCouleur.Rouge) {
				rouge+=carte.getnbPoint();
			}
			
			if(carte.getCouleur()==EnumCouleur.Vert) {
				vert+=carte.getnbPoint();
			}
			
			if(carte.getCouleur()==EnumCouleur.Mosaique) {
				mosaique+=carte.getnbPoint();
			}
			
		}
		/**pointTable: hashmap qui va contenir les point pour chaque couleur , la couleur entend que clé et les point comme valeur */
		HashMap<String, Integer> pointTable=new HashMap<String, Integer>();
		pointTable.put("point Bleu", bleu);
		pointTable.put("point Rouge", rouge);
		pointTable.put("point Vert", vert);
		pointTable.put("point Mosaique", mosaique);
		return pointTable;
		
	}
	/**calcul du maximum de point pour une couleur de l'oeuvre au cas ou le joueur veut se réincarner
	 * @return entier , nombre de point max */
	public int calculMaxPointOeuvre() {
		/**pointOeuvre: hashmap retournée par la methode calculPointOeuvre()*/
		Map<String, Integer> pointOeuvre=this.calculPointOeuvre();
		HashSet<String> keys=new HashSet<String>();
		keys.addAll(pointOeuvre.keySet());
		int max=0;
		for(String key:keys) {
			if(pointOeuvre.get(key)>max) {
				max=pointOeuvre.get(key);
			}
		}
		
		System.out.println(pointOeuvre.toString());
		
		max=max+pointOeuvre.get("point Mosaique");
		max=max+this.listeAnneaux.size();
		return max;
		
	}
		
	/**vérification de la main
	 * @return boolean correspondant à l'etat vide ou non vide de la main*/
	public boolean checkMain() {
		if(this.main.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**vérification de la pile
	 * @return boolean correspondant à l'etat vide ou non vide de la pile*/
	public boolean checkPile() {
		if(this.pile.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	
	
	
//======================================methodes d'afficahges des différentes  informations pour un joueur=================================//
	
	
	/**cette méthode renvoit une chaine de caractère avec les information de la main d'un joueur
	 * @return stringbuilder : l'ensemble des cartes de la main*/
	public StringBuilder afficherMain() {
		
		/**
		 * result: va contenir le toString des carte de la main
		 * iterator: l'itterateur de parcours de la main*/
		
		StringBuilder result=new StringBuilder();
		
		Iterator<Carte> iterator=this.main.iterator();
		
		int indice=0;
		
		while(iterator.hasNext()) {
			Carte carte=iterator.next();
			
			if (carte != null) {
		        result.append("<<<<" + indice + " " + carte.getDescription() + ">>>\n");
		        indice++;
		    }
			
		}
		return result;
	}
	
	/**cette méthode renvoit une chaine de carractère avec les information de l'oeuvre d'un joueur 
	 * @return stringbuilder : l'ensemble des cartes de l'oeuvre*/
	public StringBuilder afficherOeuvre() {
		/**
		 * result: va contenir le toString des carte de la main
		 * iterator: l'itterateur de parcours de l'oeuvre*/
		StringBuilder result=new StringBuilder();
		
		Iterator<Carte> iterator=this.oeuvre.iterator();
		int indice=0;
		
		while(iterator.hasNext()) {
			
			result.append("<<<<"+indice+" "+iterator.next().getDescription()+">>>>\n");
			indice++;
			
		}
		return result;
		
	}
	
	
	/**cette fonction renvoit une chaine de caractère avec les informations de la vie future d'un joueur 
	 * @return stringbuilder : l'ensemble des cartes de la vie future*/
	public StringBuilder afficherVieFuture() {
		/**
		 * result: va contenir le toString des carte de la main
		 * iterator: l'itterateur de parcours de la vie future*/
		StringBuilder result=new StringBuilder();
		
		Iterator<Carte> iterator=this.vieFuture.iterator();
		int indice=0;
		
		while(iterator.hasNext()) {
			
			result.append("<<<<"+indice+" "+iterator.next().getDescription()+">>>>\n");
			indice++;
			
		}
		return result;
		
	}
	
	
	/**cette methode toString comporte toute les informations sur un joueur
	 * @return string */
	public String toString() {
		StringBuilder result=new StringBuilder();
		result.append("\n-----------affichage de la main-----------\n");
		result.append(afficherMain().toString());
		
		//result.append("\n-----------affichage de la pile-----------\n");
		//result.append(pile.toString());
		
		
		result.append("\n-----------affichage de l'oeuvre-----------\n");
		result.append(afficherOeuvre().toString());
		
		result.append("\n-----------affichage de la vie future-----------\n");
		result.append(afficherVieFuture().toString());
		result.append("===================les maximums de points pour chaque couleur========================\n"+this.calculPointOeuvre().toString()+"\n#=====================================================#");
		
		return result.toString();
	}
	
		
	
	
	
	
}
