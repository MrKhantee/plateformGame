package model;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public class Bonus extends Objet{

	TypeBonus type;
	
	float remainingTime = 0;
	
	public Bonus(TypeBonus type, Point p){
		this.type = type;
		this.p = p;
		this.v = new Point(0,0);
		this.collisionBox = new Circle(p.x, p.y, 25);
		this.remainingTime = Data.lengthBonus;
	}
	
	public enum TypeBonus{
		CADENCETIR,
		TRIPLESHOT,
		HIGHERSPEED,
		HEALTH;
		
		public static TypeBonus getRandomBonus(){
			return TypeBonus.values()[(int)(TypeBonus.values().length*Math.random())];
		}
	}

	@Override
	public void updateSpec(InputModel im) {
		this.remainingTime -= Data.DT;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.darkGray);		
		g.fillArc((p.x-25)*Data.ratioSpace, (p.y-25)*Data.ratioSpace, (50)*Data.ratioSpace, (50)*Data.ratioSpace, -90, -90+360*this.remainingTime/Data.lengthBonus);
		g.setColor(Color.white);
		g.fillOval((p.x-20)*Data.ratioSpace, (p.y-20)*Data.ratioSpace, (40)*Data.ratioSpace, (40)*Data.ratioSpace);
		String s = "";
		Color c = Color.black;
		switch(type){
		case CADENCETIR : s = "+++"; c = Color.red; break;
		case TRIPLESHOT : s = "3 x"; c = Color.green; break;
		case HIGHERSPEED : s = ">>>"; c = Color.pink; break;
		case HEALTH : s = "LP"; c = Color.cyan; break;
		default: break;
		}
		g.setColor(c);
		g.drawString(s, p.x*Data.ratioSpace-g.getFont().getWidth(s)*0.5f, p.y*Data.ratioSpace-g.getFont().getHeight(s)/2);
	}

	@Override
	public void applyFrottement() {
		// TODO Auto-generated method stub
		
	}

}
