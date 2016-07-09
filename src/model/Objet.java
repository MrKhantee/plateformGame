package model;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Shape;

import multiplayer.InputModel;

public abstract class Objet {
	
	// Physic
	public Point p;
	public Point v;
	public float dt;
	public Shape collisionBox;
	
	public void setXY(Point p){
		this.p = p;
		this.collisionBox.setCenterX(p.x);
		this.collisionBox.setCenterY(p.y);
	}
	
	
	public Point getV(Point a){
		return Point.divide(Point.add(v, a), dt);
	}
	
	public Point getPos(){
		return Point.divide(Point.add(p, v), dt);
	}

	
	public void setV(Point a){
		this.v =  Point.multiply(Point.add(v, a), dt);
	}
	
	
	public void setPos(){
		this.setV(Point.multiply(Point.add(p, v), dt));
	}
	
	public void update(InputModel im){
		// Update physic
		updatePhysic(im);
		//Update Spec
		updateSpec(im);
	}
	
	public abstract void updateSpec(InputModel im);
	public void updatePhysic(InputModel im){
		// Switch given the input
		Point acc = new Point(0,Data.G);
		if(im.isKeyDown(Input.KEY_D)){
			acc = Point.add(acc, new Point(Data.ACC,0));
		}
		if(im.isKeyDown(Input.KEY_Z)){
			acc = Point.add(acc, new Point(0,-Data.ACC));
		}
		if(im.isKeyDown(Input.KEY_Q)){
			acc = Point.add(acc, new Point(-Data.ACC,0));
		}
		if(im.isKeyDown(Input.KEY_S)){
			acc = Point.add(acc, new Point(0,Data.ACC));
		}
		
		
		this.setV(acc);
		this.setPos();
	}
	
	public abstract void draw(Graphics g);
	
}
