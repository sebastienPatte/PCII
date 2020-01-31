package view;

import model.Etat;

public class RepaintScreen extends Thread{
	
	public static int time = 41;
	private boolean running;
	private Etat etat;
	
	public RepaintScreen(Etat etat) {
		this.running = true;
		this.etat = etat;
	}
	
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
