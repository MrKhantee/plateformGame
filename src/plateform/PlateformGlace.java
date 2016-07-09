package plateform;

import org.newdawn.slick.Color;

import model.Data;

public class PlateformGlace extends Plateform{

	/**
	 * 
	 */
	private static final long serialVersionUID = 807729861832742312L;

	public PlateformGlace(float x, float y, float sizeX, float sizeY) {
		super(x, y, sizeX, sizeY);
		this.coefFrottement = Data.Fglace;
		this.color = Color.white;
	}

}
