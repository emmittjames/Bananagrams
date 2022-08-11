import java.util.*;

public class Letters {
	
	private ArrayList<Character> letterPool = new ArrayList<Character>();
	private ArrayList<Character> currLetters = new ArrayList<Character>();
	
	public Letters() {
		//String str = "AAAAAAAAAAAAABBBCCCDDDDDDEEEEEEEEEEEEEEEEEEFFFGGGGHHHIIIIIIIIIIIIJJKKLLLLLMMMNNNNNNNNOOOOOOOOOOOPPPQQRRRRRRRRRSSSSSSTTTTTTTTTUUUUUUVVVWWWXXYYYZZ";
		//String str = "AABBCCDDEEFFGGHIIJKLMNNOOPQRRSSTTUVWXYZ";	//smaller letter pool for testing
		String str = "ABCDEFG";
		for(int i=0;i<str.length();i++) {
			letterPool.add(str.charAt(i));
		}
		//startingLetters(21);
		startingLetters(3);
	}
	
	public void play(char c) {
		for(int i=0;i<currLetters.size();i++) {
			if(currLetters.get(i)==c) {
				currLetters.remove(i);
				return;
			}
		}
	}
	
	public boolean check() {		//checks gameover or peel;
		if(currLetters.size()==0) {
			if(letterPool.size()<1) {		//< # of players for gameover
				return true;
			}
			peel();
			System.out.println("PEEL!");
		}
		return false;
	}
	
	private void peel() {
		int rand = (int)(Math.random()*letterPool.size());
		currLetters.add(letterPool.remove(rand));
	}
	
	public boolean dump(char c) {		//dump function which puts a desired letter back into the pool but makes the player take 3 more
		if(currLetters.size()>3) {
			peel();						//under construction
			peel();
			peel();
			letterPool.add(c);
		}
		return true;
	}
	
	public ArrayList<Character> getPool() {
		return letterPool;
	}
	
	public ArrayList<Character> getLets() {
		return currLetters;
	}
	
	private void startingLetters(int amount) {		//distributes [amount] of letters into currLetters
		for(int i=0;i<amount;i++) {
			int rand = (int)(Math.random()*letterPool.size());
			currLetters.add(letterPool.remove(rand));
		}
		//System.out.println(currLetters);
		//System.out.println(letterPool);
	}
	
	public boolean hasLetter(char c) {		//returns true if currLetters contains c
		for(char x:currLetters) {
			if(x==c)
				return true;
		}
		return false;
	}
}
