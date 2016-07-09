package plateform;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import model.Data;

public class PlateformLava extends Plateform{
	
	
	public PlateformLava(float x, float y, float sizeX, float sizeY) {
		super(x, y, sizeX, sizeY);
		this.coefFrottement = Data.Flava;
		this.color = Color.red;
	}
	
	public void draw(Graphics g){
		g.setColor(this.color);
		g.fillRect(p.x*Data.ratioSpace, p.y*Data.ratioSpace, sizeX*Data.ratioSpace, sizeY*Data.ratioSpace);
		g.setColor(Color.yellow);
	}
}
