package model;

import main.Main;

public class Data {

	public static float sizeXPlateau = 1920;
	public static float sizeYPlateau = 1080;
	public static float ratioSpace;
	public static float G = 39.8f;

	public static float ACCContact = 65f;
	public static float ACCLibre = 40f;
	
	public static float RADIUS_PLAYER  = 40f;
	
	public static float DT = 10f/Main.framerate;
	public static float Flibre = 0.99f;
	
	public static float Fplateforme = 0.80f;
	public static float Fglace = 0.95f;
	
	public static float speedJump = 220f;
	
	public static float ratioVertical = 0.6f;
}
