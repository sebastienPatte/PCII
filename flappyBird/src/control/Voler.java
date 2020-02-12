package control;

import model.Etat;

/**
 * {@link Thread} qui fait chuter l'ovale toutes les {@link #time} millisecondes
 */
public class Voler extends Thread{

	/**
	 * temps en millisecondes entre chaque chute de l'Ovale
	 * */
	public static int time = 65;
	
	/** 
	 * hauteur en pixels de la chute
	 * */
	public static int chute = 6;
	
	/**
	 * indique si le {@link Thread} tourne encore
	 * */
	private boolean running;
	
	/**
	 * Instance du modèle pour appeller {@link Etat#moveDown(int)} 
	 * */
	private Etat etat;

	
	/**
	 * Constructeur
	 * @param etat : une instance d'{@link Etat} pour appeler {@link Etat#moveDown}
	 * */
	public Voler(Etat etat) {
		this.running = true;
		this.etat = etat;

	}
	
	/**
	 * stoppe le {@link Thread}
	 */
	public void terminate() {
		this.running = false;
	}
	
	/**
	 * Méthode 'run' du {@link Thread} {@link Voler} qui lance la chute de l'Ovale
	 * toutes les {@link #time} millisecondes en appelant la méthode {@link Etat#moveDown}
	 * */
	@Override
	public void run() {
		while(this.running) {
			try { Thread.sleep(Voler.time); this.etat.moveDown(chute);}
			catch (Exception e) { e.printStackTrace(); this.terminate();}
		}
	}
}
