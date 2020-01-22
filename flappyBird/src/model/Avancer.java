package model;

import view.Affichage;

public class Avancer extends Thread{

	public static int time = 500;
	public static int avancement = 1;
	private Parcours parcours;
	private Affichage affichage;
	
	public Avancer(Parcours parcours, Affichage affichage) {
		this.parcours = parcours;
		this.affichage = affichage;
	}
	
	@Override
	public void run() {
		while(true) {
			try { Thread.sleep(time); this.parcours.incrPos(avancement); System.out.println(this.parcours.getPosition()); this.affichage.repaint();}
			catch (Exception e) { e.printStackTrace(); }
		}
	}
}
