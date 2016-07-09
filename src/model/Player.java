package model;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;

public class Player extends Objet implements java.io.Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4186225280918342257L;
	
	public float radius;
	
	
	public Player(float radius,Point p, float dt){
		this.collisionBox = new Circle(p.x-radius/2f,p.y-radius/2f,radius);
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
		
		Point acc = new Point(0f,Data.G);
		if(im.isKeyDown(Input.KEY_D)  ){
			acc = Point.add(acc, new Point(contact ? Data.ACCContact : Data.ratioVertical*Data.ACCLibre ,0));
		}
		if(im.isKeyPressed(Input.KEY_Z) && this.contact){
			this.v = Point.add(this.v, new Point(0f,-Data.speedJump));
		}
		if(im.isKeyDown(Input.KEY_Q) ){
			acc = Point.add(acc, new Point(contact ? -Data.ACCContact : -Data.ratioVertical*Data.ACCLibre,0));
		}
		if(im.isKeyDown(Input.KEY_S) ){
			acc = Point.add(acc, new Point(0,contact ? Data.ACCContact : Data.ACCLibre));
		}
		this.setV(acc);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.orange);
		g.setAntiAlias(true);
		g.fillOval((p.x-this.radius)*Data.ratioSpace,
		(p.y-this.radius)*Data.ratioSpace,
		2*this.radius*Data.ratioSpace,2*this.radius*Data.ratioSpace);
		g.setColor(Color.cyan);

		g.setAntiAlias(false);
		g.fill(collisionBox);


	}
	

}
