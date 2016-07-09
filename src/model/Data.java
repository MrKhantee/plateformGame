package model;

import main.Main;

public class Data {

	public static float sizeXPlateau = 1920;
	public static float sizeYPlateau = 1080;
	
	public static float ratioSpace = Game.g.resX/sizeXPlateau;
	
	public static float G = 9.8f;
	public static float ACC = 10f;
	public static float RADIUS_PLAYER  = 20f;
	public static float DT = 10f/Main.framerate;
}
