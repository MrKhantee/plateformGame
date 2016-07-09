package plateform;

import org.newdawn.slick.Color;

import model.Data;

public class PlateformTrampoline extends Plateform{

	/**
	 * 
	 */

	public PlateformTrampoline(float x, float y, float sizeX, float sizeY) {
		super(x, y, sizeX, sizeY);
		this.coefFrottement = Data.Fplateforme;
		this.color = Color.blue;
	}

}
