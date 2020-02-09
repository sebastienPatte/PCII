package control;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;

import java.util.concurrent.ThreadLocalRandom;

import model.Etat;
import view.Affichage;

/**Génère les points de la ligne brisée au fur et à mesure de l'avancement de la {@link #position} 
 * de l'Ovale*/
public class Parcours {
	/**
	 * marge pour éviter d'avoir des points de la courbe collés au bord
	 */
	public static int marge = 40;
	/**
	 * maximum de décalage entre deux points
	 */
	public static int maxDecPoints = 70;
	/**
	 * minimum de décalage entre deux points
	 */
	public static int minDecPoints = 30;
	/**
	 * décalage entre le début de la courbe et la position de départ
	 * (pour que le joueur ait le temps de se positionner avant d'arriver sur la courbe) 
	 */
	public static int decFirstPoint = 400;
	/**
	 * Liste des points de la ligne brisée
	 */ 
	private ArrayList<Point> points;
	/**
	 *  Position de l'ovale sur le Parcours (distance parcourue sans perdre) correspondant au Score du joueur
	 */
	private int position;
	/**
	 * vrai si on a pas encore ajouté le premier point, false sinon
	 */
	private boolean firstPoint;
	/**
	 * indice de la courbe courante (utilisé dans {@link Etat#testPerdu()})
	 */
	private int idCourbe = 0;
	/**
	 * stocke la position x du point précédent
	 */
	private int xPrev;
	/**
	 * stocke la position y du point précédent
	 */
	private int yPrev;
	
	/**Constructeur*/
	public Parcours() {
		this.position = 0;
		this.points = new ArrayList<Point>();
		this.yPrev = 0;
		this.xPrev = 0;
		this.firstPoint = true;
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
		int x =0;
		if(this.firstPoint) {
			x = decFirstPoint;
			this.firstPoint=false;
		}else {
			//On prend x entre minDecPoints et maxDecPoints
			x = randint(xPrev+minDecPoints,xPrev+maxDecPoints);
		}
		
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
		//if(yMax < marge) yMax = marge;
		if(yMax > Affichage.HAUT-marge) yMax = Affichage.HAUT-marge ;

		//calcul du y du nouveau point
		int y = randint(yMin, yMax);
		//nouveau point
		Point newPoint = new Point (x,y);
		
		//calcul du milieu entre ancient et nouveau point
		int midX = xPrev + (newPoint.x - xPrev)/2;
		int midY = yPrev + (newPoint.y - yPrev)/2;
		
		//ajout du milieu
		this.points.add(new Point(midX,midY));
		//ajout du nouveau point
		this.points.add(newPoint);
		
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
	
	public ArrayList<QuadCurve2D> getCourbe() {
		
		updateParcours();
		Point[] tabPoints = getParcours();
		ArrayList<QuadCurve2D> res = new ArrayList<QuadCurve2D>();
		for(int i=0; i+4 < tabPoints.length; i+=2){
			
			QuadCurve2D courbe = new QuadCurve2D.Double();
			Point A = tabPoints[i];
			Point B = tabPoints[i+1];
			Point C = tabPoints[i+2];
			
			Point2D.Double debut = new Point2D.Double(A.x,A.y);
			Point2D.Double ctrl = new Point2D.Double(B.x,B.y);
			Point2D.Double fin = new Point2D.Double(C.x,C.y);
			courbe.setCurve(debut,ctrl,fin);
			res.add(courbe);
		}
		
		return res;
	}
	
	/**Supprime les points qui ne sont plus dans la vue et ajoute des nouveaux points si nécessaire*/
	private void updateParcours() {
		//on prend le dernier point
		Point lastPoint = points.get(points.size()-1);
		//si il entre dans la fenêtre on en ajoute deux nouveaux
		if (lastPoint.x - (this.position+Affichage.ovalDec)-70 < Affichage.LARG) {
			this.addPoint();
			this.addPoint();
		}
		
		//on prend le 3 ème point
		Point point1 = this.points.get(2);
		//si il sort de la fenetre on retire les 2 premiers point
		if(point1.x < this.position) {
			points.remove(0);
			points.remove(0);
			//on decr l'id car on supprime un elt
			decrIdCourbe();
			
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
	
	public void incrIdCourbe() {
		this.idCourbe++;
	}
	
	public void decrIdCourbe() {
		this.idCourbe--;
	}
	
	public int getIdCourbe() {
		return this.idCourbe;
	}
}
