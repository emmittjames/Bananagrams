import java.util.*;
import java.io.*;

public class Dictionary {
	
	private Hashtable<String,String> dictionary;
	
	public Dictionary() {	//creates a hashmap with all words in the dictionary text file (words.txt)
		dictionary = new Hashtable<String,String>();
		try {
			File f = new File("src/words.txt");
			String path = f.getAbsolutePath();	
			BufferedReader in = new BufferedReader(new FileReader(path));
			String line;
			while((line = in.readLine())!=null) {
				line = line.toUpperCase();
				dictionary.put(line,line);
			}
			in.close();
		} catch(IOException e) {
			System.out.println("IO exception");
		}
	}
	
	public boolean isWord(String word) {
		word = word.toUpperCase();
		if(dictionary.contains(word)) {
			return true;
		}
		return false;
	}
	
	public static void main(String[]args) {
		Dictionary d = new Dictionary();
		System.out.println(d.isWord("asdasdvf"));
	}
}