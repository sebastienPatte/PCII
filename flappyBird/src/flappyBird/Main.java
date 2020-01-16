package flappyBird;

import javax.swing.JFrame;

import control.Control;
import model.Etat;
import view.Affichage;

public class Main {
	public static void main(String[] args) {
		/* Création des instances de Etat, Affichage et Control*/
		Etat mod = new Etat();
		Affichage aff = new Affichage(mod);
		Control ctrl = new Control(aff,mod);
		/* On ajoute l'instance de Control en tant que MouseListener de celle de Affichage*/
		aff.addMouseListener(ctrl);
		
		/* Création JFrame*/
		JFrame fenetre = new JFrame("titre de la fenêtre");
		/* ajout de l'Affichage(JPanel) à la fenêtre (JFrame)*/
		fenetre.add(aff);
		
		fenetre.pack();
		fenetre.setVisible(true);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}


