package control;

import model.Etat;
import view.Affichage;
/**
 * {@link Thread} qui fait chuter l'ovale toutes les {@link #time} millisecondes
 */
public class Voler extends Thread{
	/**temps en millisecondes entre chaque chute de l'Ovale*/
	public static int time = 65;
	/** 
	 * hauteur en pixels de la chute
	 * */
	public static int chute = 5;
	private boolean running;
	private Etat etat;
	private Affichage affichage;
	
	/**
	 * Constructeur
	 * @param etat : une instance d'{@link Etat} pour appeler {@link Etat#moveDown()}
	 * @param affichage :  une instance d'{@link Affichage} pour appeler {@link Affichage#repaint()}
	 * */
	public Voler(Etat etat, Affichage affichage) {
		this.running = true;
		this.etat = etat;
		this.affichage = affichage;
	}
	
	public void terminate() {
		this.running = false;
	}
	
	/**Méthode 'run' du {@link Thread} {@link Voler} qui lance la chute de l'Ovale
	 * toutes les {@link #time} millisecondes en appelant la méthode {@link Etat#moveDown()}*/
	@Override
	public void run() {
		while(this.running) {
			try { Thread.sleep(Voler.time); this.etat.moveDown(chute);}
			catch (Exception e) { e.printStackTrace(); this.terminate();}
		}
	}
}
