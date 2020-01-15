package flappyBird;

import java.awt.Dimension;

import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Affichage extends JPanel {

        /* Constantes */
        public static final int LARG = 600;
        public static final int HAUT = 400;
        public static final int ovalHeight = 70;
        /* Attributs */
        public Model etat;

        
        /** Constructeur */
        public Affichage(Model mod) {
                this.setPreferredSize(new Dimension(LARG, HAUT));
                this.etat = mod;
        }
        
        public void jump() {
        	this.etat.jump();
        }

        @Override
        public void paint(Graphics g) {
        	//nettoie le JFrame puis affiche l'ovale au niveau de la hauteur définie par le Model etat
        	g.clearRect(0, 0, LARG, HAUT);
        	System.out.println(this.etat.getHauteur());
        	g.drawOval(10,this.etat.getHauteur(),100,ovalHeight);
        }
}
