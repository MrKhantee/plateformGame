package model;

import main.Main;

public class Data {

	public static float sizeXPlateau = 1920;
	public static float sizeYPlateau = 1080;
	public static float ratioSpace;
	public static float G = 39.8f;


	public static float maxLifepoints = 50f;

	public static float damageBullet = 4f;
	public static float damageLava = 0.5f;

	public static float ACCContact = 65f;
	public static float ACCLibre = 40f;
	public static float RADIUS_PLAYER  = 40f;
	public static float DT = 10f/Main.framerate;
	
	// Frottements
	public static float Flibre = 0.99f;
	public static float Fplateforme = 0.80f;
	public static float Fglace = 0.95f;
	public static float Flava = 0.5f;
	
	public static float speedJump = 220f;
	public static float ratioVertical = 0.6f;
	


	
	// Weapon
	public static float ACCBullet = 70f;
	public static float Fbullet = 0.99f;
	public static float bulletRadius = 3f;
	public static float weaponRadius = 3f;
	public static float chargeTime = 0.5f;
	
}
