package multiplayer;

import java.util.Vector;

import org.newdawn.slick.Input;

public class InputModel implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9213348578257473677L;
	/**
	 * 
	 */
	public boolean isPressedLeftClick;
	public boolean isPressedRightClick;
	
	private Vector<String> keydown;
	private Vector<String> keypressed;
	
	public InputModel(){
		keydown = new Vector<String>();
		keypressed = new Vector<String>();
	}
//	public InputModel(String input){
//		keydown = new Vector<Integer>();
//		keypressed = new Vector<Integer>();
//		
//		String[] res = input.split(";");
//		String[] down = res[0].split(",");
//		String[] pressed = res[1].split(",");
//		
//		for(String s  : down){
//			keydown.add(Integer.parseInt(s));
//		}
//		for(String s  : pressed){
//			keypressed.add(Integer.parseInt(s));
//		}
//	}
	
	public InputModel(Input input){
		keydown = new Vector<String>();
		keypressed = new Vector<String>();
		this.isPressedLeftClick = input.isMousePressed(Input.MOUSE_LEFT_BUTTON);
		this.isPressedRightClick = input.isMousePressed(Input.MOUSE_RIGHT_BUTTON);
		for(int i=0; i<250; i++){
			if(input.isKeyDown(i)){
				keydown.add(""+i);
			}
		}
		for(int i=0; i<250; i++){
			if(input.isKeyPressed(i)){
				keypressed.add(""+i);
			}
		}
	}
	
	public boolean isKeyDown(int key){
		return keydown.contains(""+key);
	}
	public boolean isKeyPressed(int key){
		return keypressed.contains(""+key);
	}
	
//	@Override
//	public String toString(){
//		String result = "";
//		for(int k : keydown){
//			result+= k+",";
//		}		
//		result +=";";
//		for(int k : keypressed){
//			result+= k+",";
//		}
//		return result;
//	}

}
