package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import control.Oiseau;
import model.Etat;

public class VueOiseau implements ImageObserver{
	public static String Path= "bird/";
	
	private ArrayList<Oiseau> list;
	
	public VueOiseau(Etat etat) {
		this.list = new ArrayList<Oiseau>();
		Oiseau oiseau = new Oiseau(etat);
		(new Thread(oiseau)).start();
		this.list.add(oiseau);
	}
	
	void dessiner(Graphics g) {
		for(int i = 0; i < this.list.size(); i++){
			Oiseau oiseau = this.list.get(i);
			
			if(!oiseau.isRunning()) {
				this.list.remove(oiseau);
			}
			
			String str = Path+oiseau.getEtat()+".png";
			
			ImageIcon icon = new ImageIcon(str);
			
			Image image = icon.getImage();
			
			g.drawImage(image, oiseau.getPosition(), oiseau.getHauteur(), this);
			
			System.out.println("p = "+oiseau.getPosition()+" h = "+oiseau.getHauteur());
		}
	}

	public void finiOiseaux() {
		for(Oiseau oiseau : this.list) {
			oiseau.terminate();
		}
	}


	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
