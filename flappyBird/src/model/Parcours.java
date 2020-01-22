package model;
import java.awt.Point;
import java.util.ArrayList;

import java.util.concurrent.ThreadLocalRandom;

import control.Voler;
import view.Affichage;

public class Parcours {
	private ArrayList<Point> points;
	private int position;
	private int incrPoints;
	private int yPrev;
	/**
	 * Constructeur
	 */
	public Parcours() {
		this.points = new ArrayList<Point>();
		this.incrPoints = 0;
		this.yPrev = 0;
		initPoints();
	}
	
	private void initPoints() {
		while(incrPoints < Affichage.LARG){

			this.addPoint();
		}
	}
	
	private void addPoint() {
		
		//On prend x entre i et i+50
		int x = randint(incrPoints,incrPoints+50);
		
		//calcul vitesses
		float Vchute = (float)(Etat.chute) / (float)(Voler.time);
		float Vavance = (float)(Avancer.avancement) / (float)(Avancer.time);
		
		//calcul différence de hauteur max entre 2 points
		int yDiff = (int) (this.yPrev + Vchute * (x-incrPoints) / Vavance);
		
		//calcul yMin et yMax
		int yMin = this.yPrev - yDiff;
		int yMax = this.yPrev + yDiff;
		//vérification yMin et yMax dans la fenetre
		if(yMin < 0) yMin = 0;
		if(yMax > Affichage.HAUT) yMax = Affichage.HAUT;
		//calcul du y du nouveau point
		int y = randint(yMin, yMax);
		this.points.add(new Point (x,y));
		
		//sauvegarde du y
		this.yPrev = y;
		//incrémentation de incrPoints pour l'abscisse du prochain point
		incrPoints+=50;
	}
	
	/** randint
	 * @param int min
	 * @param int max
	 * @return random int between min and max
	 */
	private int randint(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
	private void updateParcours() {
		//on prend le dernier point
		Point lastPoint = points.get(points.size()-1);
		//si il entre dans la fenêtre on en ajoute un nouveau
		if (lastPoint.x -position < Affichage.LARG) {
			this.addPoint();
		}
		
		//on prend le 2 ème point
		Point point1 = this.points.get(1); 
		
		//si il sort de la fenetre on retire le 1er point
		if(point1.x < this.position) {
			points.remove(0);
		}
		
		
	}
	
	
	
	public Point[] getParcours() {
		updateParcours();
		Point[] res = new Point[this.points.size()];
		int i=0;
		for (Point point : points) {
			res[i] = new Point(point.x-this.position, point.y);
			i++;
		}
		return res;
	}

	public int getPosition() {
		return position;
	}

	public void incrPos(int n) {
		this.position += n;
	}
	
}
