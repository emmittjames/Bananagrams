import java.util.*;

public class Letters {
	
	private ArrayList<Character> letterPool = new ArrayList<Character>();
	private ArrayList<Character> currLetters = new ArrayList<Character>();
	
	public Letters() {
		String str = "AAAAAAAAAAAAABBBCCCDDDDDDEEEEEEEEEEEEEEEEEEFFFGGGGHHHIIIIIIIIIIIIJJKKLLLLLMMMNNNNNNNNOOOOOOOOOOOPPPQQRRRRRRRRRSSSSSSTTTTTTTTTUUUUUUVVVWWWXXYYYZZ";
		for(int i=0;i<str.length();i++) {
			letterPool.add(str.charAt(i));
		}
		startingLetters(21);
	}
	
	public boolean peel() {
		if(currLetters.size()>0) {
			int rand = (int)(Math.random()*letterPool.size());
			currLetters.add(letterPool.remove(rand));
			if(letterPool.size()>0) {
				return true;
			}
		}
		return false;
	}
	
	public boolean dump(char c) {
		if(currLetters.size()>3) {
			peel();
			peel();
			peel();
			letterPool.add(c);
		}
	}
	
	public int getPool() {
		return letterPool.size();
	}
	
	public void startingLetters(int amount) {		//distributes 21 letters into currLetters
		for(int i=0;i<amount;i++) {
			int rand = (int)(Math.random()*letterPool.size());
			currLetters.add(letterPool.remove(rand));
		}
		System.out.println(currLetters);
		System.out.println(letterPool);
	}
}
