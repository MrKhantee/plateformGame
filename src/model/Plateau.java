package model;

import java.util.Vector;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;

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
	public Vector<Bonus> bonus;
	public boolean victory;
	public int winner;

	//Bonus
	public float timerBonus = Data.timeBonus;
	public Vector<Integer> soundsToPlay = new Vector<Integer>();

	public Plateau(){
		this.plateforms = new Vector<Plateform>();
		this.players = new Vector<Player>();
		this.bonus = new Vector<Bonus>();
		this.players.add(new Player(Data.RADIUS_PLAYER, new Point(1820f,50f),1));
		this.players.add(new Player(Data.RADIUS_PLAYER, new Point(100f,50f),2));
		// Bords de la map
		this.plateforms.addElement(new PlateformLava(0,Data.sizeYPlateau-10,Data.sizeXPlateau,40));
		this.plateforms.addElement(new Plateform(0,-10,Data.sizeXPlateau,60));
		this.plateforms.addElement(new Plateform(-10,0,10,Data.sizeYPlateau));
		this.plateforms.addElement(new Plateform(Data.sizeXPlateau,0,10,Data.sizeYPlateau));
		// Autres 
		this.plateforms.addElement(new PlateformGlace(350,Data.sizeYPlateau-180,200,10));
		this.plateforms.addElement(new PlateformTrampoline(1750,720,70,10));
		this.plateforms.addElement(new PlateformTrampoline(50,620,70,10));
		this.plateforms.addElement(new Plateform(1200,420,200,10));
		this.plateforms.addElement(new Plateform(800,620,200,10));
		this.plateforms.addElement(new Plateform(450,1050,420,10));
		this.plateforms.addElement(new Plateform(1150,1050,220,10));
		this.plateforms.addElement(new Plateform(1350,820,200,10));
		this.plateforms.addElement(new Plateform(450,150,10,280));
		this.plateforms.addElement(new PlateformGlace(450,430,250,10));
		this.plateforms.addElement(new PlateformGlace(250,320,200,10));

	}

	public void update(Vector<InputModel> ims){
		// Apparition des bonus
		this.timerBonus-=Data.DT;
		if(this.timerBonus<0){
			this.bonus.addElement(new Bonus(Bonus.TypeBonus.getRandomBonus(),new Point((float)(Data.sizeXPlateau*Math.random()),(float)(50+(Data.sizeYPlateau-50)*Math.random()))));
			this.timerBonus = Data.timeBonus;
		}
		for(Plateform plt : this.plateforms){
			plt.update(null);
		}
		Vector<Bonus> toRemove = new Vector<Bonus>();
		for(Bonus bns : this.bonus){
			bns.update(null);
			if(bns.remainingTime<0){
				toRemove.add(bns);
			}
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

				Line l = new Line(b.pold.x, b.pold.y,b.p.x,b.p.y);
				for(Player p : this.players){
					if(p!=ply && p.collisionBox.intersects(b.collisionBox)){
						this.handleCollision(p, b);
					}
					if(p!=ply && p.collisionBox.intersects(l)){
						this.handleCollision(p, b);	
					}
				}

				for(Plateform plt : this.plateforms){
					if(plt.collisionBox.intersects(b.collisionBox)){
						this.handleCollision(plt, b);

					}
					if(plt.collisionBox.intersects(l)){
						this.handleCollision(plt, b);	
					}
				}
			}
			for(Bonus bns : this.bonus){
				if(bns.collisionBox.intersects(ply.collisionBox)){
					toRemove.add(bns);
					if(bns.type == Bonus.TypeBonus.HEALTH){
						this.soundsToPlay.addElement(GameSound.soundToId(GameSound.bonus));
						ply.lifepoints=Math.min(ply.lifepoints+Data.bonusLifePoint,Data.maxLifepoints);
					} else {
						ply.currentBonus.add(bns);
						bns.setXY(new Point(400,20));
						bns.remainingTime = Data.lengthBonusPlayer;
					}
				}
			}
		}
		this.bonus.removeAll(toRemove);
		// Condition of victory
		for(Player p : players){
			if(p.lifepoints<0f && winner==0){
				victory=true;
				this.soundsToPlay.addElement(GameSound.soundToId(GameSound.death));
				winner = 3-p.idPlayer;
				break;
			}
		}
	}

	public void draw(Graphics g){

		if(victory){
			if(winner==Game.currentPlayer){
				g.setColor(Color.white);
				g.drawString("Tu as vicier ton adversaire ", Game.resX/2, Game.resY/2);
			}else{
				g.setColor(Color.white);
				g.drawString("Tu es vicier ...", Game.resX/2, Game.resY/2);
			}
			return;
		}

		for(Plateform plt : this.plateforms){
			plt.draw(g);
		}
		for(Player ply : this.players){
			ply.draw(g);
		}
		for(Bonus bns : this.bonus){
			bns.draw(g);
		}

		// Draw lifepoints
		float sizeLifeX = 150f;
		float sizeLifeY = 15f;
		g.setColor(Color.black);
		g.fillRect(0, 0, Game.resX, 50);
		if(players.size()==2){
			int idx = 1;
			for(Player p : players ){
				g.setColor(Color.white);
				g.drawString("Player "+idx, Game.resX/2 + (Game.resX/2-100)*((idx-1)*2-1)-45, 7f);
				g.setColor(Color.red);
				g.fillRect(Game.resX/2 + (Game.resX/2-250)*((idx-1)*2-1)-sizeLifeX/2,10f,sizeLifeX,sizeLifeY);
				g.setColor(p.color);
				g.fillRect(Game.resX/2 + (Game.resX/2-250)*((idx-1)*2-1)-sizeLifeX/2,10f,sizeLifeX*p.lifepoints/Data.maxLifepoints,sizeLifeY);
				idx++;
				int i =0;
				for(Bonus bns : p.currentBonus){
					bns.p.x = Game.resX/2 + (200-i*50)*((idx-1)*2-1);
					System.out.println(bns.p.x);
					bns.draw(g);
					i+=1;
				}
			}
		}
		g.setColor(Color.white);
		g.drawLine(0, 30f+sizeLifeY, Game.resX, 30f+sizeLifeY);
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
		if(b.lifepoints<0f){
			return;
		}
		p.lifepoints-= Data.damageBullet;
		b.lifepoints = -1f;
		p.timeoutGotHit = 10;
		this.soundsToPlay.addElement(GameSound.soundToId(GameSound.injury));
	}

	public void handleCollision(Plateform p , Bullet b){
		b.lifepoints = -1f;
	}

}
