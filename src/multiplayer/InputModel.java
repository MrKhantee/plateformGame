package multiplayer;

import java.util.Vector;

import org.newdawn.slick.Input;

public class InputModel {
	
	public boolean isPressedLeftClick;
	public boolean isPressedRightClick;
	
	private Vector<Integer> keydown;
	private Vector<Integer> keypressed;
	public InputModel(){
		keydown = new Vector<Integer>();
		keypressed = new Vector<Integer>();
	}
	
	public InputModel(Input input){
		keydown = new Vector<Integer>();
		keypressed = new Vector<Integer>();
		this.isPressedLeftClick = input.isMousePressed(Input.MOUSE_LEFT_BUTTON);
		this.isPressedRightClick = input.isMousePressed(Input.MOUSE_RIGHT_BUTTON);
		for(int i=0; i<250; i++){
			if(input.isKeyDown(i)){
				keydown.add(i);
			}
		}
		for(int i=0; i<250; i++){
			if(input.isKeyPressed(i)){
				keypressed.add(i);
			}
		}
	}
	
	public boolean isKeyDown(int key){
		return keydown.contains(key);
	}
	public boolean isKeyPressed(int key){
		return keypressed.contains(key);
	}

}
