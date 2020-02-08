package view;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.ImageIcon;
import control.Oiseau;

public class VueOiseau{
	
	
	
	/**
	 * chemin vers les images de l'oiseau
	 */
	public static String Path= "bird/";
	
	/**
	 * Nombre maximum d'oiseaux 
	 */
	public static int MaxOiseaux= 5;
	
	/**
	 * on a une chance sur probaOiseau qu'on oiseau apparaisse
	 */
	public static int probaOiseau= 100;
	
	/**
	 * liste des oiseaux
	 */
	private ArrayList<Oiseau> list;
	
	/**
	 * initialise la {@link #list liste d'oiseaux}
	 */
	public VueOiseau() {
		this.list = new ArrayList<Oiseau>();
	}
	
	/**
	 * dessine les oiseaux qui sont dans la fenetre, supprime ceux qui sont terminés
	 * @param g
	 */
	void dessiner(Graphics g) {
		
		//générer oiseaux 
		this.genererOiseaux();
		
		for(int i = 0; i < this.list.size(); i++){
			Oiseau oiseau = this.list.get(i);
			
			//si l'oiseau s'est arreté, on le retire de la liste  
			if(!oiseau.isRunning()) {
				this.list.remove(oiseau);
			}
			
			
			
			String str = Path+oiseau.getStateBird()+".png";
			
			ImageIcon icon = new ImageIcon(str);
			
			Image image = icon.getImage();
			
			g.drawImage(image, oiseau.getPosition(), oiseau.getHauteur(), null);

		}
	}

	/**
	 * à chaque rafraichissement de l'affichage on génère un oiseau avec 1 chance sur 10 000 si on a pas dépassé le {@link #MaxOiseaux maximum d'oiseaux}
	 */
	void genererOiseaux() {
		//on génére des oiseaux seulement si on a pas dépassé la taille max
		if(this.list.size() < MaxOiseaux){
			//on génère un oiseau si on tombe sur 1 
			if(randint(1,probaOiseau) == 1 ) {
				Oiseau oiseau = new Oiseau();
				(new Thread(oiseau)).start();
				this.list.add(oiseau);
			}
		}	
	}
	
	/**
	 * termine tous les Threads Oiseau, appelé lors du Game Over
	 */
	public void finiOiseaux() {
		for(Oiseau oiseau : this.list) {
			oiseau.terminate();
		}
	}

	/** Génère un chiffre aléatoire entre min et max
	 * @param min
	 * @param max
	 * @return random int between min and max
	 */
	public int randint(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	

	
}
