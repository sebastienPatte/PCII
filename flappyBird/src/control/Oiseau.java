package control;

import java.util.concurrent.ThreadLocalRandom;

import model.Etat;
import view.Affichage;

public class Oiseau extends Thread{
	public static int posDiff = 1; 
	private boolean running;
	private int delai;
	private int etat;
	private int hauteur;
	private int position;
	
	public Oiseau(Etat etat) {
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
	private int randint(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	@Override
	public void run() {
		while(this.running) {
			try { 
				Thread.sleep(delai);
				if(this.position > 0) {
					this.position -= posDiff;  
					if(this.etat == 7) {
						this.etat = 0;
					}else {
						this.etat++;
					}
				}else {
					this.terminate();
				}
			}catch (Exception e) {
				e.printStackTrace(); this.terminate(); 
			}
		}
		
		
	}

	public boolean isRunning() {
		return this.running;
	}
	
	public void terminate() {
		this.running = false;
	}
	
	public int getDelai() {
		return delai;
	}


	public void setDelai(int delai) {
		this.delai = delai;
	}


	public int getEtat() {
		return etat;
	}


	public void setEtat(int etat) {
		this.etat = etat;
	}


	public int getHauteur() {
		return hauteur;
	}


	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}


	public int getPosition() {
		return position;
	}


	public void setPosition(int position) {
		this.position = position;
	}
	
	
	
}
