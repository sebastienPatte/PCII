package model;

import view.Affichage;

/**
 * Implémente un {@link Thread} pour faire avancer la {@link Parcours#position position}  
 * de {@link #avancement} pixels toutes les de {@link #time} millisecondes.
 */
public class Avancer extends Thread{
	
	/**temps en millisecondes entre chaque avancement de la {@link Parcours#position position}
	 * de l'Ovale de {@link #avancement} pixels.*/
	public static int time = 100;
	
	/**Nombre de pixels d'avancement de la {@link Parcours#position position}
	 * de l'Ovale toutes les {@link #time} millisecondes.*/
	public static int avancement = 1;
	
	private Parcours parcours;
	private Affichage affichage;
	
	/**Constructeur*/
	public Avancer(Parcours parcours, Affichage affichage) {
		this.parcours = parcours;
		this.affichage = affichage;
	}
	
	@Override
	/**Méthode 'run' du thread Avancer qui lance l'avancement de la {@link Parcours#position position}
	 * toutes les {@link #time} millisecondes.*/
	public void run() {
		while(true) {
			try { Thread.sleep(time); this.parcours.incrPos(avancement); System.out.println(this.parcours.getPosition()); this.affichage.repaint();}
			catch (Exception e) { e.printStackTrace(); }
		}
	}
}
