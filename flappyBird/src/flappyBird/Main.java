package flappyBird;

import javax.swing.JFrame;
import control.Avancer;
import control.Click;
import control.Parcours;
import control.RepaintScreen;
import control.Voler;
import model.Etat;
import view.Affichage;
import view.VueOiseau;

/**
 * Créé des instances de Etat, Affichage et Control et les relie entre elles 
 * Création d'une fenêtre(JFrame) à laquelle on ajoute l'Affichage(JPanel) 
 */
public class Main {
	
	public static void main(String[] args) {
		
		/* Création des instances de Etat, Affichage et Click*/
		Etat mod = new Etat(new Parcours());
		Affichage aff = new Affichage(mod, new VueOiseau());
		Click click = new Click(mod);

		/* On ajoute l'instance de Click en tant que MouseListener de celle de Affichage*/
		aff.addMouseListener(click);

		// creation du thread Voler
		Voler Vol = new Voler(mod);
		(new Thread(Vol)).start();
		//creation du thread Avancer
		Avancer Avance = new Avancer(mod); 
		(new Thread(Avance)).start();
		//creation du thread RepaintScreen		
		RepaintScreen repaintScreen = new RepaintScreen(aff); 
		(new Thread(repaintScreen)).start();;
		//on passe les instances de Voler, Avancer et RepaintScreen au modèle
		mod.setVol(Vol);
		mod.setAvance(Avance);
		mod.setRepaintScreen(repaintScreen);
		
		/* Création JFrame*/
		JFrame fenetre = new JFrame("suivre la ligne");
		/* ajout de l'Affichage(JPanel) à la fenêtre (JFrame)*/
		fenetre.add(aff);
		
		fenetre.pack();
		fenetre.setVisible(true);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
}


