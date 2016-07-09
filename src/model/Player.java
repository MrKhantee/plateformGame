package model;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import multiplayer.InputModel;

public class Player extends Objet{
	
	
	public float radius;
	
	
	public Player(float radius,Point p, float dt){
		this.dt = dt;
		this.radius = radius;
		this.setXY(p);
		this.setV(new Point(0,0));
		this.collisionBox = new Circle(p.x,p.y,radius);
	}
	
	public void updateRadius(float r){
		this.radius = r;
		((Circle)this.collisionBox).setRadius(r);
	}
	
	@Override
	public void updateSpec(InputModel im) {
		// Que faire spécifiquement avec le joueur ? 
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.orange);
		g.drawOval((p.x-0.5f*this.radius)*Data.ratioSpace,
				(p.y-0.5f*this.radius)*Data.ratioSpace,
				this.radius*Data.ratioSpace,this.radius*Data.ratioSpace);
	}
	

}
