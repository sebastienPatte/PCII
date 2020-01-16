package model;

import view.Affichage;

/**
 * Stocke l'état de ce qu'on doit afficher
 * */
public class Etat {
		/** 
		 * hauteur en pixels du saut
		 * */
		private static int saut = 20;
	
		/** 
		 * hauteur en pixels à laquelle se trouve l'ovale
		 * */
		private int hauteur;
		
		
		/** 
		 * Constructeur
		 */
		public Etat() {
			this.hauteur = 10;
			
		}
		
		/** 
		 * on augmente la hauteur de l'ovale de la valeur définie par {@link saut}
		 * si on ne dépasse pas le cadre du JPanel
		 * */
		 public void jump() {
	        if(this.hauteur+Etat.saut+Affichage.ovalHeight < Affichage.HAUT) {
	        	this.hauteur += Etat.saut;
	        }
	     }
		 
		 public int getHauteur() {
			 return this.hauteur;
		 }
}
	