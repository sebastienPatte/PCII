package flappyBird;

import javax.swing.JFrame;

import control.Control;
import control.Voler;
import model.Avancer;
import model.Etat;
import model.Parcours;
import view.Affichage;
/**
 * Créé des instances de Etat, Affichage et Control et les relie entre elles 
 * Création d'une fenêtre(JFrame) à laquelle on ajoute l'Affichage(JPanel) 
 */
public class Main {
	
	public static void main(String[] args) {
		
		
		/* Création des instances de Etat, Affichage et Control*/
		Etat mod = new Etat(new Parcours());
		Affichage aff = new Affichage(mod);
		Control ctrl = new Control(aff,mod);
		/* On ajoute l'instance de Control en tant que MouseListener de celle de Affichage*/
		aff.addMouseListener(ctrl);
		
		// ajout du thread Voler
		Voler Vol = new Voler(mod,aff);
		(new Thread(Vol)).start();
		//ajout du thread Avancer
		Avancer Avance = new Avancer(mod.getParcours(),aff); 
		(new Thread(Avance)).start();;
		
		//on passe les instances de Voler et Avancer à L'Affichage
		aff.setVol(Vol);
		aff.setAvance(Avance);
		
		/* Création JFrame*/
		JFrame fenetre = new JFrame("titre de la fenêtre");
		/* ajout de l'Affichage(JPanel) à la fenêtre (JFrame)*/
		fenetre.add(aff);
		
		fenetre.pack();
		fenetre.setVisible(true);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
}


