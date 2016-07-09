package model;

import java.util.Vector;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public  class Weapon extends Objet {

	/**
	 * 
	 */
	public Vector<Bullet> bullets;

	public float state;
	
	public float radius;

	public Weapon(int idx, Point p){
		this.radius= Data.weaponRadius;
		this.collisionBox = new Circle(p.x-radius/2f,p.y-radius/2f,radius);	
		bullets = new Vector<Bullet>();
		idPlayer = idx;
		this.v = new Point(0,0);
		this.setXY(p);
	}
	

	public void shot(Point direction){
		System.out.println(direction);
		if(direction.norm()>0){
			bullets.add(new Bullet(p,direction,v,idPlayer));
		}
	}
	
	@Override
	public void updateSpec(InputModel im) {
		state+=Data.DT;
		if(im.isPressedLeftClick && state>Data.chargeTime){
			shot(Point.sub(im.mouse, p));
		}
		// Update all the bullets
		Vector<Bullet> toRemove = new Vector<Bullet>();
		for(Bullet b : bullets){
			if(b.lifepoints<0f){
				toRemove.addElement(b);
			}else{
				b.update(im);
			}
		}
		bullets.removeAll(toRemove);
	}
	
	public void draw(Graphics g){
		// Draw all bullets
		for(Bullet b : bullets){
			b.draw(g);
		}

	}


	@Override
	public void applyFrottement() {
		// TODO Auto-generated method stub
		
	}
	
}
