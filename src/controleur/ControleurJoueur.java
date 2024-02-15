package controleur;
/**
* la classe contoleur joueur v'a s'occuper de controller les  actions du joueur. 
* en fonction des des actions éffectuées sur l'interface graphique par le joueur , contoleurJoueur va  faire appel aux différentes méthodes
*  de Joueur Physque et mettre à jours ses information dans le modèle
* 
* @author brian
*
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import modele.*;
import vue.Game;
import vue.myFrame;

public class ControleurJoueur {
	/**
	 *Joueur: ici joueur représente le joueur physique qui sera contollé dans cettd classe*/
	
	
	private JoueurPhysique joueur;
	/**
	 * instanciation d'un controleurJoueur 
	 * @param joueur: le joueur physique dont les actions seront controllé
	 * Observer o : représente la classe de l'interface graphique qui sera un observeur du joueur 
	 * @param btnOeuvre, btnVieFuture, btnPouvoir, btnReincarner, btnPiocherPile, btnPasser qui sont les boutons de l'interfaces 
	 * graphiques qui déclencheront les différentes actions possible du joueur */
	public ControleurJoueur(JoueurPhysique joueur, Observer o, JButton btnOeuvre, JButton btnVieFuture, JButton btnPouvoir, JButton btnReincarne, JButton btnPiocherPile, JButton btnPasser) {
		this.joueur=joueur;
		joueur.addObserver(o);
		
		//====================================lorsqu'on appui sur le bouton jouer Oeuvre================================//
		/**cette méthode lance les instruction permetant au joueur de jouer pour son oeuvre
		 * il peut jouer uniquement si c'est son tour , c'est à dire si la variable canPlay est à true*/
		btnOeuvre.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(joueur.getcanPlay()==true) {
					Carte carte=((Game)o).mainSelectione();
					joueur.setCarte(carte);
					
					joueur.play("Oeuvre");
				}
				else {
					JOptionPane.showMessageDialog(null,"vous ne pouvez pas jouer ", "Information", JOptionPane.INFORMATION_MESSAGE);
				}

				
				
				
			}
		});
		
		
		
		//====================================lorsqu'on appui sur le bouton jouer VieFuture================================//
		/**cette méthode lance les instructions permettant au joueur de jouer pour sa vie future
		 * il peut jouer uniquement si c'est son tour c'est à dire si la valeur de la variable canPlay est à  true */
		btnVieFuture.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(joueur.getcanPlay()) {
					Carte carte=((Game)o).mainSelectione();
					joueur.setCarte(carte);
					
					joueur.play("vieFuture");
					
				}
				else {
					JOptionPane.showMessageDialog(null,"vous ne pouvez pas jouer attendez votre tour ", "Information", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		
		//====================================lorsqu'on appui sur le bouton jouer pouvoir================================//
		btnPouvoir.addActionListener(new ActionListener() {
			/**cette méthode lance les instructions permettant aujoueur de jouer pour le pouvoir
			 * il peut jouer uniquement si c'est son tour, c'est à dire si la valeur de la variable canPlay est à  true*/
			public void actionPerformed(ActionEvent e) {
				if(joueur.getcanPlay()) {
					Carte carte=((Game)o).mainSelectione();
					joueur.setCarte(carte);
					joueur.play("pouvoir");
				}
				else {
					JOptionPane.showMessageDialog(null,"vous ne pouvez pas jouer attendez votre tour ", "Information", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
				
			}
		});
		//====================================lorsqu'on appui sur le bouton  de reincarnation================================//
		btnReincarne.addActionListener(new ActionListener() {
			/**cette méthode lance les instructions permettant au joueur de se réincarner
			 * le joueur peut se réincarner uniquement si c'est son tour 
			 * c'est à dire si la valeur de la variable canPlay est à true */
			public void actionPerformed(ActionEvent e) {
				if(joueur.getcanPlay()) {
					joueur.reincarnation();
					btnReincarne.setVisible(false);
					joueur.sethavePlayed(true);
				}
				else {
					JOptionPane.showMessageDialog(null,"vous ne pouvez pas jouer attendez votre tour ", "Information", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		
		//====================================lorsqu'on appui sur le bouton piocher  à la pile================================//
		btnPiocherPile.addActionListener(new ActionListener() {
			/**cette méthode lance les instruction pour qu'un joueur puisse piocher dans sa pile 
			 * si la pile est vide , un message est renvoyé
			 * c'est à dire quand la valeur de la variable canPlay est à true*/
			public void actionPerformed(ActionEvent e) {
				if(joueur.getPile().isEmpty()) {
					JOptionPane.showMessageDialog(null,"impossible de piocher une carte à la pile , elle est vide ", "Information", JOptionPane.INFORMATION_MESSAGE);
					
				}
				else {
					joueur.ajouterCarteMain(joueur.piocherPile());
				}
				
			}
		});
		
		//====================================lorsqu'on appui sur le bouton passer ================================//
		/**cette méthode lance les instruction pour qu'un joueur puisse passer son tour
		 * c'est à dire quand la valeur de la variable canPlay est à true*/
		btnPasser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(joueur.getcanPlay()==true) {
					JOptionPane.showMessageDialog(null," êtes vous sûr de vouloir passer votre tour ", "Information", JOptionPane.INFORMATION_MESSAGE);
					joueur.sethavePlayed(true);
				}
				else {
					JOptionPane.showMessageDialog(null,"vous ne pouvez pas jouer ", "Information", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}
		});

		
		
		
	}

}
