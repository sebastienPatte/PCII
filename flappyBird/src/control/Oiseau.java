package control;

import java.awt.Image;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.ImageIcon;
import view.Affichage;
import view.VueOiseau;

public class Oiseau extends Thread{
	/**
	 * delai minimum entre chaque maj de l'oiseau
	 */
	public static int delaiMin = 50; 
	/**
	 * delai maximum entre chaque maj de l'oiseau
	 */
	public static int delaiMax = 100; 
	/**
	 * délai entre chaque mise à jour de l'oiseau (dont son décalage vers la gauche)
	 */
	private int delai;
	/**
	 * indique l'état de l'oiseau (correspondant à une image dans "/bird")
	 */
	private int state;
	/**
	 * hauteur de l'oiseau
	 */
	private int hauteur;
	/**
	 * position de l'oiseau (relative à la position de l'ovale)
	 */
	private int position;
	/**
	 * indique si le {@link Thread} tourne encore
	 */
	private boolean running;
	
	/**
	 * on choisit un {@link #delai} et une {@link #hauteur} aléatoire
	 * et une position de 50 pixels à droite de la fenêtre
	 */
	public Oiseau() {
		this.running = true;
		this.delai = randint(delaiMin, delaiMax);
		this.hauteur = randint(0,Affichage.HAUT-maxHeightImg());
		this.position = Affichage.LARG +50;
	}
	
	/** Génère un chiffre aléatoire entre min et max
	 * @param min
	 * @param max
	 * @return random int between min and max
	 */
	public int randint(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
	/**
	 * <p>si l'oiseau est encore sur la fenetre on le deplace vers la gauche et on change son état</p>
	 * <p>sinon on arrète le Thread</p>
	 */
	@Override
	public void run() {
		while(this.running) {
			try { 
				Thread.sleep(delai);
				//si l'oiseau est encore sur la fenetre on le deplace vers la gauche et on change son état 
				if(this.position > -maxWidthImg()){
					this.position -= (this.delai-(delaiMin-1))/2;  
					if(this.state == 7) {
						this.state = 0;
					}else {
						this.state++;
					}
				//sinon on arrète le Thread
				}else {
					this.terminate();
				}
			}catch (Exception e) {
				e.printStackTrace(); this.terminate(); 
			}
		}
		
		
	}
	
	/**
	 * @return la largeur de l'image d'oiseau la plus large
	 */
	private int maxWidthImg() {
		int max = 0;
		for(int i=0; i<7; i++) {
			String str = VueOiseau.Path+i+".png";
			ImageIcon icon = new ImageIcon(str);
			Image image = icon.getImage();
			if(image.getWidth(null) > max) {
				max = image.getWidth(null);
			}
		}
		return max;
	}
	
	/**
	 * @return la largeur de l'image d'oiseau la plus large
	 */
	private int maxHeightImg() {
		int max = 0;
		for(int i=0; i<7; i++) {
			String str = VueOiseau.Path+i+".png";
			ImageIcon icon = new ImageIcon(str);
			Image image = icon.getImage();
			if(image.getHeight(null) > max) {
				max = image.getHeight(null);
			}
		}
		return max;
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
