package modele;
/**
* l'interface stratégie décrit le fait que  pour un joueur physique ou virtuel on aura une manière de jouer différente 
* ainsiq , tout les deux auront leur implementation propre de la méthode Play()
* @author diffo diffo brian- Adrake Dorcas 
* 
*
*/
import java.util.*;
public interface Strategie  {
	public Carte play(String coup);

}
