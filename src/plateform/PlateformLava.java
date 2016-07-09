package plateform;

import java.util.Vector;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import model.Data;
import model.Point;

public class PlateformLava extends Plateform{
	
	public PlateformLava(float x, float y, float sizeX, float sizeY) {
		super(x, y, sizeX, sizeY);
		this.coefFrottement = Data.Flava;
		lumieres = new Vector<Point>();
		this.color = Color.red;
	}
}
