import java.util.*;
import java.io.*;

public class Dictionary {
	
	Hashtable<String,String> dictionary;
	
	public Dictionary() {	//creates a hashmap with all words in the dictionary text file
		dictionary = new Hashtable<String,String>();
		try {
			File f = new File("words.txt");
			String path = f.getAbsolutePath();	
			BufferedReader in = new BufferedReader(new FileReader(path));
			String line;
			while((line = in.readLine())!=null) {
				line = line.toUpperCase();
				dictionary.put(line,line);		//creates a hashmap with all words in the dictionary text file
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
	
}