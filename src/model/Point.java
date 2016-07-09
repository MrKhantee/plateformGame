package model;

public class Point implements java.io.Serializable{

	public float x;
	public float y;
	
	public Point(float x, float y){
		this.setXY(x, y);
	}
	
	public void setXY(float x ,float y){
		this.x = x;
		this.y =y ;
	}
	
	public static Point sub(Point p1,Point p2){
		return new Point(p1.x-p2.x,p1.y-p2.y);
	}
	public static Point add(Point p1,Point p2){
		return new Point(p1.x+p2.x,p1.y+p2.y);
	}
	
	public static Point divide(Point p1,float divider){
		 return new Point(p1.x/divider,p1.y/divider);
	}
	public static Point multiply(Point p1,float multiplier){
		 return new Point(p1.x*multiplier,p1.y*multiplier);
	}
	
	public static float scalarProduct(Point p1,Point p2){
		 return p1.x*p2.x +p1.y*p2.y;
	}
	
	public  float norm(){
		return (float)Math.sqrt(this.x*this.x+this.y*this.y);
	}
	
	public String toString(){
		return "x : "+this.x+" y : "+this.y;
	}
}
