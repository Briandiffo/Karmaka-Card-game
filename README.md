<h1> Jeux de Carte Karmaka en java  </h1>

<h2>Introduction </h2>

<p>Karmaka est un jeu de cartes tactique qui se déroule dans un univers karmique. Voici quelques détails sur le jeu :</p>

<ul>
  <li>Chaque joueur commence la partie comme un humble bousier.</li>
  <li>Vie après vie, vous progresserez sur l'échelle karmique, dans le but d'atteindre la transcendance en premier.</li>
  <li>Trois possibilités s'offrent à vous : œuvrez pour votre vie actuelle, préparez votre vie future ou utilisez les pouvoirs des cartes à votre disposition.</li>
  <li>Les cartes rouges sont nihilistes et destructrices ; les cartes vertes sont bienfaitrices et impétueuses ; les cartes bleues sont astucieuses et sournoises.</li>
  <li>Un des éléments clés de Karmaka est qu'après avoir joué une carte pour son pouvoir, un rival pourra la récupérer pour l'ajouter à sa vie future. Vos actions passées pourraient bien revenir vous hanter au cours de votre prochaine vie.</li>
</ul>

<p>Karmaka est donc un jeu de stratégie et d'anticipation qui se déroule sur de multiples vies.</p>


<h2>les différentes classes du package java </h2>

<table>
  <tr>
    <th>Classe</th>
    <th>Rôle</th>
  </tr>
  <tr>
    <td>CardFactory</td>
    <td>Classe qui s'occupe de la création des différentes cartes du jeux</td>
  </tr>
  <tr>
    <td>Carte</td>
    <td>Représentes une carte du jeux en elle-même ainsi que tous ses attributs</td>
  </tr>
  <tr>
    <td>EnumCouleur</td>
    <td>Enumération avec les couleurs de chaque cartes</td>
  </tr>
  <tr>
    <td>EnumEtat</td>
    <td>Enumération avec les différents états possibles pour les joueurs</td>
  </tr>
  <tr>
    <td>Joueur</td>
    <td>Classe représentant un joueur qu'il soir virtuel ou réel ainsi que tous les attributs qu'ils ont en commun</td>
  </tr>
  <tr>
    <td>JoueurPhysique</td>
    <td>Représente un joueur physique (ensemble des méthodes et attributs propres au joueurs physique)</td>
  </tr>
  <tr>
    <td>JoueurVirtuel</td>
    <td>Représente un joueur virtuel (ensemble des méthodes et attributs propres aux joueurs virtuels)</td>
  </tr>
  <tr>
    <td>Partie</td>
    <td>Il s'agit ici de la classe qui comprendra toute la mécanique du jeu pour le deroullement des parties, le switch entre deux joueurs, la distribution de cartes</td>
  </tr>
  <tr>
    <td>PouvoirCarte</td>
    <td>Classes qui implémente les méthodes correspondantes aux pouvoirs de chaque carte</td>
  </tr>
  <tr>
    <td>Strategie</td>
    <td>Interface implémenté par les deux classes JoueurPhysique et JoueurVirtuel avec la méthode play qui définit leur façon de jouer</td>
  </tr>
  <tr>
    <td>Controlleur</td>
    <td></td>
  </tr>
  <tr>
    <td>ControleurJoueur</td>
    <td>Il s'agit de la classe qui servira de moyen d'interaction entre la classe joueur et l'interface graphique</td>
  </tr>
  <tr>
    <td>ControleurPartie</td>
    <td>Il s'agit de la classe qui servira de moyen d'interaction entre la classe partie et l'interface graphique</td>
  </tr>
  <tr>
    <td>Vue</td>
    <td></td>
  </tr>
  <tr>
    <td>Game</td>
    <td>Il s'agit ici de l'interface principal du jeux</td>
  </tr>
</table>


<h2>Les patrons de conception utilisés</h2>

<ol>
  <li><b>Le patron de conception stratégie :</b> <br><p>Ce patron a été utilisé pour implémenter les façons de jouer d'un joueur physique et d'un joueur virtuel, étant donné que ceux-ci ne partagent pas la même mécanique de jeu.</p></li>
  <li><b>Le patron de conception listener :</b> <br> <p>Il a été utilisé ici pour permettre à l'interface graphique d'observer la classe Joueur afin de se mettre à jour lorsque une caractéristique d'un joueur change.</p></li>
  <li><b>Le patron de conception MVC :</b> <br> <p>Il a été utilisé ici afin de permettre une gestion simple et cohérente des interactions entre les interfaces graphiques et les classes représentant les objets du jeu.</p></li>
</ol>


<h2> Diagramme de classes</h2>
<p>Il s'agit ici d'un diagrammes de classes qui permet d'illustrer les différentes classes du jeux ainsi que la manière dont elles interagissent </p>

![image](https://github.com/Briandiffo/Karmaka-Card-game/assets/112263899/6dcdbaf5-8d6b-4de3-87ad-3e0a2d3ded08)


<h2>Présentation de l'exécution du jeux </h2>

<h3>Première page du jeux (Menu) </h3>

<p>Dans cette partie , le joueur ca entrer son nom et aura le choix entre commencer une nouvelle partie contre l'odinateur ou en core de continuer une partie sauvegardée au préalable </p>

![image](https://github.com/Briandiffo/Karmaka-Card-game/assets/112263899/5082b202-bc15-479d-a941-34a1203faebc)

<h3>deuxième page , le jeux en lui même </h3>
<p> il s'agit de la page de deroulemment du jeux , avec tous les boutons que peut utiliser le joueur pour agencer son jeux ainsi que les information concernant son jeux de carte ou son état</p>


![image](https://github.com/Briandiffo/Karmaka-Card-game/assets/112263899/c8bdfe01-5c78-4348-b070-bed03a5f8443)


