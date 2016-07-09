package model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import multiplayer.InputModel;

public class Game extends BasicGame{
	
	
	public static Game g;
	public int resX,resY;
	public AppGameContainer app;
	
	public Plateau plateau;
	
	public Color bgcolor = Color.black;
	
	public boolean host = true;
	
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
		this.plateau = new Plateau();
		String serializedObject = "";

		 // serialize the object
		 try {
		     ByteArrayOutputStream bo = new ByteArrayOutputStream();
		     ObjectOutputStream so = new ObjectOutputStream(bo);
		     so.writeObject(plateau);
		     so.flush();
		     serializedObject = bo.toString();
		 } catch (Exception e) {
		     System.out.println(e);
		 }

		 // deserialize the object
		 try {
		     byte b[] = serializedObject.getBytes(); 
		     ByteArrayInputStream bi = new ByteArrayInputStream(b);
		     ObjectInputStream si = new ObjectInputStream(bi);
		     this.plateau = (Plateau) si.readObject();
		 } catch (Exception e) {
		     System.out.println(e);
		 }
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setColor(bgcolor);
		g.fillRect(0, 0, resX, resY);
		this.plateau.draw(g);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		InputModel im = new InputModel(gc.getInput());
		if(host){
			Vector<InputModel> ims = new Vector<InputModel>();
			ims.add(im);
			ims.add(new InputModel());
			this.plateau.update(ims);			
		} else {
			
		}
	}
	
	public static float getPointToDraw(float f){
		return f*Game.g.resX/Data.sizeXPlateau;
	}
	
}