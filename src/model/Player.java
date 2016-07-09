package model;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
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
		Point acc = new Point(0f,Data.G);
		if(im.isKeyDown(Input.KEY_D)){
			acc = Point.add(acc, new Point(Data.ACC,0));
		}
		if(im.isKeyDown(Input.KEY_Z) && this.contact){
			this.v = Point.add(this.v, new Point(0f,-Data.speedJump));
		}
		if(im.isKeyDown(Input.KEY_Q)){
			acc = Point.add(acc, new Point(-Data.ACC,0));
		}
		if(im.isKeyDown(Input.KEY_S)){
			acc = Point.add(acc, new Point(0,Data.ACC));
		}
		this.setV(acc);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.orange);
		g.setAntiAlias(true);
		g.fillOval((p.x-0.5f*this.radius)*Data.ratioSpace,
		(p.y-0.5f*this.radius)*Data.ratioSpace,
		this.radius*Data.ratioSpace,this.radius*Data.ratioSpace);

		g.setAntiAlias(false);


	}
	

}
