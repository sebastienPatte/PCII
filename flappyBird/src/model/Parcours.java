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
	
	public Point[] getParcours() {
		Point[] res = new Point[this.points.size()];
		int i=0;
		for (Point point : points) {
			if(point.x - this.position <= Affichage.LARG ) {
				res[i] = point;
				i++;
			}
		}
		return res;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
}
