package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;

import control.Oiseau;
import model.Etat;

public class VueOiseau implements ImageObserver{
	public static String Path= "bird/";
	public static int MaxOiseaux= 5;
	
	private ArrayList<Oiseau> list;
	
	
	public VueOiseau(Etat etat) {
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
			
			
			
			String str = Path+oiseau.getEtat()+".png";
			
			ImageIcon icon = new ImageIcon(str);
			
			Image image = icon.getImage();
			
			g.drawImage(image, oiseau.getPosition(), oiseau.getHauteur(), this);

		}
	}

	void genererOiseaux() {
		//on génére des oiseaux seulement si on a pas dépassé la taille max
		if(this.list.size() < MaxOiseaux){
			//une chance sur 10 000 qu'un oiseau apparaisse
			if(randint(0,10000) == 0 ) {
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
	 * @param int min
	 * @param int max
	 * @return random int between min and max
	 */
	public int randint(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
	
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}
	
}
