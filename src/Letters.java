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
	
	public void peel() {
		int rand = (int)(Math.random()*letterPool.size());
		currLetters.add(letterPool.remove(rand));
	}
	
	public boolean dump(char c) {		//Under construction
		if(currLetters.size()>3) {
			peel();
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
	
	public void startingLetters(int amount) {		//distributes 21 letters into currLetters
		for(int i=0;i<amount;i++) {
			int rand = (int)(Math.random()*letterPool.size());
			currLetters.add(letterPool.remove(rand));
		}
		//System.out.println(currLetters);
		//System.out.println(letterPool);
	}
	
	public boolean valid(char c) {
		for(char x:currLetters) {
			if(x==c)
				return true;
		}
		return false;
	}
}
