package control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Etat;
import view.Affichage;

/**
 * lance la fonction jump de {@link Etat} 
 * si l'utilisateur clique sur la fenêtre
 */
public class Control extends MouseAdapter{
	/** attribut de type {@link Affichage} */
	private Affichage affichage;
	/** attribut de type {@link Etat} */
	private Etat etat;
	
	/**
	 * Constructeur
	 * @param affichage de type {@link Affichage} 
	 * @param etat de type {@link Etat} 
	 */
	public Control(Affichage affichage, Etat etat){
		this.affichage = affichage;
		this.etat = etat;
	}
	
	/**
	 * fonction appellée si l'utilisateur clique sur la fenêtre :
	 * on appelle la fonction {@link Etat#jump()}
	 * puis on actualise l'affichage avec {@link #affichage}.repaint()
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		this.etat.jump();
		this.affichage.repaint();
	}


}

