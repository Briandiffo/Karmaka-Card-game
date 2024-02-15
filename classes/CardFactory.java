package modele;
/**la classe CardFactory s'occupe de la création de toutes les carte du jeux en implementant la méthode de jeux de pouvoir pour 
 * les joueur physiques et les joueur virtuel 
 * cette classe permet également de remplir la source à partir des cartes crées 
 * cette carte es implementé avec le patron singleton on ne peut donc avoir qu'une seule instance
* @author diffo diffo brian- Adrake Dorcas 
* 
*
*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.*;
import javax.swing.JOptionPane;

import javax.swing.JFrame;

import vue.myFrame;

public class CardFactory implements Serializable{
	private static CardFactory instance;
	/**listPouvoir: classe qui permet de décrire le pouvoir de charque carte*/
	public PouvoirCarte listPouvoir=new PouvoirCarte();
	
	private CardFactory() {
		
	}
	public static CardFactory getInstance() {
		if(instance==null) {
			instance=new CardFactory();
		}
		return instance;
		
	}
	
	/**permet d'afficher dans la console la liste de toutes les cartes*/
	public void afficherListCarte(List<Carte> liste) {
		StringBuilder result=new StringBuilder();
		
		Iterator<Carte> iterator=liste.iterator();
		
		int indice=0;
		
		while(iterator.hasNext()) {
			
			result.append("<<<<"+indice+" "+iterator.next().getDescription()+">>>>\n");
			indice++;
			
		}
		System.out.println(result.toString());
	}
//========================================================creation des cartes de couleur Bleu========================================================//
	/**cette méthode permet de créer la liste des cartes de type destinée
	 * @return list<Carte>*/
	
	
	public List<Carte> createDestinee(){
		String path="C:\\Users\\brian\\eclipse-workspace\\karmaka\\src\\cardset\\destinee.png";
		Carte carte=new Carte(EnumCouleur.Bleu, 2, "Destinee", path, listPouvoir.destinee()) {
			
			
			
			/**implementation de la méthode d'utilisation du pouvoir de la carte pour un joueur virtuel*/
			public void computerUsePouvoir(JoueurVirtuel user, Joueur victim) {
				ArrayList<Carte> cardlis=new ArrayList<Carte>();				
				for(int i=0;i<3;i++) {
					cardlis.add(Partie.piocherSource());
				}
				user.ajouterCarteMain(cardlis.get(0));
				user.sethavePlayed(true);
				JOptionPane.showMessageDialog(null,"l'ordinateur vient d'utiliser le pouvoir de destinée", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
			
			
			
			/**implementation de la méthode d'utilisation du pouvoir de la carte pour un joueur physique
			 * dans un premier temps, affichage d'une frame avec 3 cartes de la source
			 * le joueur choisit les cartes qu'il veut */
			public void usePouvoir(Joueur user, Joueur victim) {
				//creation du jFrame pour l'utilisation du pouvoir
				Partie partie=Partie.getInstancePartie(user, victim);
				int sizeSource=partie.getSource().size();
				if(sizeSource>=2) {
					if(sizeSource>=3) {
						sizeSource=3;
					}
					myFrame frame = new myFrame();
					frame.setLabel("choisissez la carte que vous ne voulez pas");
					frame.setVisible(true);
					ArrayList<Carte> liste=new ArrayList<Carte>(); //liste des cartes que verra le joueur
					
					for(int i=0; i<sizeSource;i++) {
						liste.add(partie.retirerCarteSource()); //recupération de trois cartes à la source
					}
					
					//affichage des trois premières carte de la source
					frame.remplissageListe(liste); 
					System.out.println("affichage de la source après remise\n");
					afficherListCarte(Partie.getInstancePartie(user, victim).getSource());
									
					//choix de la carte à remettre par le joueur
					frame.btnAction.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Carte carte=frame.carteSelectione();
							partie.ajouterCarte(carte);
							liste.remove(carte);						
							user.ajouterCarteMain(liste);
							user.sethavePlayed(true);
							frame.dispose();						
						}
					});
					
					
				}
				else {
					JOptionPane.showMessageDialog(null,"impossible d'utiliser ce pouvoir car la source est vide ", "Information", JOptionPane.INFORMATION_MESSAGE);
					Carte carte= ((JoueurPhysique) user).getCarteJoue();
					user.ajouterCarteMain(carte);
				}
								
			}
			
		};
		ArrayList<Carte> liste=new ArrayList<Carte>();
		for(int i=0; i<3;i++) {
			liste.add(carte);
		}		
		return liste;
	}
	
	
	
	
	
	/** creation des carte de coup d'oeil*/
	public List<Carte> createCoupOeil(){//machine ne doit pas utiliser
		String path="C:\\Users\\brian\\eclipse-workspace\\karmaka\\src\\cardset\\coupOeil.png";
		Carte carte=new Carte(EnumCouleur.Bleu, 1, "coup d'oeil", path, listPouvoir.coupOeil()) {
			/**implementation de la méthode d'utilisation du pouvoir de la carte pour un joueur virtuel*/
			public void computerUsePouvoir(JoueurVirtuel user, Joueur victim) {
				if(!(user.getMain().isEmpty())) {
					ArrayList<Carte> cardlis=new ArrayList<Carte>();				
					cardlis=(ArrayList<Carte>) user.getMain();
					user.ajouterCarteMain(cardlis.get(0));
					
					JOptionPane.showMessageDialog(null,"l'ordinateur vient d'utiliser le pouvoir de coup d'oeil", "Information", JOptionPane.INFORMATION_MESSAGE);
					//user.play("coup");
				}
				else {
					JOptionPane.showMessageDialog(null,"l'ordinateur vient coup oeil ", "Information", JOptionPane.INFORMATION_MESSAGE);
					user.ajouterCarteMain(user.getCarteJoue());
					//user.play("coup");
					
				}
			}
			
			/**implementation de la méthode d'utilisation du pouvoir de la carte pour un joueur physique */
			public void usePouvoir(Joueur user, Joueur victim) {
				if(!Partie.computerPlayer.getMain().isEmpty()) {
					myFrame frame=new myFrame();
					frame.setLabel("choisissez la carte que vous voulez recupérer");
					frame.setVisible(true);
					ArrayList<Carte> listechoix=(ArrayList<Carte>) Partie.computerPlayer.getMain();
								
					frame.remplissageListe(listechoix);
					frame.btnAction.setVisible(false);
					frame.btnAccept.setVisible(true);
					frame.btnAccept.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame.dispose();	
						}
					});				
				}
				else {
					JOptionPane.showMessageDialog(null,"impossible d'utiliser ce pouvoir car la vie future du rival est vide est vide ", "Information", JOptionPane.INFORMATION_MESSAGE);
					Carte carte= ((JoueurPhysique) user).getCarteJoue();
					user.ajouterCarteMain(carte);
				}			
			}
			
		};
		ArrayList<Carte> liste=new ArrayList<Carte>();
		for(int i=0; i<3;i++) {
			liste.add(carte);
		}
		return liste;
		
	}
	
	
	
	
	/** creation des carte de transmigration*/
	public List<Carte> createTransmigrate(){
		String path="C:\\Users\\brian\\eclipse-workspace\\karmaka\\src\\cardset\\karmakaCardSet.png";
		Carte carte=new Carte(EnumCouleur.Bleu, 1, "transmigrate", path, listPouvoir.transmigration()) {
			/**implementation de la méthode d'utilisation du pouvoir de la carte pour un joueur virtuel*/
			public void computerUsePouvoir(JoueurVirtuel user, Joueur victim) {
				if(!(user.getVieFuture().isEmpty())) {
					ArrayList<Carte> cardlis=new ArrayList<Carte>();				
					cardlis=(ArrayList<Carte>) user.getVieFuture();
					user.ajouterCarteMain(cardlis.get(0));	
					user.sethavePlayed(true);
					JOptionPane.showMessageDialog(null,"l'ordinateur vient d'utiliser le pouvoir de transmigration", "Information", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null,"l'ordinateur vient transmigrre ", "Information", JOptionPane.INFORMATION_MESSAGE);
					user.ajouterCarteMain(user.getCarteJoue());
					//user.play("coup");
					
				}
			}

			/**implementation de la méthode d'utilisation du pouvoir de la carte pour un joueur physique */
			public void usePouvoir(Joueur user, Joueur victim) {
				//affichage de la vie future
				if(!user.getVieFuture().isEmpty()) {
					myFrame frame=new myFrame();
					frame.setLabel("choisissez la carte que vous voulez recupérer");
					frame.setVisible(true);
					ArrayList<Carte> listechoix=new ArrayList<Carte>();
					listechoix.addAll(user.getVieFuture());				
					frame.remplissageListe(listechoix);								
					frame.btnAction.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Carte carte2=frame.carteSelectione();												
							user.ajouterCarteMain(carte2);						
							user.removeCarteVieFuture(carte2);	
							user.sethavePlayed(true);
							frame.dispose();											
						}
					});
					
				}
				else {
					JOptionPane.showMessageDialog(null,"impossible d'utiliser ce pouvoir car votre vie future est vide ", "Information", JOptionPane.INFORMATION_MESSAGE);
					Carte carte= ((JoueurPhysique) user).getCarteJoue();
					user.ajouterCarteMain(carte);
				}				
			}
			
		};
		ArrayList<Carte> liste=new ArrayList<Carte>();
		for(int i=0; i<3;i++) {
			liste.add(carte);
		}
		return liste;		
	}
	
	
	
	/**creation des carte de Reve brisé*/
	public List<Carte> createReveBrise(){//ajouter une condition en cas de vie future vide
		String path="C:\\Users\\brian\\eclipse-workspace\\karmaka\\src\\cardset\\reveBrise.png";
		Carte carte=new Carte(EnumCouleur.Bleu, 2, "Reve Brisé", path, listPouvoir.reveBrise()) {
			
			/**implementation de la méthode d'utilisation du pouvoir de la carte pour un joueur virtuel*/
			public void computerUsePouvoir(JoueurVirtuel user, Joueur victim) {
				if(!(Partie.player.getVieFuture().isEmpty())) {
					Carte carte=victim.recupererCarteVieFuture(0);
					user.ajouterCarteVieFuture(carte);
					user.sethavePlayed(true);
					JOptionPane.showMessageDialog(null,"l'ordinateur vient  de recupérer dans votre vieFuture "+ carte.getDescription(), "Information", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					user.ajouterCarteMain(user.getCarteJoue());
					JOptionPane.showMessageDialog(null,"l'ordinateur vient reve brisé ", "Information", JOptionPane.INFORMATION_MESSAGE);
					//user.play("coup");
					
				}
			}
			
			
			/**implementation de la méthode d'utilisation du pouvoir de la carte pour un joueur physique */
			public void usePouvoir(Joueur user, Joueur victim) {
				Partie partie=Partie.getInstancePartie(user, victim);								
				if(!(partie.getComputer().getVieFuture().isEmpty())) {
					Carte carte=partie.getComputer().recupererCarteVieFuture(0);
					user.ajouterCarteVieFuture(carte);
					JOptionPane.showMessageDialog(null, "vous venez de recupérer une carte "+carte.getDescription(), "Information", JOptionPane.INFORMATION_MESSAGE);
					user.sethavePlayed(true);
				}
				else {
					JOptionPane.showMessageDialog(null,"impossible d'utiliser ce pouvoir la vie future est vide ", "Information", JOptionPane.INFORMATION_MESSAGE);
					Carte carte= ((JoueurPhysique) user).getCarteJoue();
					user.ajouterCarteMain(carte);
				}												
			}			
		};
		ArrayList<Carte> liste=new ArrayList<Carte>();
		for(int i=0; i<3;i++) {
			liste.add(carte);
		}
		return liste;		
	}
	
	
	
	
	/** creation des carte de Deni*/
	

	
	
	
	/** creation des carte de Duperie*/
	public List<Carte> createDuperie(){
		String path="C:\\Users\\brian\\eclipse-workspace\\karmaka\\src\\cardset\\duperie.png";
		Carte carte=new Carte(EnumCouleur.Bleu, 3, "Duperie", path, listPouvoir.duperie()) {
			
			/**implementation de la méthode d'utilisation du pouvoir de la carte pour un joueur virtuel*/
			public void computerUsePouvoir(JoueurVirtuel user, Joueur victim) {
				int size=Partie.player.getMain().size();
				ArrayList<Carte> liste=new ArrayList<Carte>();
				
				if(size>=1) {
					if(size>=3) {
						size=3;
					}
					ArrayList<Carte> main=(ArrayList<Carte>) Partie.player.getMain();
					for(int i=0; i<size; i++) {
						liste.add(main.get(i));
					}
					Carte carte=main.get(0);
					
					user.ajouterCarteMain(main.get(0));
					Partie.player.retirerCarteMain(carte);
					user.sethavePlayed(true);
					JOptionPane.showMessageDialog(null,"l'ordinateur vient d'utiliser le pouvoir de duperie et a récupéré dans votre main"+carte.getDescription(), "Information", JOptionPane.INFORMATION_MESSAGE);
					
				}

				else {
					user.ajouterCarteMain(user.getCarteJoue());
					JOptionPane.showMessageDialog(null,"l'ordinateur vient duperie ", "Information", JOptionPane.INFORMATION_MESSAGE);
					//user.play("coup");
				}
			}
			
			/**implementation de la méthode d'utilisation du pouvoir de la carte pour un joueur physique */
			public void usePouvoir(Joueur user, Joueur victim) {
				myFrame frame=new myFrame();
				frame.setLabel("choisissez la carte qui vous interesse");
				frame.setVisible(true);
				ArrayList<Carte> liste=new ArrayList<Carte>(); //liste des cartes que verra le joueur
				Partie partie=Partie.getInstancePartie(user, victim);
				int size=partie.getComputer().getMain().size();
				if(size>=1) {
					if(size>=3) {
						size=3;
					}
					ArrayList<Carte> main=(ArrayList<Carte>) partie.getComputer().getMain();
					for(int i=0; i<size; i++) {
						liste.add(main.get(i));
					}
					/**affichage des trois premières carte de la source*/
					frame.remplissageListe(liste); 			
					/**choix de la carte à remettre par le joueur*/
					frame.btnAction.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Carte carte=frame.carteSelectione();
							partie.getComputer().removeCarteVieFuture(carte);
							
							user.ajouterCarteMain(carte);
							user.sethavePlayed(true);
							frame.dispose();						
						}
					});
				}
				else {
					JOptionPane.showMessageDialog(null,"impossible d'utiliser ce pouvoir, les condition ne sont pas réunies  ", "Information", JOptionPane.INFORMATION_MESSAGE);
					Carte carte= ((JoueurPhysique) user).getCarteJoue();
					user.ajouterCarteMain(carte);
				}
				
				
			}			
		};
		ArrayList<Carte> liste=new ArrayList<Carte>();
		for(int i=0; i<2;i++) {
			liste.add(carte);
		}
		return liste; 		
	}
	
	

	
	/** creation des carte de Vol*/
	public List<Carte> createVol(){
		String path="C:\\Users\\brian\\eclipse-workspace\\karmaka\\src\\cardset\\vol.png";
		Carte carte=new Carte(EnumCouleur.Bleu, 3, "Vol", path, listPouvoir.vol()) {
			
			/**implementation de la méthode d'utilisation du pouvoir de la carte pour un joueur virtuel*/
			public void computerUsePouvoir(JoueurVirtuel user, Joueur victim) {
				if(victim.getOeuvre().size()>=1) {
					Carte carte=victim.recupererCarteOeuvre(0);
					user.ajouterCarteMain(carte);
					user.sethavePlayed(true);
					JOptionPane.showMessageDialog(null,"l'ordinateur vient d'utiliser le pouvoir de vol et a récupéré dans votre oeuvre"+ carte.getDescription(), "Information", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					user.ajouterCarteMain(user.getCarteJoue());
					JOptionPane.showMessageDialog(null,"l'ordinateur vient vol", "Information", JOptionPane.INFORMATION_MESSAGE);
					//user.play("coup");
				}
			}
			
			/**implementation de la méthode d'utilisation du pouvoir de la carte pour un joueur physique */
			public void usePouvoir(Joueur user, Joueur victim) {
				Partie partie=Partie.getInstancePartie(user, victim);
				if(!(partie.getComputer().getOeuvre().isEmpty())) {
					Carte carte=partie.getComputer().recupererCarteOeuvre(0);
					user.ajouterCarteMain(carte);
					JOptionPane.showMessageDialog(null, "vous venez de recupérer une carte "+carte.getDescription(), "Information", JOptionPane.INFORMATION_MESSAGE);
					user.sethavePlayed(true);
				}
				else {
					JOptionPane.showMessageDialog(null,"impossible d'utiliser ce pouvoir la l'eouvre de votre rival est vide ", "Information", JOptionPane.INFORMATION_MESSAGE);
					Carte carte= ((JoueurPhysique) user).getCarteJoue();
					user.ajouterCarteMain(carte);
				}										
			}			
		};
		ArrayList<Carte> liste=new ArrayList<Carte>();
		for(int i=0; i<2;i++) {
			liste.add(carte);
		}
		return liste;		
	}
	
	
	
	public List<Carte> createLendemain(){
		String path="C:\\Users\\brian\\eclipse-workspace\\karmaka\\src\\cardset\\lendemain.png";
		Carte carte=new Carte(EnumCouleur.Vert, 1, "Lendemain", path, listPouvoir.lendemain()) {
			
			/**implementation de la méthode d'utilisation du pouvoir de la carte pour un joueur virtuel*/
			public void computerUsePouvoir(JoueurVirtuel user, Joueur victim) {				
					Carte carte=Partie.piocherSource();
					user.ajouterCarteMain(carte);
					user.sethavePlayed(true);
					JOptionPane.showMessageDialog(null,"l'ordinateur vient d'utiliser le pouvoir de Lendemain ", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
			
			/**implementation de la méthode d'utilisation du pouvoir de la carte pour un joueur physique */
			public void usePouvoir(Joueur user, Joueur victim) {
				Partie partie=Partie.getInstancePartie(user, victim);
				Carte carte=partie.piocher(user);
				JOptionPane.showMessageDialog(null,"vous venez de piocher à la source une carte "+carte.getDescription()+" vous pouvez jouer à  nouveau", "Information", JOptionPane.INFORMATION_MESSAGE);
				//******************************************faire en sorte que le joueur joue après pioche											
			}			
		};
		ArrayList<Carte> liste=new ArrayList<Carte>();
		for(int i=0; i<2;i++) {
			liste.add(carte);
		}
		return liste;		
	}
	

	
	
	public List<Carte> createLongevite(){
		String path="C:\\Users\\brian\\eclipse-workspace\\karmaka\\src\\cardset\\longevite.png";
		Carte carte=new Carte(EnumCouleur.Vert, 2, "Longevité", path, listPouvoir.longevite()) {
			
			/**implementation de la méthode d'utilisation du pouvoir de la carte pour un joueur virtuel*/
			public void computerUsePouvoir(JoueurVirtuel user, Joueur victim) {				
					ArrayList<Carte> list=new ArrayList<Carte>();
					list.add(Partie.piocherSource());
					list.add(Partie.piocherSource());
					victim.ajouterCartePile(list);
					user.sethavePlayed(true);
					JOptionPane.showMessageDialog(null,"l'ordinateur vient d'utiliser le pouvoir de Longevité", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
			
			/**implementation de la méthode d'utilisation du pouvoir de la carte pour un joueur physique */
			public void usePouvoir(Joueur user, Joueur victim) {
				Partie partie=Partie.getInstancePartie(user, victim);
				Carte carte1=partie.retirerCarteSource();
				Carte carte2=partie.retirerCarteSource();
				partie.getComputer().ajouterCartePile(carte1);
				partie.getComputer().ajouterCartePile(carte2);
				JOptionPane.showMessageDialog(null,"vous venez d'ajouter à le pile de votre rival "+carte1.getDescription()+" et "+carte2.getDescription(), "Information", JOptionPane.INFORMATION_MESSAGE);
				//le joueur a joué son tour
											
			}			
		};
		ArrayList<Carte> liste=new ArrayList<Carte>();
		for(int i=0; i<1;i++) {
			liste.add(carte);
		}
		return liste;		
	}
	
	public List<Carte> createCrise(){
		String path="C:\\Users\\brian\\eclipse-workspace\\karmaka\\src\\cardset\\crise.png";
		Carte carte=new Carte(EnumCouleur.Rouge, 2, "crise", path, listPouvoir.crise()) {
			
			/**implementation de la méthode d'utilisation du pouvoir de la carte pour un joueur virtuel*/
			public void computerUsePouvoir(JoueurVirtuel user, Joueur victim) {				
					if(!(victim.getOeuvre().isEmpty())) {
						int a=0;
						Carte carte=victim.recupererCarteOeuvre(a);
						user.sethavePlayed(true);
						JOptionPane.showMessageDialog(null,"l'ordinateur vient de defausser dans votre main "+carte.getDescription(), "Information", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null,"l'ordinateur vient crise ", "Information", JOptionPane.INFORMATION_MESSAGE);
						user.ajouterCarteMain(user.getCarteJoue());
						//user.play("coup");
					}
			}
			
			/**implementation de la méthode d'utilisation du pouvoir de la carte pour un joueur physique */
			public void usePouvoir(Joueur user, Joueur victim) {
				Partie partie=Partie.getInstancePartie(user, victim);
				if(!(partie.getComputer().getOeuvre().isEmpty())) {
					int a=0;
					partie.getComputer().recupererCarteOeuvre(a);
					
					JOptionPane.showMessageDialog(null,"votre rival vient de perdre une carte de son oeuvre ", "Information", JOptionPane.INFORMATION_MESSAGE);
					user.sethavePlayed(true);
					//le joueur a joué son tour
												
				}
				else {
					JOptionPane.showMessageDialog(null,"vous ne pouvez pas utiliser ce pouvoir, l'oeuvre est vide ", "Information", JOptionPane.INFORMATION_MESSAGE);
					Carte carte= ((JoueurPhysique) user).getCarteJoue();
					user.ajouterCarteMain(carte);
				}
			}			
		};
		ArrayList<Carte> liste=new ArrayList<Carte>();
		for(int i=0; i<2;i++) {
			liste.add(carte);
		}
		return liste;		
	}
	
	
	
	
	
	
	
	
	public List<Carte> createVoyage(){
		String path="C:\\Users\\brian\\eclipse-workspace\\karmaka\\src\\cardset\\voyage.png";
		Carte carte=new Carte(EnumCouleur.Vert, 3, "Voyage", path, listPouvoir.voyage()) {
			
			/**implementation de la méthode d'utilisation du pouvoir de la carte pour un joueur virtuel*/
			public void computerUsePouvoir(JoueurVirtuel user, Joueur victim) {				
					//Carte carte=Partie.piocherSource();
					//user.ajouterCarteMain(carte);
					for(int i=0; i<3; i++) {
						Carte carte=Partie.piocherSource();
						user.ajouterCarteMain(carte);
						
					}
					
					JOptionPane.showMessageDialog(null,"l'ordinateur vient d'utiliser le pouvoir de voyage  ", "Information", JOptionPane.INFORMATION_MESSAGE);
					//user.play("");
			}
			
			/**implementation de la méthode d'utilisation du pouvoir de la carte pour un joueur physique */
			public void usePouvoir(Joueur user, Joueur victim) {
				ArrayList<Carte> liste=new ArrayList<Carte>();
				for(int i=0;i<3;i++) {
					liste.add(Partie.piocherSource());
					
				}
				user.ajouterCarteMain(liste);
				JOptionPane.showMessageDialog(null,"vous venez de récupérer trois cartes à la source  ", "Information", JOptionPane.INFORMATION_MESSAGE);
				
														
			}			
		};
		ArrayList<Carte> liste=new ArrayList<Carte>();
		for(int i=0; i<2;i++) {
			liste.add(carte);
		}
		return liste;		
	}
	
	
	
	
	
	
	public List<Carte> createSemis(){
		String path="C:\\Users\\brian\\eclipse-workspace\\karmaka\\src\\cardset\\semis.png";
		Carte carte=new Carte(EnumCouleur.Vert, 2, "Semis", path, listPouvoir.semis()) {
			
			/**implementation de la méthode d'utilisation du pouvoir de la carte pour un joueur virtuel*/
			public void computerUsePouvoir(JoueurVirtuel user, Joueur victim) {				
					//Carte carte=Partie.piocherSource();
					//user.ajouterCarteMain(carte);
					for(int i=0; i<2; i++) {
						Carte carte=Partie.piocherSource();
						user.ajouterCarteMain(carte);
						
					}
					user.ajouterCarteVieFuture(user.recupererCarteMain(0));
					user.ajouterCarteVieFuture(user.recupererCarteOeuvre(0));
					
					user.sethavePlayed(true);
					JOptionPane.showMessageDialog(null,"l'ordinateur vient d'utiliser le pouvoir de voyage  ", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
			
			private int complet=0;
			
			/**implementation de la méthode d'utilisation du pouvoir de la carte pour un joueur physique */
			public void usePouvoir(Joueur user, Joueur victim) {
				ArrayList<Carte> liste=new ArrayList<Carte>();
				for(int i=0;i<3;i++) {
					liste.add(Partie.piocherSource());
					
				}
				user.ajouterCarteMain(liste);
				JOptionPane.showMessageDialog(null,"vous venez de récupérer trois cartes à la source  ", "Information", JOptionPane.INFORMATION_MESSAGE);
				
				
				complet=0;
				myFrame frame = new myFrame();
				frame.setLabel("veillez appuyer deux fois sur le bouton pour choisir");
				frame.setVisible(true);
				
				
				//affichage des trois premières carte de la source
				frame.remplissageListe(user.getMain()); 
				
			
								
				//choix de la carte à remettre par le joueur
				frame.btnAction.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Carte carte=frame.carteSelectione();
						
						user.retirerCarteMain(carte);
						frame.remplissageListe(user.getMain());
						user.ajouterCarteVieFuture(carte);
						complet+=1;
						if(complet==2) {
							complet=0;
							user.sethavePlayed(true);
							frame.dispose();
						}
					}
				});
				
				
														
			}			
		};
		ArrayList<Carte> liste=new ArrayList<Carte>();
		for(int i=0; i<3;i++) {
			liste.add(carte);
		}
		return liste;		
	}
	
	
	
	
	
	
	/**cette méthode permet de créer la liste de s anneaux karmiques*/
	public static List<AnneauxKarmic> createAnneaux(){
		ArrayList<AnneauxKarmic> liste=new ArrayList<AnneauxKarmic>();
		AnneauxKarmic anneau=new AnneauxKarmic();
		for(int i=0; i<15; i++) {
			liste.add(anneau);
			
		}
		return liste;
	}
	

//========================================================ajout des carte à notre source qui sera en paramètre========================================================//
	
	/**cette méthode prend en paramètre une collection de cartes et la rempli avec toutes les cartes crées*/
	public void remplissageCarte(List<Carte> source) {
		source.addAll(this.createDestinee());
		source.addAll(createCoupOeil());
		source.addAll(createTransmigrate());
		source.addAll(createReveBrise());
		source.addAll(createDuperie());
		source.addAll(createVol());
		source.addAll(createCrise());
		source.addAll(createLendemain());
		source.addAll(createLongevite());
		source.addAll(createSemis());
		source.addAll(createVoyage());
		

	}
	
	
	
	

}
