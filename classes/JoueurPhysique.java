package modele;

/**
* la classe joueurPhysique hérite de la classe joueur et implemente les méthode qui son propres à un player physique
* il s'agit de la méthode de jeux Play()  et la méthode de réincarnation  reincarnation()
*
* cette classe implemente l'interface stratégie
*
* @author diffo diffo brian- Adrake Dorcas 
* 
*
*/

import java.io.Serializable;
import java.util.*;

import javax.swing.JOptionPane;

public class JoueurPhysique extends Joueur implements Strategie, Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * carteJouee: représente la carte que le joueur va sélectionner pour jouer à  chaque fois */
	private Carte carteJouee;
	
	/**instanciation d'un joueur physique*/
	public JoueurPhysique(String nomJoueur) {
		super(nomJoueur);
	}
	
	
	
	/**cette méthode permet mettre à  jour la carte que le joueur a décidé de jouer 
	 * @param carte la cartejouee*/
	public void setCarte(Carte carte) {
		this.carteJouee=carte;
	}
	
	/**cette méthode permet de récupérer la carte que le joueur a décidé de jouer
	 * @return cartejouee la carte jouee*/
	public Carte getCarteJoue() {
		return this.carteJouee;
		
	}
	
	
	
	
	/**cette méthode  permet  au joueur physique de se réincarner pour passer à l'état 
	 * supérieur ou rester au même état
	 * si le joueur a assez de  point pour se réincarnner il passe à l'état suivant 
	 * sinon il reste dans son état*/
	public void reincarnation() {
		/**dans le cas ou le joueur a  assez de point pour passer au niveau supérieur*/
		if(this.calculMaxPointOeuvre()>=this.objectif) {
			this.etatJoueur=this.etatSuivant;
			this.etatSuivant=etatSuivant.getNextState(etatSuivant);
			if(this.etatJoueur==EnumEtat.singe) {
				this.winned=true;
			}
			List<Carte> vieFuture=this.getVieFuture();
			this.ajouterCarteMain(vieFuture);
			this.vieFuture.removeAll(vieFuture);
			this.defausserOeuvre();
			
			if(!this.listeAnneaux.isEmpty()) {
				this.listeAnneaux.remove(0);
			}
			/**si le joueur  n'a pas assez de carte pour se constituer sa main , on  complète avec la source*/
			if(this.main.size()<4) {
				int complete=4-this.main.size();
				while(this.main.size()<4) {
					this.ajouterCarteMain(Partie.piocherSource());
				}
			}
			/**si le joueur a assez de carte pour consituer sa main */
			if(this.main.size()<6) {
				int comp=6-this.main.size();
				for(int i=0;i<comp;i++) {
					this.ajouterCartePile(Partie.piocherSource());
				}
			}
			
			this.setChanged();
			this.notifyObservers();
		
			
			JOptionPane.showMessageDialog(null,"Bravo vous venez de vous reincarner en  "+this.getstate(), "Information", JOptionPane.INFORMATION_MESSAGE);
			
		}
		/**si le joueur n'a pas assez de points pour passer au niveau suivant */
		else {
			AnneauxKarmic anneau=new AnneauxKarmic();
			this.listeAnneaux.add(anneau);
			List<Carte> vieFuture=this.getVieFuture();
			this.ajouterCarteMain(vieFuture);
			this.vieFuture.removeAll(vieFuture);
			this.defausserOeuvre();
			/**si le joueur  n'a pas assez de carte pour se constituer sa main , on  complète avec la source*/
			if(this.main.size()<4) {
				int complete=4-this.main.size();
				while(this.main.size()<4) {
					this.ajouterCarteMain(Partie.piocherSource());
				}
			}
			/**si le joueur a assez de carte pour consituer sa main */
			if(this.main.size()<6) {
				int comp=6-this.main.size();
				for(int i=0;i<comp;i++) {
					this.ajouterCartePile(Partie.piocherSource());
				}
			}
			this.setChanged();
			this.notifyObservers();
			JOptionPane.showMessageDialog(null,"vous n'avez pas pu vous reincarner, vous allez donc rester "+this.getstate(), "Information", JOptionPane.INFORMATION_MESSAGE);
			
		}
		
	}
	
	
	
	/**methode qui envoit un premier signal à l'interface graphique pour afficher les informations au début du jeu*/
	public void observation() {
		System.out.println("je suis obervé");
		
		this.setChanged();
		this.notifyObservers();
	}
	
	
	
	
	
	
	
	/**grace à cette méthode , le joueur peux jouer en fonction de son choix
	 * @param coup le choix de jeu du joueurS*/
	public Carte play(String coup) {
		/**cas ou le joueur n'a de carte ni dans sa pile ni dans sa main , on passe à la réincarnation*/
		if(this.checkMain()==false && this.checkPile()==false) {
			if(this.calculMaxPointOeuvre()>=this.getObjectif()) {
				System.out.println("***********************bravo vous allez vous reincarnner**************************");
				this.reincarnation();
				System.out.println("********************a présent votre nouvel état est "+this.getstate()+"**********************************");
				this.setObjectif();
				this.setMain(this.getVieFuture());
				
				
			}
		
			
			
		}
		
		/**cas ou le joueur a au moins une carte dans sa pile ou dans sa main*/
		else {
			boolean actionPioche=false;
			if(this.checkPile()==true) {//pioche une carte dans la pile si il y'en a
				//this.ajouterCarteMain(this.piocherPile());
				actionPioche=true;
			}
			
			//le joueur joue son tour
			Joueur victim=Partie.getInstancePartie(null, null).getComputer();
			
			
			
			
			/**appele la méthode de jeuc correspondant au coup passé en paramètre par le joueur */
			switch(coup) {
			case "Oeuvre":
				this.jouerOeuvre(this.carteJouee);
				this.havePlayed=true;
				break;
			case "vieFuture":
				this.jouerVieFuture(this.carteJouee);
				this.havePlayed=true;
				break;
			case "pouvoir":
				this.jouerPouvoir(this.carteJouee, victim, this);
				
				
				break;
			}
			
			
			System.out.println("/*******vous avez joué votre tour*******/");
			
		}
		
		this.setChanged();
		this.notifyObservers();
		
		
		
		
		
		
		return null;
	}

}
  

