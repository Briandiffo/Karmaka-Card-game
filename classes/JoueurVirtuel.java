package modele;
/**
* la classe joueurVirtuel hérite de la classe joueur et implemente les méthode qui son propres à un player physique
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

public class JoueurVirtuel extends Joueur implements Strategie, Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * carteJouee: représente la carte que le joueur va sélectionner pour jouer à  chaque fois */
	private Carte carteJouee;
	
	public void observation() {
		System.out.println("je suis obervét");
		this.setChanged();
		this.notifyObservers();
	}
	
	
	/**cette méthode permet mettre à  jour la carte que le joueur a décidé de jouer 
	 * @param carte la cartejouee*/
	
	public void setCarteJoue(Carte carte) {
		this.carteJouee=carte;
	}
	
	/**cette méthode permet de récupérer la carte que le joueur a décidé de jouer
	 * @return cartejouee la carte jouee*/
	public Carte getCarteJoue() {
		return this.carteJouee;
	}
	/**instanciation d'un nouveau joueur virtuel
	 * @param nomJoueur le nom du joueur*/
	public JoueurVirtuel(String nomJoueur) {
		super(nomJoueur);
		
	}
	
	@Override
	/**le joueur virtuel joue pour le pouvoir d'une carte 
	 * @param carteJouee la carte que le joueur decide de jouer 
	 * @param victim: le joueur sur lequel la carteJouee applique ses effets
	 * @param user: l'utilisateur du pouvoir de la carte
	 * */
	public Carte jouerPouvoir(Carte carteJoue, Joueur victim, Joueur user) {
		this.main.remove(carteJoue);
		carteJoue.computerUsePouvoir(this, victim);
		return carteJoue;
		
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
			this.sethavePlayed(true);
			this.setChanged();
			this.notifyObservers();
			
			
			
			JOptionPane.showMessageDialog(null," message , l'ordinateur vient de se réincarner en  "+this.getstate(), "Information", JOptionPane.INFORMATION_MESSAGE);
			
		}
		/**si le joueur n'a pas assez de points pour passer au niveau suivant */
		else {//si le joueur n'a pas assez de points pour se réincarner
			
			List<Carte> vieFuture=this.getVieFuture();
			this.ajouterCarteMain(vieFuture);
			this.vieFuture.removeAll(vieFuture);
			this.defausserOeuvre();
			if(this.main.size()<4) {
				int complete=4-this.main.size();
				while(this.main.size()<4) {
					this.ajouterCarteMain(Partie.piocherSource());
				}
			}
			if(this.main.size()<6) {
				int comp=6-this.main.size();
				for(int i=0;i<comp;i++) {
					this.ajouterCartePile(Partie.piocherSource());
				}
			}
			this.sethavePlayed(true);
			this.setChanged();
			this.notifyObservers();
			
			JOptionPane.showMessageDialog(null,"l'ordinateur n'a pas pu se réincarner et reste donc "+this.getstate(), "Information", JOptionPane.INFORMATION_MESSAGE);
			
		}
		
		
	}
	
	
	
	
	/**la method play permet au joueur Virtuel de jouer soit pour sa vie future , pour son oeuvre ou pour le pouvoir d'une carte
	 * de facon aléatoire 
	 * à chaque fois le joueur virtuel va jouer la première carte de sa main*/
	public Carte play(String coup) {
		try {
			Thread.sleep(3000);
			if(this.main.size()<=1 && this.pile.size()>=1) {
				Carte carte=this.piocherPile();
				this.ajouterCarteMain(carte);
			}
			
			if(this.main.isEmpty() && this.pile.isEmpty()) {
				this.reincarnation();
				this.sethavePlayed(true);
				
			}
			Random random=new Random();
			int a=random.nextInt(3) +1;
			this.setCarteJoue(main.get(0));
			if(a==1) {
				this.jouerOeuvre(this.main.get(0));
				this.sethavePlayed(true);
				this.setChanged();
				this.notifyObservers();
			}
			else if(a==3) {
				this.jouerVieFuture(this.main.get(0));
				this.sethavePlayed(true);
				this.setChanged();
				this.notifyObservers();
			}
			else if(a==2) {
				this.jouerPouvoir(this.main.get(0),Partie.player, this);
				
				this.setChanged();
				this.notifyObservers();
			}
			
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	

}
