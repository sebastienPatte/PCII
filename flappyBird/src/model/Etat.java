package model;

import java.awt.Point;

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
		private static int saut = 30;
		
	
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
		 
		 /**fait baisser la {@link #hauteur} de l'Ovale de {@link #chute} pixels*/
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
			 Point[] points = this.parcours.getParcours();
			 int i=0;
			 Point A = points[i];
			 Point B = points[i+1];
			 
			 //pas de game over si on a pas encore atteint le début de la courbe
			 if(A.x>=Affichage.ovalDec) {
				 return false;
			 }else {
				 
				 
				 //tant que le deuxième point sélectioné est à gauche du milieu de l'ovale, on décal la sélection de 1 point 
				 while(B.x < Affichage.ovalDec) {
					 i++;
					 A = points[i];
					 B = points[i+1];
				 }
				float coeffDir = floatDiv(B.y - A.y ,(B.x - A.x));
			 
			 	float Ypos0 = ((-A.x+Affichage.ovalDec) * coeffDir) + A.y;
			 	this.yPos0 = Ypos0;
			 	return this.hauteur + Affichage.largTraits.getLineWidth() >= Ypos0 || this.hauteur+Affichage.ovalHeight - Affichage.largTraits.getLineWidth() <= Ypos0;
			 }
		 }
		 
		 /**
		  * renvoie la division flottante de deux entiers sous forme d'un flottant
		  * @param  x
		  * @param  y
		  * @return float : division flottante de x et y
		  **/
		 private float floatDiv(int x, int y) {
			 return (float)(x) / (float)(y);
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
		 * Arrète les Threads Voler et Avancer
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
	