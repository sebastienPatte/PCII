package model;
import java.awt.Point;
import java.util.ArrayList;

import java.util.concurrent.ThreadLocalRandom;

import view.Affichage;

public class Parcours {
	
	private ArrayList<Point> points;
	
	public Parcours() {
		int x = 50;
		this.points = new ArrayList<Point>();
		while(x <= Affichage.LARG) {
			
			int y = ThreadLocalRandom.current().nextInt(30, Affichage.HAUT -30 + 1);
			Point p = new Point(x,y);
			this.points.add(p);
			x += ThreadLocalRandom.current().nextInt(0, Affichage.LARG/5 + 1);
		}
	}
	
	public Point[] getParcours() {
		Point[] res = new Point[this.points.size()];
		int i=0;
		for (Point point : points) {
			res[i] = point;
			i++;
		}
		return res;
	}
	
}
