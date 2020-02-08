package control;

import model.Etat;

/**
 * Rafraichit l'affichage toutes les {@link #time} millisecondes
 */
public class RepaintScreen extends Thread{
	
	/**
	 * temps entre chaque rafraichissement de l'affichage 
	 */
	public static int time = 41;
	/**
	 * indique si le {@link Thread} tourne encore
	 */
	private boolean running;
	/**
	 * instance de Etat pour lancer le raffraichissement avec {@link Etat#repaint()}
	 */
	private Etat etat;
	
	/**
	 * Constructeur
	 * @param etat
	 */
	public RepaintScreen(Etat etat) {
		this.running = true;
		this.etat = etat;
	}
	
	/**
	 * stoppe le Thread
	 */
	public void terminate() {
		this.running = false;
	}
	
	
	@Override
	public void run() {
		while(this.running) {
			try { Thread.sleep(time);  this.etat.repaint();}
			catch (Exception e) { e.printStackTrace(); this.terminate(); }
		}
	}
}
