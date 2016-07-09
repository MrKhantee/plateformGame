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
		this.players.add(new Player(Data.RADIUS_PLAYER, new Point(50f,50f),Data.DT));
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
		// Gerer les collisions entre players et plateforme
		for(Player ply : this.players){
			for(Plateform plt : this.plateforms){
				if(plt.collisionBox.intersects(ply.collisionBox)){
					this.handleCollision(ply, plt);
				}
			}
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
	
	public void handleCollision(Player ply, Plateform plt){
		/*On consid�re pour l'instant que nos natural objets sont carr�s
		 * il faut dans un premier temps d�terminer de quel c�t� �jecter l'objet
		 * pour cela on d�limite 4 secteurs:
		 * 		1: � droite
		 * 		2: en haut
		 * 		3: � gauche
		 * 		4: en bas
		 *	puis on �jecte le point au bord du c�t� correspondant via projection
		 */
		float oX, oY;
		oX = plt.collisionBox.getCenterX();
		oY = plt.collisionBox.getCenterY();
		float x, y;
		x = ply.p.x;
		y = ply.p.y;
		int sector = 0;
		if(x-oX>0f){
			if(y-oY>Math.abs(x-oX)*plt.collisionBox.getHeight()/plt.collisionBox.getWidth()){
				sector = 2;
			} else if(y-oY<-Math.abs(x-oX)*plt.collisionBox.getHeight()/plt.collisionBox.getWidth()){
				sector = 4;
			} else {
				sector = 1;
			}
		} else {
			if(y-oY>Math.abs(x-oX)*plt.collisionBox.getHeight()/plt.collisionBox.getWidth()){
				sector = 2;
			} else if(y-oY<-Math.abs(x-oX)*plt.collisionBox.getHeight()/plt.collisionBox.getWidth()){
				sector = 4;
			} else {
				sector = 3;
			}
		}
		// Ejecting the point
		float newX = ply.p.x;
		float newY = ply.p.y;
		switch(sector){
		case 1: newX = plt.collisionBox.getMaxX()+ply.collisionBox.getBoundingCircleRadius();
		break;
		case 2:	newY = plt.collisionBox.getMaxY()+ply.collisionBox.getBoundingCircleRadius();
		break;
		case 3: newX = plt.collisionBox.getMinX()-ply.collisionBox.getBoundingCircleRadius();
		break;
		case 4: newY = plt.collisionBox.getMinY()-ply.collisionBox.getBoundingCircleRadius();
		break;
		default:
		}
		ply.setXY(new Point(newX, newY));
	}
}
