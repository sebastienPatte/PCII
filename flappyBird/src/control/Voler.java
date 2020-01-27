package control;

import model.Etat;
import view.Affichage;
/**
 * {@link Thread} qui fait chuter l'ovale toutes les {@link #time} millisecondes
 */
public class Voler extends Thread{
	/**temps en millisecondes entre chaque chute de l'Ovale*/
	public static int time = 15;
	
	private Etat etat;
	private Affichage affichage;
	
	/**Constructeur*/
	public Voler(Etat etat, Affichage affichage) {
		this.etat = etat;
		this.affichage = affichage;
	}
	/**Méthode 'run' du {@link Thread} {@link Voler} qui lance la chute de l'Ovale
	 * toutes les {@link #time} millisecondes en appelant la méthode {@link Etat#moveDown()}*/
	@Override
	public void run() {
		while(true) {
			try { Thread.sleep(Voler.time); this.etat.moveDown();this.affichage.repaint();}
			catch (Exception e) { e.printStackTrace(); }
		}
	}
}
