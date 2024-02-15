package vue;

/**
*- la classe Game est un Jframe qui représente l'interface qui contiendra notre jeu et permettra au joueur physique d'interragir 
*- cette classes  comprend l'ensemble des bouton , des labels et des zones de texte pour un affichage complet des informations du jeu 
*- la classe Game impelemnte l'Observer et permet d'observer les deux joueur JoueurPhysique et JoueurVirtuel 
*- la méthode Update va s'occuper de mettre à jour les informations à l'écran à  chaque nouvelle observation
* 
* @author diffo diffo brian - dorcas adrake
*
*/
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controleur.ControleurJoueur;
import controleur.ControleurPartie;
import modele.Carte;
import modele.*;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.List;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.SystemColor;

public class Game extends JFrame implements Observer, Serializable {
	/**
	 * private JPanel contentPane; btnOeuvre: c'est le bouton qui permet à
	 * l'utilisateur de jouer pour son oeuvre labelPlay: ce label affiche le nom de
	 * l'utilisateur dont c'est le tour textArea: affiche toutes les information du
	 * joueur physique JList<Carte> list : c'est la liste des cartes de la main du
	 * joueur physique DefaultListModel<Carte> model: permettra de controller les
	 * opération de la liste de cartes textArea_1: affiche les information du joueur
	 * virtuel btnPiocher: bouton qui permet au joueur physique de piocher une carte
	 * btnReincarne : boutton qui déclenche l'action de réincarnation du joueur
	 * virtue btnPiocherPile: boutton qui déclenche l'action de pioche dans la pile
	 * du joueur physique public boolean affiche = false; private JPanel panel = new
	 * JPanel(); descriptionCarte: affiche le pouvoir de la carte sélectionnée par
	 * le joueur physique
	 * 
	 * btnSave : boutton qui permet de sauvegarder une partie en cours
	 * 
	 * labelState : label qui affiche le niveau du joueur physique labelAn: label
	 * qui affiche les point karmiques du joueur physique btnRestart : boutton qui
	 * permet de lancer la partie btnStart : bouton qui permet de lancer une
	 * nouvelle partie btnPasser = boutton qui permet au joueur physique de passer
	 * son tour labelPlayerName : label qui affiche le nom du joueur
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnOeuvre;
	public JLabel labelPlay = new JLabel("");
	private JTextArea textArea;
	private JList<Carte> list = new JList<Carte>();
	DefaultListModel<Carte> model = new DefaultListModel<>();
	private JTextArea textArea_1;
	public JButton btnPiocher = new JButton("Piocher");
	public JButton btnReincarne = new JButton("Reincarnation");
	private JButton btnPiocherPile = new JButton("Piocher Pile");
	public boolean affiche = false;
	private JPanel panel = new JPanel();
	private JTextArea descriptionCarte = new JTextArea();
	public JLabel labelPile = new JLabel("Pile: ");
	public JButton btnSave = new JButton("SAVE");

	public JLabel labelState = new JLabel("state: ");
	public JLabel labelAn = new JLabel("point Karmic: ");
	public JButton btnRestart = new JButton("restart");
	public JButton btnStart = new JButton("start");
	public JButton btnPasser = new JButton("PASSER");
	public JLabel labelPlayerName = new JLabel("player: ");

	public Game(JoueurPhysique player1, Partie partie) {

		/**
		 * <strong>initialisation des élément de notre interface</strong>
		 */

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1126, 745);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		btnPiocher.setVisible(false);
		setContentPane(contentPane);

		btnOeuvre = new JButton("Jouer Oeuvre");
		btnOeuvre.setBounds(10, 639, 161, 47);
		btnOeuvre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.setLayout(null);

		btnOeuvre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnOeuvre);

		JButton btnVieFuture = new JButton("Jouer Vie Future");
		btnVieFuture.setBounds(200, 638, 161, 47);

		btnVieFuture.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(btnVieFuture);

		JButton btnPouvoir = new JButton("Jouer Pouvoir");
		btnPouvoir.setBounds(387, 638, 161, 47);

		btnPouvoir.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(btnPouvoir);
		list.setBounds(10, 13, 143, 161);
		contentPane.add(list);

		btnStart.setBounds(362, 2, 100, 40);

		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnStart);
		btnPiocher.setBounds(563, 310, 154, 47);

		btnPiocher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPiocher.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(btnPiocher);

		// personnalisation
		list.setModel(model);

		labelPlay.setBounds(250, 5, 400, 47);
		labelPlay.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPane.add(labelPlay);

		textArea = new JTextArea();
		textArea.setForeground(SystemColor.inactiveCaptionText);
		textArea.setBackground(SystemColor.inactiveCaptionBorder);
		textArea.setBounds(753, 377, 335, 308);
		contentPane.add(textArea);

		textArea_1 = new JTextArea();
		textArea_1.setForeground(SystemColor.inactiveCaptionText);
		textArea_1.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textArea_1.setBackground(SystemColor.inactiveCaptionBorder);
		textArea_1.setBounds(753, 57, 335, 252);
		contentPane.add(textArea_1);

		btnReincarne.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReincarne.setBounds(10, 526, 161, 47);
		contentPane.add(btnReincarne);

		btnPiocherPile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPiocherPile.setBounds(563, 530, 161, 47);
		contentPane.add(btnPiocherPile);

		panel.setBounds(181, 166, 377, 411);
		contentPane.add(panel);

		ImagePanel imagePanel = new ImagePanel(
				"C:\\Users\\brian\\eclipse-workspace\\karmaka\\src\\cardset\\acceuil.png");
		imagePanel.setBounds(0, 0, 377, 411);
		panel.setLayout(new BorderLayout());
		panel.add(imagePanel, BorderLayout.CENTER);

		JLabel infoMachine = new JLabel("information machine");
		infoMachine.setFont(new Font("Tahoma", Font.BOLD, 16));
		infoMachine.setBounds(753, 14, 300, 30);
		contentPane.add(infoMachine);

		JLabel infoJoueur = new JLabel("information Joueur ");
		infoJoueur.setFont(new Font("Tahoma", Font.BOLD, 16));
		infoJoueur.setBounds(753, 337, 300, 30);
		contentPane.add(infoJoueur);
		descriptionCarte.setFont(new Font("Segoe Script", Font.PLAIN, 16));

		descriptionCarte.setBounds(250, 56, 400, 100);
		contentPane.add(descriptionCarte);
		labelPile.setFont(new Font("Arial Black", Font.BOLD, 16));
		labelPile.setBounds(10, 269, 161, 53);

		contentPane.add(labelPile);
		labelState.setFont(new Font("Arial Black", Font.PLAIN, 16));
		labelState.setBounds(10, 204, 143, 55);

		contentPane.add(labelState);

		labelAn.setFont(new Font("Arial", Font.BOLD, 16));
		labelAn.setBounds(10, 348, 143, 40);
		contentPane.add(labelAn);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(300, 580, 161, 47);
		contentPane.add(btnSave);

		btnRestart.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnRestart.setBounds(250, 2, 100, 40);

		contentPane.add(btnRestart);

		btnPasser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPasser.setBounds(556, 639, 161, 47);

		contentPane.add(btnPasser);

		/**
		 * Instanciation du controlleur qui av se charger de controller les actions du
		 * joueur
		 */
		new ControleurJoueur(player1, this, btnOeuvre, btnVieFuture, btnPouvoir, btnReincarne, btnPiocherPile,
				btnPasser);

		/**
		 * instanciation du controlleur qui va s'occuper de lancer la partie et de
		 * surveiller
		 */
		new ControleurPartie(btnStart, btnRestart, btnSave, player1, btnPiocher, btnReincarne, this, partie);

		labelPlayerName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelPlayerName.setBounds(10, 421, 143, 40);
		contentPane.add(labelPlayerName);

		/**
		 * <strong>le liste actionListener permet de réaliser une opération quand le
		 * joueur sélectionne une carte de la liste </strong> ici on aura deux opération
		 * : - un changement de l'image de la carte - changement de cardDescription
		 */
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					// L'événement est déclenché une fois que la sélection est terminée
					Carte carte = list.getSelectedValue();
					if (carte != null) {
						String path = "C:\\Users\\brian\\eclipse-workspace\\karmaka\\src\\cardset\\destinee.png";
						// Appelez la méthode setImage de votre ImagePanel avec le nouveau chemin
						// d'accès
						descriptionCarte.setText(carte.getPouvoir());
						imagePanel.setImage(carte.path);

					}
				}
			}
		});

	}

	@Override

	/**
	 * la méthode update va mettre à jour les informations à l'écran à chaque fois
	 * qu'on aura un changement des informations d'un joueur
	 */
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stu

		if (o instanceof JoueurPhysique) {
			if (Partie.player.getMain().isEmpty() == false && Partie.player.getPile().isEmpty() == false) {
				btnReincarne.setVisible(false);
			}
			this.labelState.setText("etat: >" + Partie.player.getstate());
			this.labelPile.setText("taille pile: >" + Partie.player.getPile().size());
			this.labelAn.setText("point Karmic: >" + Partie.player.listeAnneaux.size());
			if (Partie.player.getWinned()) {
				labelPlay.setText("la partie est terminée le gagnant est " + Partie.player.getName());
			}
			if (((Joueur) o).getMain().isEmpty()) {
				model.removeAllElements();
				btnReincarne.setVisible(true);

			} /*
				 * else if (((Joueur) o).getPile().isEmpty()) {
				 * btnPiocherPile.setVisible(false); }
				 */
			model.removeAllElements();

			model.addAll(((Joueur) o).getMain());
			list.setSelectedIndex(0);
			list.getSelectionModel().addListSelectionListener(e -> {
				Carte carte = (Carte) list.getSelectedValue();
				// textArea_1.setText(carte.toString());
			});
			this.textArea.setText(o.toString());

			affiche = true;

			System.out.println("je vois bien");

		}

		if (o instanceof JoueurVirtuel) {

			this.textArea_1.setText(o.toString());
			if (Partie.computerPlayer.getWinned()) {
				labelPlay.setText("la partie est terminée le gagnant est " + Partie.computerPlayer.getName());
			}

			affiche = true;

			System.out.println("je vois bien pour l'ordi");

		}
	}

	/**
	 * mainSelectionee: permet de récupérer dans une variable la carte sélectionnée
	 * par le joueur à chaque fois
	 * 
	 * @return carte: la carte sélectionnée
	 */
	public Carte mainSelectione() {
		Carte carte = (Carte) list.getSelectedValue();

		System.out.println(carte);
		return carte;
	}
}
