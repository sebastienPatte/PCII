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
		private static int chute = 1;
	
		/** 
		 * hauteur en pixels à laquelle se trouve l'ovale
		 * */
		private int hauteur;
		
		public Parcours parcours;
		
		/** 
		 * Constructeur
		 */
		public Etat() {
			this.hauteur = 100;
			this.parcours = new Parcours();
		}
		
		/** 
		 * on augmente la hauteur de l'ovale de la valeur définie par {@link saut}
		 * si on ne dépasse pas le cadre du JPanel
		 * */
		 public void jump() {
			 if(this.hauteur-Etat.saut > 0) {
		        	this.hauteur -= Etat.saut;
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
}
	