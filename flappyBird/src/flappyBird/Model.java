package flappyBird;

public class Model {
		private int hauteur;
		private int saut;
		
		public Model() {
			this.hauteur = 10;
			this.saut = 20;
		}
		
		/* on augmente la hauteur */
		 public void jump() {
	        if(this.hauteur+this.saut+Affichage.ovalHeight < Affichage.HAUT) {
	        	this.hauteur += this.saut;
	        }
	     }
		 
		 public int getHauteur() {
			 return this.hauteur;
		 }
}
