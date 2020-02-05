package control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Etat;

/**
 * lance la fonction jump de {@link Etat} 
 * si l'utilisateur clique sur la fenêtre
 */
public class Click extends MouseAdapter{

	/** attribut de type {@link Etat} */
	private Etat etat;
	
	/**
	 * Constructeur 
	 * @param etat de type {@link Etat} 
	 */
	public Click(Etat etat){
		this.etat = etat;
	}
	
	/**
	 * fonction appellée si l'utilisateur clique sur la fenêtre :
	 * on appelle la fonction {@link Etat#jump()}
	 * puis on actualise l'affichage avec {@link Etat#repaint}
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		this.etat.jump();
	}


}

