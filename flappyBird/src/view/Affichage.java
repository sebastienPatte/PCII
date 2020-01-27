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
        /** décalage de l'ovale par rapport au bord gauche de la fenêtre*/
        public static final int ovalDec = 10;
        
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
        /** affiche l'ovale au niveau de la hauteur définie par le Modèle {@link Etat}*/
        private void afficheOvale(Graphics g) {
        	g.drawOval(ovalDec,this.etat.getHauteur(),ovalWidth,ovalHeight);
        }
        
        /** affiche la ligne brisée à partir des points définis par la classe {@link Parcours}*/
        private void afficheLigne(Graphics g) {
        	Point[] p = this.etat.getParcours().getParcours();
        	
        	int i=1;
        	while(i < p.length) {
        		g.drawLine(p[i-1].x, p[i-1].y, p[i].x, p[i].y);
        		i++;
        	}
        }
        
        /** affiche le score actuel, c'est à dire la position définie par la classe {@link Parcours}*/
        private void afficheScore(Graphics g) {
        	int score = this.etat.getParcours().getPosition();
        	String strScore ="Score : "+score;
        	FontMetrics fm = getFontMetrics(getFont());
        	int printedLength = fm.stringWidth(strScore) +10; // on ajoute 10 pour pas etre collé au bord
        	g.drawString(strScore, LARG-printedLength, 20);
        }
        
        /**
         * nettoye l'affichage puis affiche l'ovale à l'endroit défini par l'{@link Etat}
         * */
        @Override
        public void paint(Graphics g) {
        	//nettoie le JFrame 
        	g.clearRect(0, 0, LARG, HAUT);
        	
        	this.afficheOvale(g);
        	this.afficheLigne(g);
        	this.afficheScore(g);
        	
        }
}
