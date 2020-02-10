package model;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import control.Avancer;
import control.Parcours;
import control.RepaintScreen;
import control.Voler;
import view.Affichage;

/**
 * Stocke l'état de ce qu'on doit afficher
 * */
public class Etat {
	
		/** 
		 * hauteur en pixels du saut
		 * */
		private static int saut = 34;
		
	
		/** 
		 * hauteur en pixels à laquelle se trouve l'ovale
		 * */
		private int hauteur;
		
		//instances des threads
		private Parcours parcours;
		private Avancer avance;
		private Voler vol;
		private RepaintScreen repaintScreen;
		/**
		 * position où on arrive sur la 1ere courbe c'est à dire où le score doit etre 0 
		 * */
		private int score0;
		/** 
		 * Constructeur
		 * @param parcours : on prend une instance de {@link Parcours} pour avoir accès à la position de l'ovale
		 */
		public Etat(Parcours parcours) {
			this.hauteur = 100;
			this.parcours = parcours;
			this.score0 = 0;
		}
		
		/** 
		 * on augmente la hauteur de l'ovale de la valeur définie par {@link #saut}
		 * si on ne dépasse pas le cadre du JPanel. sinon on met l'ovale tout en haut
		 * */
		 public void jump() {
			 if(!testPerdu()) {
			 	if(this.hauteur-Etat.saut > 0) {
				 	this.hauteur -= Etat.saut;
		     	}else {
		    	 	this.hauteur = 0;
		     	}
			 }
	     }
		 
		 /**
		  * 
		  * @param c courbe actuelle
		  * @param p point à tester
		  * @return vrai si p est sur la courbe c, false sinon
		  */
		 private boolean onCurve(QuadCurve2D c, Point p){
			 for(double t=0.0; t<1.0;t+=0.001) {
				 Point2D p1 = c.getP1();
				 Point2D p2 = c.getCtrlPt();
				 Point2D p3 = c.getP2();
				 int x = (int) Math.round( (1 - t) * (1 - t) * p1.getX() + 2 * (1 - t) * t * p2.getX() + t * t * p3.getX());
				 int y = (int) Math.round( (1 - t) * (1 - t) * p1.getY() + 2 * (1 - t) * t * p2.getY() + t * t * p3.getY());
				 if(p.x==x && p.y==y) {
					 return true;
				 }
			 }
			 return false;
		 }
		 
		 /**
		  * @param chute nombre de pixels de la chute  
		  * fait baisser la {@link #hauteur} de l'Ovale de {@link Voler#chute} pixels*/
		 public void moveDown(int chute) {
			 if(this.hauteur+chute+Affichage.ovalHeight < Affichage.HAUT && !testPerdu()) {
		        	this.hauteur += chute;
		     }
		 }
		 
		 /**
		  * vrai si l'ovale est sortie de la ligne brisée
		  * @return <p>true si l'ovale est sortie de la ligne brisée </p>
		  * 		<p>false sinon</p>
		  */
		 public boolean testPerdu(){
			 
			 ArrayList<QuadCurve2D> tabCourbes = parcours.getCourbe();
			 //si on a pas encore dépassé la courbe on a pas perdu
			 if(tabCourbes.get(0).getX1() >= Affichage.ovalDec) {
				 this.score0 = parcours.getPosition();
				 return false;
			 }else {
				 //on passe à la courbe suivante si on X2 passe à gauche de l'ovale
				 while(tabCourbes.get(parcours.getIdCourbe()).getX2() <= Affichage.ovalDec){
					parcours.incrIdCourbe();	
				 }
				 
				 int largTraits = (int)Affichage.largTraits.getLineWidth();
				 //pour chaque point sur le segment qui traverse l'ovale en son milieu  
				 for(int i = this.hauteur+largTraits; i<=this.hauteur+Affichage.ovalHeight-largTraits; i+=1) {
					 //si le point est sur la courbe c'est qu'on a pas encore perdu
					 Point actualPoint = new Point(Affichage.ovalDec,i);
					 if(onCurve(tabCourbes.get(parcours.getIdCourbe()), actualPoint)) {
						 return false;
					 }
				 }
				 //si aucun point n'est sur la courbe alors on a perdu
				 return true;
			 }
		 }
		 
		 /**
		  * @return la {@link #hauteur} de l'Ovale
		  * */
		 public int getHauteur() {
			 return this.hauteur;
		 }
		
		/**
		 * @return une instance de la classe {@link Parcours}
		 * */
		public Parcours getParcours() {
			return parcours;
		}
		
		/**
		 * pour ajouter l'instance de {@link Voler} au modèle après l'appel au constructeur d'Etat
		 * @param v
		 */
		public void setVol(Voler v) {
        	this.vol = v;
        }
		/**
		 * pour ajouter l'instance de {@link Avancer} au modèle après l'appel au constructeur d'Etat
		 * @param a
		 */
        public void setAvance(Avancer a) {
        	this.avance = a;
        }
		/**
		 * pour ajouter l'instance de {@link RepaintScreen} au modèle après l'appel au constructeur d'Etat
		 * @param repaintScreen
		 */
        public void setRepaintScreen(RepaintScreen repaintScreen) {
			this.repaintScreen = repaintScreen;
		}
        
		
		/**
		 * fait avancer la position de l'ovale de n pixels
		 * @param n
		 */
		public void avance(int n) {
			this.parcours.incrPos(n);
		}
		
		/**
		 * Arrète les Threads Voler, Avancer et RepaintScreen
		 */
		public void stopThreads() {
			this.vol.terminate();
			this.avance.terminate();
			this.repaintScreen.terminate();
		}
		
		/**
		 * @return la position où le score doit commencer à augmenter (quand on arrive au début de la 1ère courbe) 
		 */
		public int getScore0() {
			return score0;
		}

		
		
}
	