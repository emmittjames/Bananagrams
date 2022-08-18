import java.util.*;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class Letters {
	
	private ArrayList<Character> letterPool = new ArrayList<Character>();
	private ArrayList<Character> currLetters = new ArrayList<Character>();
	
	public Letters() {
		//String str = "AAAAAAAAAAAAABBBCCCDDDDDDEEEEEEEEEEEEEEEEEEFFFGGGGHHHIIIIIIIIIIIIJJKKLLLLLMMMNNNNNNNNOOOOOOOOOOOPPPQQRRRRRRRRRSSSSSSTTTTTTTTTUUUUUUVVVWWWXXYYYZZ";
		//String str = "AABBCCDDEEFFGGHIIJKLMNNOOPQRRSSTTUVWXYZ";
		//String str = "HELLO";
		String str = "ABCDEFGHIJK";
		for(int i=0;i<str.length();i++) {
			letterPool.add(str.charAt(i));
		}
		//startingLetters(21);
		startingLetters(3);
	}
	
	public void remove(char c) {		//puts a letter back into the players hand
		currLetters.add(c);
	}
	
	public void play(char c) {		//removes the played letter from the players hand
		for(int i=0;i<currLetters.size();i++) {
			if(currLetters.get(i)==c) {
				currLetters.remove(i);
				return;
			}
		}
	}
	
	public boolean checkPeel() {		
		if(currLetters.size()==0) {
			return true;
		}
		return false;
	}
	
	public boolean checkGameOver() {	
		if(currLetters.size()==0 && letterPool.size()==0) {
			return true;
		}
		return false;
	}
	
	public void peel() {
		int rand = (int)(Math.random()*letterPool.size());
		currLetters.add(letterPool.remove(rand));
	}
	
	public void dump(char c) {		//dump function which puts a desired letter back into the pool but makes the player take 3 more
		int index=0;
		for(int i=0;i<currLetters.size();i++) {
			if(currLetters.get(i)==c) {
				index=i;						//gets index of dumped letter
			}
		}
		peel();				
		peel();
		peel();
		letterPool.add(currLetters.remove(index));		//moves dumped letter from hand to pool
	}
	
	public ArrayList<Character> getPool() {
		return letterPool;
	}
	
	public ArrayList<Character> getCurrLets() {
		return currLetters;
	}
	
	private void startingLetters(int amount) {		//distributes [amount] of letters into currLetters
		for(int i=0;i<amount;i++) {
			int rand = (int)(Math.random()*letterPool.size());
			currLetters.add(letterPool.remove(rand));
		}
	}
	
	public boolean hasLetter(char c) {		//returns true if currLetters contains c
		for(char x:currLetters) {
			if(x==c)
				return true;
		}
		return false;
	}
	
	public HBox getHBox() {
		HBox box = new HBox(5);
		for(char c:currLetters) {
			box.getChildren().add(new Button(c+""));
		}
		return box;
	}
}