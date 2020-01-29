package model;

import java.awt.Point;

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
		 * hauteur en pixels de la chute
		 * */
		public static int chute = 1;
	
		/** 
		 * hauteur en pixels à laquelle se trouve l'ovale
		 * */
		private int hauteur;
		
		private Parcours parcours;
		
		/** 
		 * Constructeur
		 * @param parcours : on prend une instance de {@link Parcours} pour avoir accès à la position de l'ovale
		 */
		public Etat(Parcours parcours) {
			this.hauteur = 100;
			this.parcours = parcours;
		}
		
		/** 
		 * on augmente la hauteur de l'ovale de la valeur définie par {@link saut}
		 * si on ne dépasse pas le cadre du JPanel. sinon on met l'ovale tout en haut
		 * */
		 public void jump() {
			 if(this.hauteur-Etat.saut > 0) {
		        this.hauteur -= Etat.saut;
		     }else {
		    	 this.hauteur = 0;
		     }
	     }
		 
		 /**fait baisser la {@link #hauteur} de l'Ovale de {@link #chute} pixels*/
		 public void moveDown() {
			 if(this.hauteur+Etat.chute+Affichage.ovalHeight < Affichage.HAUT) {
		        	this.hauteur += Etat.chute;
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
			 
			 //tant que le deuxième point sélectioné est à gauche du milieu de l'ovale, on décal la sélection de 1 point 
			 while(B.x < Affichage.ovalDec) {
				 i++;
				 A = points[i];
				 B = points[i+1];
			 }
			 
			 //pas de game over si on a pas encore atteint le début de la courbe
			 if(A.x>this.parcours.getPosition()-Affichage.ovalDec) {
				 return false;
			 }else {
				 
				float coeffDir = floatDiv(B.y - A.y ,(B.x - A.x));
			 
			 	float Ypos0 = ((-A.x+Affichage.ovalDec) * coeffDir) + A.y;
			 	this.yPos0 = Ypos0;
			 	return this.hauteur >= Ypos0 || this.hauteur+Affichage.ovalHeight <= Ypos0;
			 }
		 }
		 
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

		
}
	