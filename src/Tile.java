import javafx.scene.control.Button;

public class Tile extends Button{
	
	boolean type;		//true if letter false if board tile
	int x,y;			//x and y cords when placed on board
	char letter;
	boolean played = false;
	
	public Tile(char c) {		//letter tile
		type = true;
		letter = c;
		x=-1;
		y=-1;
	}
	
	public Tile(int a,int b) {		//board tile
		type = false;		
		x=a;				
		y=b;
	}
	
	public boolean isLetter() {
		return type;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getLetter() {
		return letter;
	}
	
	public void play() {
		played = true;
	}
}
