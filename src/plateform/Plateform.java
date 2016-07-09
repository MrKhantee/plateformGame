package plateform;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import model.Data;
import model.InputModel;
import model.Objet;
import model.Point;

public class Plateform extends Objet {

	/**
	 * 
	 */
	

	Color color;

	public float coefFrottement = Data.Fplateforme;
	
	float sizeX, sizeY;
	
	public Plateform(float x, float y, float sizeX, float sizeY){
		this.collisionBox = new Rectangle(x,y,sizeX,sizeY);
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.setXY(new Point(x, y));
		this.color = Color.green;
		this.v = new Point(0,0);
	}
	
	public void setXY(Point p){
		this.p = p;
		this.collisionBox.setX(p.x);
		this.collisionBox.setY(p.y);
	}
	
	public void draw(Graphics g){
		g.setColor(this.color);
		g.fillRect(p.x*Data.ratioSpace, p.y*Data.ratioSpace, sizeX*Data.ratioSpace, sizeY*Data.ratioSpace);
	}


	@Override
	public void updateSpec(InputModel im) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void applyFrottement() {
		// TODO Auto-generated method stub
		
	}

}
