package model;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public class Bullet extends Objet {
	
	
	/**
	 * 
	 */
	
	Point direction;
	float radius;
	
	
	public Bullet(Point origin, Point direction, int idx){
		idPlayer = idx;
		this.collisionBox = new Circle(origin.x-radius/2f,origin.y-radius/2f,radius);
		this.direction = Point.divide(direction, direction.norm());
		radius = Data.bulletRadius;
		this.v = new Point(0,0);
		setXY(origin);
		
	}
	@Override
	public void updateSpec(InputModel im) {
		// Put acceleration toward direction
		Point acc = new Point(0f,0.1f*Data.G);
		acc = Point.add(acc, Point.multiply(direction,Data.ACCBullet ));
		setV(acc);		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		g.fillOval((p.x-this.radius)*Data.ratioSpace,
				(p.y-this.radius)*Data.ratioSpace,
				2*this.radius*Data.ratioSpace,2*this.radius*Data.ratioSpace);	
	}
	@Override
	public void applyFrottement() {
		// TODO Auto-generated method stub
		this.v = Point.multiply(v, Data.Fbullet);
	}



	
	
}
