package modele;
/**
* l'énumération EnumCouleur comprend les différentes couleurs  du jeux de carte pour pouvoir les manipuler plus facilement
* @author diffo diffo brian- Adrake Dorcas 
* 
*
*/
import java.io.Serializable;

public enum EnumEtat implements Serializable {
	Bousier, serpent, loup, singe, transcendance;
	
	// Méthode pour obtenir l'état suivant
	
	
	/**calcul de l'état suivant lorsque le joueur se trouve dans un état 
	 * cette méthode est utilisée lors de la réincarnation*/
    public EnumEtat getNextState(EnumEtat currentState) {
        // Récupérer l'indice de l'état actuel
        int currentIndex = this.ordinal();

        // Calculer l'indice de l'état suivant (en prenant en compte le dépassement)
        int nextIndex = (currentIndex + 1) % values().length;

        // Récupérer l'état suivant en utilisant l'indice calculé
        EnumEtat nextState = values()[nextIndex];

        return nextState;
    }

}
