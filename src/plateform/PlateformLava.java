package plateform;

import java.util.Vector;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import model.Data;
import model.Point;

public class PlateformLava extends Plateform{
	
	private transient Vector<Point> lumieres;
	private transient int timeToGo = 0;
	
	public PlateformLava(float x, float y, float sizeX, float sizeY) {
		super(x, y, sizeX, sizeY);
		this.coefFrottement = Data.Flava;
		lumieres = new Vector<Point>();
		this.color = Color.red;
	}
	
	public void draw(Graphics g){
		if(timeToGo==0){
			timeToGo=30;
			lumieres.clear();
			for(int i=0; i<1000; i++){
				lumieres.add(new Point((float)(p.x*Data.ratioSpace+Math.random()*collisionBox.getWidth()*Data.ratioSpace), 
					(float)(p.y*Data.ratioSpace+Math.random()*collisionBox.getHeight()*Data.ratioSpace)));
			}
		}
		g.setColor(this.color);
		g.fillRect(p.x*Data.ratioSpace, p.y*Data.ratioSpace, sizeX*Data.ratioSpace, sizeY*Data.ratioSpace);
		g.setColor(Color.orange);
		timeToGo--;
		for(Point p : lumieres){
			g.fillRect(p.x, p.y,4f, 4f);
		}
	}
}
