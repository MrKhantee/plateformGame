package model;

import main.Main;

public class Data {

	public static float sizeXPlateau = 1920;
	public static float sizeYPlateau = 1080;
	public static float ratioSpace;
	public static float G = 39.8f;


	public static float maxLifepoints = 50f;

	public static float damageBullet = 11f;
	public static float damageLava = 0.5f;

	public static float ACCContact = 65f;
	public static float ACCLibre = 30f;
	public static float RADIUS_PLAYER  = 40f;
	public static float DT = 10f/Main.framerate;
	
	// Frottements
	public static float Flibre = 0.99f;
	public static float Fplateforme = 0.85f;
	public static float Fglace = 0.95f;
	public static float Flava = 0.4f;
	
	public static float speedJump = 220f;
	public static float ratioVertical = 0.6f;
	


	
	// Weapon
	public static float speedBullet = 300f;
	public static float ACCBullet = 80f;
	public static float Fbullet = 0.99f;
	public static float bulletRadius = 6f;
	public static float weaponRadius = 3f;
	public static float chargeTime = 10f;
	public static float chargeTimeBonus = 3f;
	
	// Bonus
	public static float lengthBonus = 100f;
	public static float lengthBonusPlayer = 100f;
	public static float bonusFrottement = 1.005f;
	public static float timeBonus = 50f;
	
}
