package model;

import view.Affichage;

/**
 * Stocke l'état de ce qu'on doit afficher
 * */
public class Etat {
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
		 
		 public void moveDown() {
			 if(this.hauteur+Etat.chute+Affichage.ovalHeight < Affichage.HAUT) {
		        	this.hauteur += Etat.chute;
		     }
		 }
		 
		 public int getHauteur() {
			 return this.hauteur;
		 }

		public Parcours getParcours() {
			return parcours;
		}

		
}
	