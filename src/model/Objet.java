package model;

import org.newdawn.slick.geom.Shape;

public abstract class Objet {
	
	
	private Point pos;
	private Point v;
	
	public Shape collisionBox;
	
	
	public abstract void setXY(float x, float y);
	
//	public float getAcc(){
//		return 
//	}
	

	
}
