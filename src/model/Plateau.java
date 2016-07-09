package model;

import java.util.Vector;

import org.newdawn.slick.Graphics;

import multiplayer.InputModel;

public class Plateau {
	
	public Vector<Plateform> plateforms;
	public Vector<Player> players;
	
	public Plateau(){
		this.plateforms = new Vector<Plateform>();
		this.players = new Vector<Player>();
		this.players.add(new Player(Data.RADIUS_PLAYER, new Point(0f,0f),Data.DT));
		this.plateforms.addElement(new Plateform(0,Game.g.resY-10,Game.g.resX,10));
		this.plateforms.addElement(new Plateform(350,Game.g.resY-400,300,10));
		this.plateforms.addElement(new Plateform(1200,Game.g.resY-700,250,10));
	}

	public void update(InputModel im){
		for(Plateform plt : this.plateforms){
			plt.update(im);
		}
		for(Player ply : this.players){
			ply.update(im);
		}
		
	}
	
	public void draw(Graphics g){
		for(Plateform plt : this.plateforms){
			plt.draw(g);
		}
		for(Player ply : this.players){
			ply.draw(g);
		}
	}
}
