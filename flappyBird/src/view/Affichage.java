package view;

import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

import model.Etat;


/**
 * la classe Affichage est le JPanel dans lequel on va afficher l'ovale 
 */
public class Affichage extends JPanel {

		private static final long serialVersionUID = 1L;
		
		/** largeur du JPanel*/
        public static final int LARG = 600;
        /** hauteur du JPanel*/
        public static final int HAUT = 400;
        /** hauteur de l'ovale*/
        public static final int ovalHeight = 70;
        
        /** attribut de type {@link Etat}*/
        public Etat etat;

        
        /** 
         * Constructeur:
         * @param etat de type {@link Etat}
         * 
         * */
        public Affichage(Etat etat) {
                this.setPreferredSize(new Dimension(LARG, HAUT));
                this.etat = etat;
        }

        
        /**
         * nettoye l'affichage puis affiche l'ovale à l'endroit défini par l'{@link Etat}
         * */
        @Override
        public void paint(Graphics g) {
        	//nettoie le JFrame puis affiche l'ovale au niveau de la hauteur définie par le Model etat
        	g.clearRect(0, 0, LARG, HAUT);
        	
        	g.drawOval(10,this.etat.getHauteur(),100,ovalHeight);
        	
        	Point[] p = this.etat.parcours.getParcours();
        	int i=1;
        	while(i < p.length) {
        		g.drawLine(p[i-1].x, p[i-1].y, p[i].x, p[i].y);
        		i++;
        	}
        	
        }
}
