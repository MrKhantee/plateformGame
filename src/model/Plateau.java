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
