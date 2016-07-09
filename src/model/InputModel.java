package model;


import org.newdawn.slick.Input;

public class InputModel implements java.io.Serializable{

	/**
	 * 
	 */
	
	public boolean isPressedLeftClick;
	public boolean isPressedRightClick;
	public Point mouse;
	
	
	private boolean[] keydown;
	private boolean[] keypressed;
	
	public InputModel(){
		keydown = new boolean[250];
		keypressed = new boolean[250];
	}

	
	public InputModel(Input input){
		keydown = new boolean[250];
		keypressed = new boolean[250];
		this.isPressedLeftClick = input.isMousePressed(Input.MOUSE_LEFT_BUTTON);
		this.isPressedRightClick = input.isMousePressed(Input.MOUSE_RIGHT_BUTTON);
		for(int i=0; i<250; i++){
			if(input.isKeyDown(i)){
				keydown[i] = true;
			}
		}
		for(int i=0; i<250; i++){
			if(input.isKeyPressed(i)){
				keypressed[i] = true;
			}
		}
		mouse = new Point(input.getMouseX()/Data.ratioSpace,input.getMouseY()/Data.ratioSpace);
	}
	
	public boolean isKeyDown(int key){
		return keydown[key];
	}
	public boolean isKeyPressed(int key){
		return keypressed[key];
	}
	



}
