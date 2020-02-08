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
		 * POUR TEST
		 */
		public float yPos0;
	
		/** 
		 * hauteur en pixels du saut
		 * */
		private static int saut = 20;
		
	
		/** 
		 * hauteur en pixels à laquelle se trouve l'ovale
		 * */
		private int hauteur;
		
		private Parcours parcours;
		private Avancer avance;
		private Voler vol;
		private RepaintScreen repaintScreen;
		private Affichage aff;
		/** 
		 * Constructeur
		 * @param parcours : on prend une instance de {@link Parcours} pour avoir accès à la position de l'ovale
		 */
		public Etat(Parcours parcours) {
			this.hauteur = 100;
			this.parcours = parcours;
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
		 
		 private boolean onCurve(QuadCurve2D c, Point p){
			 for(double t=0.0; t<1.0;t+=0.001) {
				 Point2D p1 = c.getP1();
				 Point2D p2 = c.getCtrlPt();
				 Point2D p3 = c.getP2();
				 int x = (int) Math.round( ((1 - t) * (1 - t) * p1.getX() + 2 * (1 - t) * t * p2.getX() + t * t * p3.getX()));
				 int y = (int) Math.round(((1 - t) * (1 - t) * p1.getY() + 2 * (1 - t) * t * p2.getY() + t * t * p3.getY()));
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
		  * */
		 public boolean testPerdu() {
			 
			 ArrayList<QuadCurve2D> tabCourbes = parcours.getCourbe();
			 if(tabCourbes.get(0).getX1() >= Affichage.ovalDec) {
				 return false;
			 }else {
				 //on skip curve si on X2 passe à gauche de l'ovale
				 while(tabCourbes.get(parcours.getIdCourbe()).getX2() <= Affichage.ovalDec) {
					parcours.incrIdCourbe();	
				 }
				 
				 //pour chaque point ds l'ovale
				 for(int i =this.hauteur; i<=this.hauteur+Affichage.ovalHeight; i+=1) {
					 
					 Point actualPoint = new Point(Affichage.ovalDec,i);
					 if(onCurve(tabCourbes.get(parcours.getIdCourbe()), actualPoint)) {
						 return false;
					 }
				 }
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
		
		public void setAff(Affichage aff) {
			this.aff = aff;
		}
		
		public void setVol(Voler v) {
        	this.vol = v;
        }
        public void setAvance(Avancer a) {
        	this.avance = a;
        }
		
        /**
         * appelle {@link Affichage#repaint}
         */
		public void repaint() {
			this.aff.repaint();
		}
		
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

		public void setRepaintScreen(RepaintScreen repaintScreen) {
			this.repaintScreen = repaintScreen;
		}
		
}
	