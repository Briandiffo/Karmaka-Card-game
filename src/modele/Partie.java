package modele;
/**
* la classe controleur partie va s'occuper de lancer la partie selon deux cas:
* le cas ou on lance une nouvelle partie le controleur crèe un joueur virtuel, crée les différentes cartes , les distribue aux joueurx et lance la partie
* le cas ou on reprend une partie sauvegardée les information de la partie sont récupérées et on la relance là où  on s'était arrêté
* 
* 
*
*/
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.*;

import javax.swing.JLabel;

public class Partie implements Serializable{
	
	/**
	 * instancePartie: représente la seule instance de partie que l'on aura dans le jeu
	 * player: le joueur physique
	 * computerPlayer: le joueur virtuel
	 * source: la source du jeu
	 * winner: booléen qui définit si un joueur a gagné ou non
	 * listeAnneaux: la liste des anneaux karmique
	 * */
	private static final long serialVersionUID = 1L;
	private static Partie instancePartie;
	public static JoueurPhysique player;
	public static JoueurVirtuel computerPlayer;
	public static LinkedList<Carte> source;
	private ArrayList<Carte> fausse;
	private boolean winner=false;
	private Joueur playerWinner;
	private Joueur currentPlayer;
	public static List<AnneauxKarmic> listeAnneaux;
	
	
	/**constructeur de la classe partie
	 * @param player1: le joueur physique
	 * @param player2: le joueur virtuel*/
	private Partie(Joueur player1, Joueur player2) {
		this.player=(JoueurPhysique) player1;
		this.computerPlayer=(JoueurVirtuel) player2;
		this.source= new LinkedList<Carte>();
		this.fausse= new ArrayList<Carte>();
		this.listeAnneaux=CardFactory.createAnneaux();		
	}
	

	/**cette méthode permet d'instancier une partie si il n'en existe pas déja une et de renvoyer la partie existante*/
	public static Partie getInstancePartie(Joueur player1, Joueur player2) {
		if(instancePartie==null) {
			instancePartie=new Partie(player1, player2);
			return instancePartie;		
		}
		else {
			return instancePartie;
		}		
	}
	
	
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
	    ois.defaultReadObject();
	    instancePartie = this;
	    player=this.player;
	    computerPlayer=this.computerPlayer;
	    source=this.source;
	}
	
	
	/**cette méthode renvoie le joueur virtuel de la partie lancée
	 * @return computerPlayer: retourne le joueur virtuel*/
	public static Joueur getComputer() {
		return Partie.computerPlayer;
	}
	
	
	/**cette méthode permet au joueur passé en paramètre de piocher une carte à la source
	 * @return carte: la carte que le joueur aura pioché*/
	public Carte piocher(Joueur player) {
		Carte carte=source.poll();
		player.ajouterCarteMain(carte);
		return carte;	
	}
	
	
	/**cette méthode permet de retirer une carte à  la source */
	public static Carte piocherSource() {
		return Partie.source.poll();
	}
	
	
	/**cette méthode renvoie la source de la partie en cours */
	public List<Carte> getSource(){
		return this.source;
	}
	
	
	/**cette méthode permet d'ajouter une carte à la source*/
	public  void ajouterCarte(Carte carte) {
		this.source.add(carte);		
	}
	
	
	/**cette méthode est une surcharge permettant d'ajouter une collection de carte à la source*/
	public  void ajouterCarte(List<Carte> carte) {
		this.source.addAll(carte);
		
	}

	
	/**cette méthode permet de rétirer une carte à la source et de renvoyer la carte retirée*/
	public Carte retirerCarteSource() {
		return this.source.poll();
	}
	
	/**cette méthode permet de distribuer les cartes aux deux joueur au début de la partie
	 * compteur: c'est lui qui va permettre d'arreter le remplissage*/
	public void distribuerCarte() {
		int compteur =0;
		Collections.shuffle(source);	
		while(compteur<4) {
			Carte carte1=source.poll();
			Carte carte2=source.poll();
			this.player.ajouterCarteMain(carte1);
		    this.computerPlayer.ajouterCarteMain(carte2);
			compteur++;
			
			
		}
		compteur =0;
		
		/**remplissage des piles des fifférents joueurs*/
		while(compteur<2) {
			Carte carte1=source.poll();
			Carte carte2=source.poll();
			this.player.ajouterCartePile(carte1);
			this.computerPlayer.ajouterCartePile(carte2);
			compteur++;	
		}	
		
	}
	
	/**permet de vérfier si il y'a eu un gagnant
	 * @return false ou true celon qu'il y'ait un gagnant ou non*/
	public boolean checkWinner() {
		if(player.checkMain()==false) {
			return true;
		}
		return false;
	}
	
	
	public void changeCurrentPlayer() {
		if(currentPlayer.getName()==computerPlayer.getName()) {
			currentPlayer=player;
		}
		else if(currentPlayer.getName()==player.getName()) {
			currentPlayer=computerPlayer;
		}
	}
	
	/**cette méthode représente la mécanique de la partie , elle assure la possibilité pour chaque joueur de commencer et terminer son tour
	 * @param label: c'est le label qui va afficher le nom du joueur dont c'est le tour
	 * @param factory: va  permettre de créer les carte du  jeux
	 * après le tour de chaque joueur , la varaible havePlayed va passer à true pour signifier que le joueur a dééja joué
	 * pour un joueur physique , quand le joueur virtuel joura son tour , la variable canPlay passera à false */
	 public void deroulement(JLabel label, CardFactory factory) {
		 int c=0;
	        while (player.getWinned()!=true && computerPlayer.getWinned()!=true) {
	        	if(Partie.source.size()<=3) {
	        		factory.remplissageCarte(Partie.source);
	        	}
	            // Joueur physique
	            player.setcanPlay(true);
	            
	            
	            while (!player.gethavePlayed()) {
	            	
	            	label.setText("vous pouvez jouer");
	                
	                
	            }
	            label.setText("c'est au tour de la machine");
	            player.setcanPlay(false);
	            player.sethavePlayed(false);
	            if (player.getWinned()) {
	                winner = true;
	                this.playerWinner=player;
	                
	                System.out.println(player.getName() + " a gagné !");
	                label.setText("partie terminée, gagnant: "+ player.getName());
	                break;
	            }
	            
	            

	            // Joueur machine
	            while (!computerPlayer.gethavePlayed()) {
	            	

	            	computerPlayer.play("coup");
	                
	                
	            }
	            
	            computerPlayer.sethavePlayed(false);
	            if (computerPlayer.getWinned()) {
	                winner = true;
	                System.out.println(computerPlayer.getName() + " a gagné !");
	    
	                this.playerWinner=computerPlayer;
	                label.setText("partie terminée , gagnant: "+ computerPlayer.getName());
	                
	                break;
	            }
	            
	            c+=1;
	        }
	        this.winner=true;
	        
	        
	    }

	
	 
	public static void main(String[] args) {
		CardFactory factory=CardFactory.getInstance();
		
		System.out.println("une modification");
		
		JoueurPhysique player1=new JoueurPhysique("brian");
		Joueur player2=new Joueur("charlet");
		
		Partie partie=Partie.getInstancePartie(player1, player2);
		
		
		factory.remplissageCarte(partie.source);
		
		
		partie.distribuerCarte();
		
		//affichage de la main d'un joueur apreès distribution de carte
		//System.out.println(player1);
		
		//test de la methode de jouerOeuvre
		/**
		 * while(player1.getstate()==EnumEtat.Bousier) {
			player1.play();
		}*/
		
		//test de la methode de calcul du nombre de points 
		int a=player1.calculMaxPointOeuvre();
		
		System.out.println("pour le joueur 1 , le nombre max de point est "+a);
				
	}
	

}


