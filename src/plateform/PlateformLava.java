package plateform;

import org.newdawn.slick.Color;

import model.Data;

public class PlateformLava extends Plateform{
	
	
	public PlateformLava(float x, float y, float sizeX, float sizeY) {
		super(x, y, sizeX, sizeY);
		this.coefFrottement = Data.Flava;
		this.color = Color.red;
	}
}
