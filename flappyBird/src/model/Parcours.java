package model;
import java.awt.Point;
import java.util.ArrayList;

import java.util.concurrent.ThreadLocalRandom;

import view.Affichage;

public class Parcours {
	private ArrayList<Point> points;
	private int position;
	/**
	 * Constructeur
	 */
	public Parcours() {
		this.points = new ArrayList<Point>();
		for(int i=0; i < Affichage.LARG; i+=50) {
			//On prend x entre i et i+50
			int x = randint(i,i+50);
			int y = randint(0, Affichage.HAUT);
			Point p = new Point(x,y);
			this.points.add(p);
		}
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
		ArrayList<Point> res = new ArrayList<Point>();
		
		for (Point point : this.points) {
			if(point.x >= this.position && point.x + this.position <= Affichage.LARG ) {
				res.add(point);
			}
			
		}
		
		this.points = res;
		
		
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
