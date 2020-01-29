package control;
import java.awt.Point;
import java.util.ArrayList;

import java.util.concurrent.ThreadLocalRandom;

import view.Affichage;

/**Génère les points de la ligne brisée au fur et à mesure de l'avancement de la {@link #position} 
 * de l'Ovale*/
public class Parcours {
	public static int marge = 40;
	public static int maxDecPoints = 20;
	public static int minDecPoints = 50;
	/**Liste des points de la ligne brisée*/
	private ArrayList<Point> points;
	
	/** Position de l'ovale sur le Parcours (distance parcourue sans perdre) correspondant au Score du joueur*/
	private int position;
	private int xPrev;
	private int yPrev;

	
	/**Constructeur*/
	public Parcours() {
		this.position = 0;
		this.points = new ArrayList<Point>();
		this.yPrev = 0;
		this.xPrev = 0;
		initPoints();
	}
	
	/**Initialise la liste {@link #points}*/
	private void initPoints() {
		while(xPrev < Affichage.LARG){
			this.addPoint();
		}
	}

	/**Génère un nouveau point et l'ajoute à la liste {@link #points}*/
	private void addPoint() {
		//On prend x entre minDecPoints et maxDecPoints
		int x = randint(xPrev+minDecPoints,xPrev+maxDecPoints);
		
		//calcul vitesses
		float Vchute = (float)(Voler.chute) / (float)(Voler.time);
		float Vavance = (float)(Avancer.avancement) / (float)(Avancer.time);
		
		//calcul différence de hauteur max entre 2 points
		int yDiff = (int) (Vchute * (x-xPrev) / Vavance);
		
		//calcul yMin et yMax
		int yMin = this.yPrev - yDiff;
		int yMax = this.yPrev + yDiff;
		//vérification yMin et yMax dans la fenetre
		if(yMin < marge) yMin = marge;
		if(yMax < marge) yMax = marge;
		if(yMax > Affichage.HAUT-marge) yMax = Affichage.HAUT-marge ;
		//calcul du y du nouveau point
		int y = randint(yMin, yMax);
		this.points.add(new Point (x,y));
		
		//sauvegarde du y
		this.yPrev = y;
		//sauvegarde du x
		this.xPrev = x;
	}
	
	/** Génère un chiffre aléatoire entre min et max
	 * @param int min
	 * @param int max
	 * @return random int between min and max
	 */
	private int randint(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
	/**Supprime les points qui ne sont plus dans la vue et ajoute des nouveaux points si nécessaire*/
	private void updateParcours() {
		//on prend le dernier point
		Point lastPoint = points.get(points.size()-1);
		//si il entre dans la fenêtre on en ajoute un nouveau
		if (lastPoint.x - (this.position+Affichage.ovalDec) < Affichage.LARG) {
			this.addPoint();
		}
		
		//on prend le 2 ème point
		Point point1 = this.points.get(1);
		//si il sort de la fenetre on retire le 1er point
		if(point1.x < this.position) {
			points.remove(0);
		}
	
	}
	
	
	/**
	 * renvoie les points à afficher
	 * @return Point[]
	 * */
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
	
	/**
	 * renvoie la position de l'Ovale
	 * @return {@link #position}
	 * */
	public int getPosition() {
		return position;
	}

	/**
	 * augmente la {@link #position} de l'Ovale de n
	 * @param n : taille (en pixels) de l'augmentation de la position  
	 * */
	public void incrPos(int n) {
		this.position += n;
	}
	
}
