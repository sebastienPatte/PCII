package flappyBird;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		
		Model mod = new Model();
		Affichage aff = new Affichage(mod);
		Control ctrl = new Control(aff,mod);
		aff.addMouseListener(ctrl);
		
		JFrame fenetre = new JFrame("titre de la fenêtre");
		
		fenetre.add(aff);
		fenetre.pack();
		fenetre.setVisible(true);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}


