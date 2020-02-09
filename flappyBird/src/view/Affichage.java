package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import model.Etat;


/**
 * la classe Affichage est le JPanel dans lequel on va afficher les éléments du jeu (Ovale, courbe, oiseaux, ...) 
 */
public class Affichage extends JPanel {
		
		private static final long serialVersionUID = 1L;
		/**
		 * largeur des traits
		 */
		public static final BasicStroke largTraits = new BasicStroke(2.0f);
		
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

		private VueOiseau vueOiseau;

        
        /** 
         * Constructeur:
         * @param etat de type {@link Etat}
         * */
        public Affichage(Etat etat, VueOiseau vueOiseau) {
                this.setPreferredSize(new Dimension(LARG, HAUT));
                this.etat = etat;
                this.vueOiseau = vueOiseau;
        }
        
        /** affiche l'ovale au niveau de la hauteur définie par le Modèle {@link Etat}*/
        private void afficheOvale(Graphics g) {
        	g.setColor(Color.red);
        	g.drawOval(ovalDec-ovalWidth/2,this.etat.getHauteur(),ovalWidth,ovalHeight);
        	g.setColor(Color.black);
        }
        
        /**
         * affiche les courbes actuelles
         * @param g2d
         */
        private void afficheCourbe(Graphics2D g2d) {
        	
        	ArrayList<QuadCurve2D> curveList = this.etat.getParcours().getCourbe();
        	
        	for(QuadCurve2D courbe : curveList) {
        		g2d.draw(courbe);
        	}
        }
        
        /** affiche le score actuel, c'est à dire la position définie par la classe {@link Parcours}*/
        private void afficheScore(Graphics g, int score) {
        	Font newFont = new Font("TimesRoman", Font.PLAIN, 20);
    		g.setFont(newFont);
        	String strScore ="Score : "+score;
        	FontMetrics fm = getFontMetrics(newFont);
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
        	
            
        	Graphics2D g2d = (Graphics2D) g;
        	g2d.setStroke(largTraits);
        	
        	//nettoie le JFrame 
        	g.clearRect(0, 0, LARG, HAUT);
        	
        	//test perdu
        	boolean perdu =etat.testPerdu();
        	//calcul du score
    		int score = this.etat.getParcours().getPosition() - etat.getScore0();
    		if(perdu) {
        		//si on a perdu on stoppe tout les threads et on affiche game over avec le score
    			etat.stopThreads();
        		vueOiseau.finiOiseaux();
        		//on retire l'unique MouseListener "Click" de l'affichage si il y est encore 
        		if(this.getMouseListeners().length > 0) {
        			this.removeMouseListener(this.getMouseListeners()[0]);
        		}
        		Font newFont = new Font("TimesRoman", Font.PLAIN, 50);
        		g.setFont(newFont);
        		printMidStr("GAME OVER", HAUT/2, newFont, g);
        		printMidStr("Score:"+score, HAUT/2+50, newFont, g);
        	}else {
        		//si on a encore pas perdu on affiche le score en haut à droite
        		this.afficheScore(g,score);
        	}
    		
    		//dans tout les cas on affiche les oiseaux, l'ovale et la courbe
        	this.vueOiseau.dessiner(g);
        	this.afficheOvale(g);
        	this.afficheCourbe(g2d);
        	
        	
        }
}
