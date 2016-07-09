package model;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame{
	
	
	public static Game g;
	public int resX,resY;
	public AppGameContainer app;
	public Game(String title) {
		super(title);
		// TODO Auto-generated constructor stub
		Game.g = this;
	}

	public Game(int resolutionX, int resolutionY) {
		// TODO Auto-generated constructor stub
		super("Vicier");
		Game.g = this;
		this.resX = resolutionX;
		this.resY = resolutionY;
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		// Deplacement
	}
	
}