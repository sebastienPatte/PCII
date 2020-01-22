package view;

import java.awt.Dimension;
import java.awt.FontMetrics;
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
        public static final int ovalHeight = 100;
        /** largeur de l'ovale*/
        public static final int ovalWidth = 30;
        
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
        	//nettoie le JFrame 
        	g.clearRect(0, 0, LARG, HAUT);
        	
        	//affiche l'ovale au niveau de la hauteur définie par le Model etat
        	g.drawOval(10,this.etat.getHauteur(),ovalWidth,ovalHeight);
        	
        	//TODO : sous-mécthodes pour affichage de l'ovale et de la ligne brisée
        	
        	Point[] p = this.etat.getParcours().getParcours();
        	
        	int i=1;
        	while(i < p.length) {
        		g.drawLine(p[i-1].x, p[i-1].y, p[i].x, p[i].y);
        		i++;
        	}
        	
        	//affichage score 
        	int score = this.etat.getParcours().getPosition();
        	String strScore ="Score : "+score;
        	FontMetrics fm = getFontMetrics(getFont());
        	int printedLength = fm.stringWidth(strScore) +10; // on ajoute 10 pour pas etre collé au bord
        	g.drawString(strScore, LARG-printedLength, 20);
        }
}
