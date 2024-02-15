package vue;

/**
* le JFrame Menu est le point d'entrée du jeux il permet deux chose s:
* - grace à un premier bouton , il permet de lancer une nouvelle partie de Karmaka
* - grace à  un deuxième boutton , il permet de reprendre une partie sauvegardée au préalable
* 	- dans le deuxième cas , dans la méthode Main, le sinformation des joeuur sont récupérées dans les fichier de sauvegarde
* 	- à partir des ces information, on crée une nouvelle instance de Partie  grace à laquelle on relance le jeu  
* 
*
* 
* @author diffo diffo brian - dorcas adrake
*
*/

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.Joueur;
import modele.JoueurPhysique;
import modele.Partie;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class Menu extends JFrame {
	/**
	 * private JTextField textField public JButton btnNewButton = new
	 * JButton("PLAY"); : boutton pour créer une nouvelle partie public JButton
	 * btnContinue = new JButton("continuer une sauvegarde"): bouton pour continuer
	 * une sauvegarde
	 */

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	public JButton btnNewButton = new JButton("PLAY");
	public JButton btnContinue = new JButton("continuer une sauvegarde");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
					/**
					 * cas ou le joueur décide de commencer une nouvelle partie
					 */
					frame.btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JoueurPhysique player1 = new JoueurPhysique(frame.textField.getText());
							Game game = new Game(player1, null);
							game.setVisible(true);
							game.btnRestart.setVisible(false);

							frame.dispose();

						}
					});

					/**
					 * cas ou l'utilisateur décide de continuer une sauvegarde - les infos des
					 * joueur sont récupérées dans le fichier de sauvegarde - création d'une partie
					 * avec ces information - création d'une nouvelle instance de Game
					 */
					frame.btnContinue.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								FileInputStream fileReader1 = new FileInputStream("joueurPhysique.ser");
								ObjectInputStream rd1 = new ObjectInputStream(fileReader1);
								Joueur player = ((Joueur) rd1.readObject());
								rd1.close();
								System.out.println("le joueu est " + player);

								FileInputStream fileReader2 = new FileInputStream("joueurVirtuel.ser");
								ObjectInputStream rd2 = new ObjectInputStream(fileReader2);
								Joueur computerPlayer = ((Joueur) rd2.readObject());
								Partie partie = Partie.getInstancePartie(player, computerPlayer);
								Game game = new Game((JoueurPhysique) player, partie);
								game.setVisible(true);
								game.btnStart.setVisible(false);
								frame.dispose();
								rd2.close();

							} catch (IOException | ClassNotFoundException e2) {
								e2.printStackTrace();
							}

						}
					});

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 457);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setFont(new Font("Segoe Script", Font.PLAIN, 16));
		textField.setBounds(193, 156, 152, 40);
		contentPane.add(textField);
		textField.setColumns(10);

		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(193, 257, 152, 40);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("KARMAKA");
		lblNewLabel.setFont(new Font("Segoe Script", Font.PLAIN, 17));
		lblNewLabel.setBounds(193, 26, 152, 40);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ENTER YOU NAME");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(193, 106, 152, 22);
		contentPane.add(lblNewLabel_1);

		btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnContinue.setBounds(156, 346, 237, 40);
		contentPane.add(btnContinue);
	}
}
