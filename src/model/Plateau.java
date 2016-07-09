package model;

import java.util.Vector;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import plateform.Plateform;
import plateform.PlateformGlace;
import plateform.PlateformLava;
import plateform.PlateformTrampoline;

public class Plateau implements java.io.Serializable{
	
	/**
	 * 
	 */
	
	public Vector<Plateform> plateforms;
	public Vector<Player> players;

	
	public Plateau(){
		this.plateforms = new Vector<Plateform>();
		this.players = new Vector<Player>();
		this.players.add(new Player(Data.RADIUS_PLAYER, new Point(500f,50f),1));
		this.players.add(new Player(Data.RADIUS_PLAYER, new Point(50f,50f),2));
		// Bords de la map
		this.plateforms.addElement(new PlateformLava(0,Data.sizeYPlateau,Data.sizeXPlateau,400));
		this.plateforms.addElement(new Plateform(0,-10,Data.sizeXPlateau,10));
		this.plateforms.addElement(new Plateform(-10,0,10,Data.sizeYPlateau));
		this.plateforms.addElement(new Plateform(Data.sizeXPlateau,0,10,Data.sizeYPlateau));
		// Autres 
		this.plateforms.addElement(new PlateformGlace(350,Data.sizeYPlateau-350,400,10));
		this.plateforms.addElement(new PlateformTrampoline(1750,800,100,10));
		this.plateforms.addElement(new PlateformTrampoline(50,600,100,10));
		this.plateforms.addElement(new Plateform(1200,Data.sizeYPlateau-700,400,10));
		this.plateforms.addElement(new Plateform(250,1030,1420,10));

	}

	public void update(Vector<InputModel> ims){
		for(Plateform plt : this.plateforms){
			plt.update(null);
		}
			
		for(int i=0; i<this.players.size(); i++){
			this.players.get(i).update(ims.get(i));
		}
		// Gerer les collisions entre players et plateforme
		int i = 0;
		for(Player ply : this.players){
			ply.indexPlateforme.clear();
			ply.orientationContact.clear();
			i=0;
			for(Plateform plt : this.plateforms){
				if(plt.collisionBox.intersects(ply.collisionBox)){
					this.handleCollision(ply, plt);
					ply.indexPlateforme.add(i);
				}
				i+=1;
			}
			for(Bullet b : ply.weapon.bullets){
				for(Player p : this.players){
					if(p!=ply && p.collisionBox.intersects(b.collisionBox)){
						this.handleCollision(p, b);
					}
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
		
		// Draw lifepoints
		float sizeLifeX = 200f;
		float sizeLifeY = 50f;
		if(players.size()==2){
			int idx = 0;
			for(Player p : players ){
				g.setColor(Color.white);
				g.drawString("Player "+idx, 10f+300f*idx, 50f);
				g.setColor(Color.red);
				g.fillRect(50f+(300f)*idx,10f,sizeLifeX,sizeLifeY);
				g.setColor(Color.green);
				g.fillRect(50f+300f*idx,10f,sizeLifeX*p.lifepoints/Data.maxLifepoints,sizeLifeY);
				idx++;
			}
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
		ply.v.x=0;
		break;
		case 2:	newY = plt.collisionBox.getMaxY()+ply.collisionBox.getBoundingCircleRadius();
		ply.v.y=0;
		break;
		case 3: newX = plt.collisionBox.getMinX()-ply.collisionBox.getBoundingCircleRadius();
		ply.v.x=0;
		break;
		case 4: newY = plt.collisionBox.getMinY()-ply.collisionBox.getBoundingCircleRadius();
		ply.v.y=(plt instanceof PlateformTrampoline ? -1.2f*Data.speedJump : 0);
		break;
		default:
		}
		ply.orientationContact.add(sector);
		ply.setXY(new Point(newX, newY));
	}
	
	public void handleCollision(Player p , Bullet b){
		p.lifepoints-= Data.damageBullet;
		b.lifepoints = -1f;
	}
	
	public void handleCollision(Plateform p , Bullet b){
		b.lifepoints = -1f;
	}
	
}
