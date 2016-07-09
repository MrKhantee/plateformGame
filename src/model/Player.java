package model;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import multiplayer.InputModel;

public class Player extends Objet{
	
	
	public float radius;
	
	
	public Player(float radius,Point p, float dt){
		this.collisionBox = new Circle(p.x,p.y,radius);
		this.dt = dt;
		this.radius = radius;
		this.setXY(p);
		this.v = new Point(0,0);
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
		g.fillOval(this.p.x,this.p.y,this.radius,this.radius);
		
	}
	

}
