package multiplayer;

import java.util.Vector;

import org.newdawn.slick.Input;

public class InputModel {
	
	public boolean isPressedLeftClick;
	public boolean isPressedRightClick;
	
	private Vector<Integer> keydown;
	
	public InputModel(Input input){
		this.isPressedLeftClick = input.isMousePressed(Input.MOUSE_LEFT_BUTTON);
		this.isPressedRightClick = input.isMousePressed(Input.MOUSE_RIGHT_BUTTON);
		for(int i=0; i<250; i++){
			if(input.isKeyDown(i)){
				keydown.add(i);
			}
		}
	}
	
	public boolean isKeyDown(int key){
		return keydown.contains(key);
	}

}
