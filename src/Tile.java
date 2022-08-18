import javafx.scene.control.Button;

public class Tile extends Button{
	
	boolean letter;		//true if letter false if board tile
	int x,y;			//x and y cords when placed on board
	int index;			//index in currLetters
	
	public Tile(int i,boolean let) {		//letter tile
		letter = let;
		index = i;
		x=-1;
		y=-1;
	}
	
	public Tile(int a,int b) {		//board tile
		letter = false;
		index = -1;			
		x=a;				
		y=b;
	}
	
	public boolean isLetter() {
		return letter;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getIndex() {
		return index;
	}
}
