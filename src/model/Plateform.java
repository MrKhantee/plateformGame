package model;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import multiplayer.InputModel;

public class Plateform extends Objet{

	Color color;
	
	public Plateform(float x, float y, float sizeX, float sizeY){
		this.collisionBox = new Rectangle(x,y,sizeX,sizeY);
		this.setXY(new Point(x, y));
		this.color = Color.white;
		this.v = new Point(0,0);
	}
	
	public void setXY(Point p){
		this.p = p;
		this.collisionBox.setX(p.x);
		this.collisionBox.setY(p.y);
	}
	
	public void draw(Graphics g){
		g.setColor(this.color);
		g.fill(this.collisionBox);
	}


	@Override
	public void updateSpec(InputModel im) {
		// TODO Auto-generated method stub
		
	}

}
