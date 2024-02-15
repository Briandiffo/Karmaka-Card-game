package vue;

/**
*- la calsse myFrame va  servir de base pour l'utilisation du pouvoir de chaque carte 
*- en fonction du pouvoir de la carte , elle permettra au joueur de réaliser les opérations correspondantes 
*
* 
* @author diffo diffo brian - dorcas adrake
*
*/

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.Carte;
import modele.Joueur;

import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class myFrame extends JFrame implements Serializable {
	/**
	 * contentPane; JList<Carte> : va contenir les carte si le pouvoir demande à
	 * l'utilisateur de choisir parmi une liste de cartes btnAction: permet à
	 * l'utilisateur de confirmer son choix btnAccept JLabel label: affiche
	 * l'instruction pour le joueur DefaultListModel<Carte> model
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<Carte> list = new JList<Carte>();
	public JButton btnAction;
	public JButton btnAccept = new JButton("OK");
	private JLabel label;
	DefaultListModel<Carte> model = new DefaultListModel<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myFrame frame = new myFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * creation d'une nouvelle instance de myFrame
	 */
	public myFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 609, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 585, 438);
		contentPane.add(panel);
		panel.setLayout(null);

		this.btnAction = new JButton("CHOISIR");
		btnAction.setFont(new Font("Tahoma", Font.PLAIN, 16));

		btnAction.setBounds(199, 314, 187, 55);
		panel.add(btnAction);

		list.setBounds(199, 63, 187, 187);
		panel.add(list);
		list.setModel(model);

		label = new JLabel("New label");
		label.setForeground(SystemColor.textHighlight);
		label.setFont(new Font("Segoe Script", Font.BOLD, 15));
		label.setBounds(37, 10, 519, 43);
		panel.add(label);

		btnAccept.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAccept.setBounds(199, 272, 187, 32);
		panel.add(btnAccept);
		btnAccept.setVisible(false);
	}

	/*
	 * public void remplissageListe(List<Carte> liste) {
	 * System.out.println("les cartes sont sensées être affichée");
	 * model.addAll(liste); list.getSelectionModel().addListSelectionListener(e -> {
	 * Carte carte = (Carte) list.getSelectedValue(); //
	 * textArea_1.setText(carte.toString()); });
	 * 
	 * }
	 */
	/**
	 * cette méthode permet de remplir la liste du Jframe avec des cartes
	 * 
	 * @param liste: la liste de carte à afficher au joueur
	 */
	public void remplissageListe(List<Carte> liste) {
		System.out.println("Les cartes sont censées être affichées");
		model.clear(); // Nettoie le modèle existant
		model.addAll(liste);

		list.setModel(model); // Définit le modèle sur la JList
		list.setSelectedIndex(0);
	}

	/**
	 * permet de récupérer la carte ou les cartes choisies par le joueur
	 * 
	 * @return carte : la carte sélectionnée
	 */
	public Carte carteSelectione() {
		// Récupérer la carte au moment de l'action de l'utilisateur (par exemple, clic
		// sur un bouton)
		return (Carte) list.getSelectedValue();
	}

	public void setLabel(String message) {
		this.label.setText(message);
	}
}
