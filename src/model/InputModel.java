package model;


import org.newdawn.slick.Input;

public class InputModel implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6349329859623778400L;
	public boolean isPressedLeftClick;
	public boolean isPressedRightClick;
	
	private boolean[] keydown;
	private boolean[] keypressed;
	
	public InputModel(){
		keydown = new boolean[250];
		keypressed = new boolean[250];
	}

	public InputModel(String input){
		keydown = new boolean[250];
		keypressed = new boolean[250];
		
		String[] res = input.split(";");
		String[] down = res[0].split(",");
		String[] pressed = res[1].split(",");
		
//		for(String s  : down){
//			keydown.add(s);
//		}
//		for(String s  : pressed){
//			keypressed.add(s);
//		}
		
		isPressedLeftClick = res[2].equals("1");
		isPressedRightClick = res[3].equals("1");
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
	}
	
	public boolean isKeyDown(int key){
		return keydown[key];
	}
	public boolean isKeyPressed(int key){
		return keypressed[key];
	}
	

//	@Override
//	public String toString(){
//		String result = "";
//		for(String k : keydown){
//			result+= k+",";
//		}		
//		result +=";";
//		for(String k : keypressed){
//			result+= k+",";
//		}
//		result+=";";
//		result+=isPressedLeftClick ? 1 : 0;
//		result+=";";
//		result+=isPressedRightClick ? 1 : 0;
//		return result;
//	}

}
