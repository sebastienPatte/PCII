package flappyBird;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Control extends MouseAdapter{
	private Affichage a;
	private Model m;
	
	public Control(Affichage a, Model m){
		this.a = a;
		this.m = m;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("click");
		this.m.jump();
		this.a.repaint();
	}


}

