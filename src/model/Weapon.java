package model;

import java.util.Vector;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public  class Weapon extends Objet {

	/**
	 * 
	 */
	public Vector<Bullet> bullets;

	public float state;
	
	public float radius;

	public Weapon(int idx, Point p){
		this.radius= Data.weaponRadius;
		this.collisionBox = new Circle(p.x-radius/2f,p.y-radius/2f,radius);	
		bullets = new Vector<Bullet>();
		idPlayer = idx;
		this.v = new Point(0,0);
		this.setXY(p);
	}
	

	public void shot(Point direction){
		if(direction.norm()>0){
			bullets.add(new Bullet(p,direction,idPlayer));
		}
		// Play sound
		Game.g.plateau.soundsToPlay.addElement(GameSound.soundToId(GameSound.shotgun));
	}
	
	@Override
	public void updateSpec(InputModel im) {
		
		state=Math.min(state+Data.DT, Data.chargeTime);
		boolean flagCadenceTir = false;
		boolean flagTripleShot = false;
		for(Bonus bns :Game.g.plateau.players.get(idPlayer-1).currentBonus){
			flagCadenceTir = flagCadenceTir || bns.type==Bonus.TypeBonus.CADENCETIR;
			flagTripleShot = flagTripleShot || bns.type==Bonus.TypeBonus.TRIPLESHOT;
		}
		float seuil = flagCadenceTir ? Data.chargeTimeBonus : Data.chargeTime;
		if(im.isPressedLeftClick && state>=seuil){
			shot(Point.sub(im.mouse, p));
			if(flagTripleShot){
				shot(Point.add(new Point(0,100), Point.sub(im.mouse, p)));
				shot(Point.add(new Point(0,-100), Point.sub(im.mouse, p)));
			}
			state= 0f;
		}
		// Update all the bullets
		Vector<Bullet> toRemove = new Vector<Bullet>();
		for(Bullet b : bullets){
			if(b.lifepoints<0f){
				toRemove.addElement(b);
			}else{
				b.update(im);
			}
		}
		bullets.removeAll(toRemove);
	}
	
	public void draw(Graphics g){
		// Draw all bullets
		for(Bullet b : bullets){
			b.draw(g);
		}
		// Create tempo
		if(this.state<Data.chargeTime && this.idPlayer==Game.currentPlayer){
			g.setColor(Color.blue);
			g.fillArc((p.x-15)*Data.ratioSpace, (p.y-1.5f*Data.RADIUS_PLAYER-20)*Data.ratioSpace, 30*Data.ratioSpace, 30*Data.ratioSpace, -90f, -90f+360f*this.state/Data.chargeTime);
		}

	}


	@Override
	public void applyFrottement() {
		// TODO Auto-generated method stub
		
	}
	
}
