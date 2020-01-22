package control;

import model.Etat;
import view.Affichage;
/**
 * Thread qui fait tomber l'ovale de 2 pixels toutes les 20 millisecondes
 */
public class Voler extends Thread{
	public static int time = 15;
	private Etat etat;
	private Affichage affichage;
	
	
	public Voler(Etat etat, Affichage affichage) {
		this.etat = etat;
		this.affichage = affichage;
	}
	@Override
	public void run() {
		while(true) {
			try { Thread.sleep(Voler.time); this.etat.moveDown();this.affichage.repaint();}
			catch (Exception e) { e.printStackTrace(); }
		}
	}
}
