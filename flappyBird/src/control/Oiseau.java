package control;

import java.util.concurrent.ThreadLocalRandom;

import view.Affichage;

public class Oiseau extends Thread{
	public static int posDiff = 1; 
	private boolean running;
	private int delai;
	private int state;
	private int hauteur;
	private int position;
	
	/**
	 * on choisit un {@link #delai} et une {@link #hauteur} aléatoire
	 * et une position de 50 pixels à droite de la fenêtre
	 */
	public Oiseau() {
		this.running = true;
		this.delai = randint(24, 200);
		this.hauteur = randint(0,Affichage.HAUT);
		this.position = Affichage.LARG +50;
	}
	
	/** Génère un chiffre aléatoire entre min et max
	 * @param int min
	 * @param int max
	 * @return random int between min and max
	 */
	public int randint(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
	/**
	 * TODO
	 */
	@Override
	public void run() {
		while(this.running) {
			try { 
				Thread.sleep(delai);
				if(this.position > 0) {
					this.position -= posDiff;  
					if(this.state == 7) {
						this.state = 0;
					}else {
						this.state++;
					}
				}else {
					this.terminate();
				}
			}catch (Exception e) {
				e.printStackTrace(); this.terminate(); 
			}
		}
		
		
	}
	
	/**
	 * @return true si le thread tourne encore, false sinon
	 */
	public boolean isRunning() {
		return this.running;
	}
	
	/**
	 * stoppe le {@link Thread}
	 */
	public void terminate() {
		this.running = false;
	}

	/**
	 * @return l'{@link #state état} de l'oiseau
	 */
	public int getStateBird() {
		return state;
	}
	
	/**
	 * @return la {@link #hauteur} de l'oiseau
	 */
	public int getHauteur() {
		return hauteur;
	}

	/**
	 * @return la {@link #position} de l'oiseau
	 */
	public int getPosition() {
		return position;
	}
}
