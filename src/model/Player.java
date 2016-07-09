package model;

import java.util.Vector;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;

import plateform.PlateformLava;

public class Player extends Objet {


	/**
	 * 
	 */
	

	public float radius;
	
	public Color color;
	
	public int timeoutGotHit = 0;
	
	public boolean directionRegard = true;
	

	public boolean isMoving;
	public Weapon weapon;
	public Vector<Integer> indexPlateforme = new Vector<Integer>();
	public Vector<Integer> orientationContact = new Vector<Integer>();



	public Player(float radius,Point p, int idx){
		this.collisionBox = new Circle(p.x-radius/2f,p.y-radius/2f,radius);
		this.radius = radius;
		this.setXY(p);
		this.v = new Point(0,0);
		this.lifepoints = Data.maxLifepoints;
		this.idPlayer = idx;
		if(idx==1){
			this.color = Color.orange;
		} else {
			this.color = Color.cyan;
		}
		this.weapon = new Weapon(idx,p);	
	}

	public void updateRadius(float r){
		this.radius = r;
		((Circle)this.collisionBox).setRadius(r);
	}

	@Override
	public void updateSpec(InputModel im) {
		isMoving = false;
		boolean contact = this.indexPlateforme.size()>0;
		Point acc = new Point(0f,Data.G);
		if(im.isKeyDown(Input.KEY_D)  ){
//			isMoving = true;
			acc = Point.add(acc, new Point(contact ? Data.ACCContact : Data.ratioVertical*Data.ACCLibre ,0));
		}
		if(im.isKeyPressed(Input.KEY_SPACE) && this.orientationContact.contains(4)){
			this.v = Point.add(this.v, new Point(0f,-Data.speedJump));
		}
		if(im.isKeyDown(Input.KEY_Q) ){
//			isMoving = true;
			acc = Point.add(acc, new Point(contact ? -Data.ACCContact : -Data.ratioVertical*Data.ACCLibre,0));
		}
		if(im.isKeyDown(Input.KEY_S) ){
			acc = Point.add(acc, new Point(0,contact ? Data.ACCContact : Data.ACCLibre));
		}
		this.setV(acc);
		this.directionRegard = v.x>0;
		this.weapon.v=  v.copy();
		this.weapon.update(im);
		weapon.setXY(p);
		
	}

	@Override
	public void draw(Graphics g) {
		if(timeoutGotHit>0){
			g.setColor(Color.red);	
			timeoutGotHit--;
		} else {
			g.setColor(color);			
		}
		g.setAntiAlias(true);

		g.fillOval((p.x-this.radius)*Data.ratioSpace,
				(p.y-this.radius)*Data.ratioSpace,
				2*this.radius*Data.ratioSpace,2*this.radius*Data.ratioSpace);			
		g.setAntiAlias(false);
		// Dessine ses yeux
		g.setColor(Color.black);
		g.fillOval((p.x)*Data.ratioSpace+(this.directionRegard ? 1f :-1f)*this.radius*0.2f-6, 
				(p.y - this.radius*0.5f)*Data.ratioSpace,
				12f,12f);
		g.fillOval((p.x)*Data.ratioSpace+(this.directionRegard ? 1f :-1f)*this.radius*0.65f-6, 
				(p.y - this.radius*0.5f)*Data.ratioSpace,
				12f,12f);
		// Dessine sa bouche
		float ratioLP = this.lifepoints/Data.maxLifepoints;
		g.setLineWidth(5f);
		g.drawArc((p.x-0.5f*radius-(this.directionRegard ? -1f :1f)*0.4f*radius)*Data.ratioSpace, (p.y-0.5f*radius)*Data.ratioSpace, (radius)*Data.ratioSpace, (radius)*Data.ratioSpace, 80-ratioLP*50, 100+ratioLP*50);
		// Dessine son arme
		weapon.draw(g);
		
	}

	@Override
	public void applyFrottement() {
		float coefFrottement = Data.Flibre;
		if(!isMoving){
			for(int i =0; i<this.indexPlateforme.size(); i++){
				if(orientationContact.get(i)==4){
					coefFrottement*=Game.g.plateau.plateforms.get(indexPlateforme.get(i)).coefFrottement;
				}
				if(Game.g.plateau.plateforms.get(indexPlateforme.get(i)) instanceof PlateformLava){
					this.lifepoints -= Data.damageLava;
					timeoutGotHit = 10;
				}
			}
		}
		this.v = Point.multiply(v, coefFrottement);
	}


}
