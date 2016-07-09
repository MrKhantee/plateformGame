package model;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Plateform extends Objet{

	Color color;
	
	public Plateform(float x, float y, float sizeX, float sizeY){
		this.setXY(x, y);
		this.collisionBox = new Rectangle(x,y,sizeX,sizeY);
		this.color = Color.white;
	}
	
	
	public void draw(Graphics g){
		g.setColor(this.color);
		g.fill(this.collisionBox);
	}

}
