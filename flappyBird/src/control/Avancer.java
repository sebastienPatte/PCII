package control;

import model.Etat;

/**
 * {@link Thread} qui fait avancer la {@link Parcours#position position}  
 * de {@link #avancement} pixels toutes les de {@link #time} millisecondes.
 */
public class Avancer extends Thread{
	
	/**temps en millisecondes entre chaque avancement de la {@link Parcours#position position}
	 * de l'Ovale de {@link #avancement} pixels.*/
	public static int time = 100;
	
	/**Nombre de pixels d'avancement de la {@link Parcours#position position}
	 * de l'Ovale toutes les {@link #time} millisecondes.*/
	public static int avancement = 2;
	
	private boolean running;
	
	private Etat etat;
	
	/**
	 * Constructeur
	 * @param etat : instance de {@link Etat} pour appeller {@link Etat#avance} 
	 * */
	public Avancer(Etat etat) {
		this.running = true;
		this.etat = etat;
	}
	
	public void terminate() {
		this.running = false;
	}
	/**Méthode 'run' du {@link Thread} {@link Avancer} qui lance l'avancement de la {@link Parcours#position position}
	 * toutes les {@link #time} millisecondes.*/
	@Override
	public void run() {
		while(this.running) {
			try { Thread.sleep(time); this.etat.avance(avancement);}
			catch (Exception e) { e.printStackTrace(); this.terminate(); }
		}
	}
}
