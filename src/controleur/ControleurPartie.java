package controleur;
/**
* la classe controleur partie va s'occuper de lancer la partie selon deux cas:
* le cas ou on lance une nouvelle partie le controleur crèe un joueur virtuel, crée les différentes cartes , les distribue aux joueurx et lance la partie
* le cas ou on reprend une partie sauvegardée les information de la partie sont récupérées et on la relance là où  on s'était arrêté
* 
* @author brian
*
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.SwingWorker;

import modele.*;
import vue.Game;

public class ControleurPartie {
	/**
	 * partie: stocke les information de la partie qui va se jouer
	 * btnStart: le bouton qui va permettre de lancer la partie
	 * game: représente l'interface graphique qui permettra d'interagir avec le joueur physique 
	  */
	
	private Partie partie;
	private JButton btnStart;
	private Game game;
	/**instaciation d'un nouveau controleur partie
	 * btnSave: permet de sauvegarder une partie
	 * btnRestart: permet au joueur de reprendre la partie sauvegarder
	 * player1: représente le joueur physique récupéré dans la partie sauvegardée
	 * btnReincarne: le bouton qui permet à un joueur de se réincarner
	 * */
	public ControleurPartie(JButton btn, JButton btnRestart, JButton btnSave, JoueurPhysique player1, JButton btnPiocher, JButton btnReincarne, Game game, Partie partieSaved) {
        this.btnStart = btn;
        this.game = game;

        CardFactory factory = CardFactory.getInstance();
        
        /**
         * lancement d'une nouvelle partie lorsque le joueur clique sur ce bouton
         * * les informations sauvegardées sont affichées sur l'interface graphique
         * appel de la méthode deroullement partie dans un swing worket pour que elle puisse s'exécuter en arrière plan sans pertuber l'affichage de l'interface*/
        btnStart.addActionListener(new ActionListener() {
        	/**ensemeble des opérations qui s'effectuent lorsque le joueur appuie sur le bouton start*/
            public void actionPerformed(ActionEvent e) {
            	JoueurVirtuel player2 = new JoueurVirtuel("ordi");
                player2.addObserver(game);
                ControleurPartie.this.partie = Partie.getInstancePartie(player1, player2);
                
                
                
                System.out.println("lancement de la partie");
                
                factory.remplissageCarte(partie.source);
                partie.distribuerCarte();
                System.out.println(player2.toString());
                game.labelPlayerName.setText("player: "+Partie.player.getName());
                game.labelState.setText("etat: >"+Partie.player.getstate().toString());
                game.labelPile.setText("taille Pile: >"+Partie.player.getPile().size());
                game.labelAn.setText("point Karmic >"+Partie.player.listeAnneaux.size());
                btnStart.setVisible(false);
                btnReincarne.setVisible(false);

                // Appeler partie.deroulement() dans un SwingWorker
                if (game.affiche) {
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            partie.deroulement(game.labelPlay, factory);
                            return null;
                        }
                    };
                    worker.execute();
                }
            }
        });
        
        
        /**
         * lancement d'une partie grace aux information récupérées dans le fichier de sauvegarde 
         * les informations sauvegardées sont affichées sur l'interface graphique
         * appel de la méthode deroullement partie dans un swing worket pour que elle puisse s'exécuter en arrière plan sans pertuber l'affichage de l'interface*/
        btnRestart.addActionListener(new ActionListener() {
        	/**ensemeble des opérations qui s'effectuent lorsque le joueur appuie sur le bouton Restart ici il reprends la partie sauvegardée au préalable*/
			public void actionPerformed(ActionEvent e) {
				ControleurPartie.this.partie=partieSaved;
				
				
                Partie.computerPlayer.addObserver(game);
                
                
                
                System.out.println("lancement de la partie");
               
                System.out.println(partie.computerPlayer.toString());
                game.labelPlayerName.setText("player: "+Partie.player.getName());
                game.labelState.setText("etat: >"+Partie.player.getstate().toString());
                game.labelPile.setText("taille Pile: >"+Partie.player.getPile().size());
                game.labelAn.setText("point Karmic >"+Partie.player.listeAnneaux.size());
                Partie.player.observation();
                Partie.computerPlayer.observation();
                btnRestart.setVisible(false);
                btnReincarne.setVisible(false);

                // Appeler partie.deroulement() dans un SwingWorker
                if (game.affiche) {
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            partie.deroulement(game.labelPlay, factory);
                            return null;
                        }
                    };
                    worker.execute();
                }
			}
		});
        
        
        /**le bouton save lorsqu'on appui dessu va appeler la méthode save pour sauvegarder puis fermer la partie*/
        btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					saveObject();
					game.dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});

        
        

        btnPiocher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                player1.ajouterCarteMain(partie.retirerCarteSource());
            }
        });
    }
	
	/**dans cette méthode , si le joueur le décide les informations de la partie seront sauvegardées
	 * on sauvegarde le joueur physique dans un fichier
	 * et ton sauvegarde le joueur virtuel dans un autre fichier*/
	public static void saveObject() throws IOException {
		 FileOutputStream file = new FileOutputStream("joueurPhysique.ser");
		 ObjectOutputStream object = new ObjectOutputStream(file); 
		 object.writeObject(Partie.player);
		 object.close();
		 
		 FileOutputStream file2 = new FileOutputStream("joueurVirtuel.ser");
		 ObjectOutputStream object2 = new ObjectOutputStream(file2); 
		 object2.writeObject(Partie.computerPlayer);
		 object2.close();
	 
	 }
	
	


	
	
	
	
	
	
	
	
	
	

}
