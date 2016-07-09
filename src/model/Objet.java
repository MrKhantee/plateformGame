package model;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

public abstract class Objet implements java.io.Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2296061632203406519L;
	// Physic
	public Point p;
	public Point v;
	public float dt;
	public Shape collisionBox;
	public boolean moving; // Tell is this object can move
	public boolean contact=false;
	
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
		if(contact){
			v = Point.multiply(v, Data.Fcontact);
		}else{
			Point.multiply(v, Data.Flibre);
		}
		
		v =  Point.add(v, Point.multiply(a,dt));
	}
	
	public void setPos(){
		this.setXY(Point.add(p, Point.multiply(v,dt)));
	}
	
	public void update(InputModel im){		
		//Update Spec
		updateSpec(im);
		setPos();
	}

	
	public abstract void updateSpec(InputModel im);	 // What to do with inputs
	public abstract void draw(Graphics g);
	
}
