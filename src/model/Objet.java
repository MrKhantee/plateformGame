package model;

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
		Point acc = new Point(0,0);
		this.setV(acc);
		this.setPos();
	}
	

	
}
