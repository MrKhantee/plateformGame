package model;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

public abstract class Objet implements java.io.Serializable{
	
	
	/**
	 * 
	 */
	
	// Physic
	public Point p;
	public Point v;
	int idPlayer;
	public transient Shape collisionBox;
	public boolean moving; // Tell is this object can move
	public float lifepoints;
	public void setXY(Point p){
		this.p = p;
		this.collisionBox.setCenterX(p.x);
		this.collisionBox.setCenterY(p.y);
	}
	
	
	public Point getV(Point a){
		return Point.divide(Point.add(v, a), Data.DT);
	}
	
	public Point getPos(){
		return Point.divide(Point.add(p, v), Data.DT);
	}

	
	public void setV(Point a){

		v =  Point.add(v, Point.multiply(a,Data.DT));
		this.applyFrottement();

	}
	
	public void setPos(){
		this.setXY(Point.add(p, Point.multiply(v,Data.DT)));
	}
	
	public void update(InputModel im){		
		//Update Spec
		updateSpec(im);
		setPos();
	}

	
	public abstract void updateSpec(InputModel im);	 // What to do with inputs
	public abstract void draw(Graphics g);
	public abstract void applyFrottement();
}
