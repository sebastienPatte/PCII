package view;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;
import model.Etat;


/**
 * la classe Affichage est le JPanel dans lequel on va afficher l'ovale 
 */
public class Affichage extends JPanel {
		
		private static final long serialVersionUID = 1L;

		
		/** largeur du JPanel*/
        public static final int LARG = 1000;
        /** hauteur du JPanel*/
        public static final int HAUT = 800;
        
        /** hauteur de l'ovale*/
        public static final int ovalHeight = 100;
        /** largeur de l'ovale*/
        public static final int ovalWidth = 30;
        /** décalage entre le centre de l'ovale et le bord gauche de la fenêtre*/
        public static final int ovalDec = 10+ovalWidth/2;
        
        /** attribut de type {@link Etat}*/
        public Etat etat;

        
        /** 
         * Constructeur:
         * @param etat de type {@link Etat}
         * */
        public Affichage(Etat etat) {
                this.setPreferredSize(new Dimension(LARG, HAUT));
                this.etat = etat;
        }
        
        /** affiche l'ovale au niveau de la hauteur définie par le Modèle {@link Etat}*/
        private void afficheOvale(Graphics g) {
        	g.drawOval(ovalDec-ovalWidth/2,this.etat.getHauteur(),ovalWidth,ovalHeight);
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
         * affiche une string au milieu de la fenetre
         */
        private void printMidStr(String str, int height, Font font, Graphics g) {
        	FontMetrics fm = getFontMetrics(font);
        	int width = LARG/2 - fm.stringWidth(str)/2;
        	g.drawString(str,width,height);
        }
        
        /**
         * <p>- nettoye l'affichage </p>
         * <p>- affiche l'ovale </p>
         * <p>- affiche la ligne brisée </p>
         * <p>- Si Game Over, stoppe les Threads et affiche un message avec le score.</p>
         * */
        @Override
        public void paint(Graphics g) {
        	
        	
        	//nettoie le JFrame 
        	g.clearRect(0, 0, LARG, HAUT);
        	if(etat.testPerdu()) {
        		etat.stopThreads();
        		Font newFont = new Font("TimesRoman", Font.PLAIN, 50);
        		g.setFont(newFont);
        		printMidStr("GAME OVER", HAUT/2, newFont, g);
        		printMidStr("Score:"+this.etat.getParcours().getPosition(), HAUT/2+50, newFont, g);
        	}else {
        		this.afficheScore(g);
        	}
        	
        	Graphics2D g2d = (Graphics2D) g;
        	g2d.setStroke(new BasicStroke(2.0f));
        	this.afficheOvale(g);
        	g2d.setStroke(new BasicStroke(1.0f));
        	this.afficheLigne(g);
        	
        	g.drawOval(ovalDec, (int) etat.yPos0, 2, 2);
        	
        }
}
