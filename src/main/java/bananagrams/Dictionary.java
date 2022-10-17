package bananagrams;

import java.util.*;
import java.io.*;

public class Dictionary {

    Hashtable<String,String> dictionary;

    public Dictionary() {	//creates a hashmap with all words in the dictionary text file (words.txt)
        dictionary = new Hashtable<>();
        try {
            InputStream is = Dictionary.class.getResourceAsStream("/words.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                line = line.strip();
                line = line.toUpperCase();
                dictionary.put(line,line);
            }
            reader.close();
            is.close();
        } catch(IOException e) {
            System.out.println("Dictionary file does not exist");
            System.exit(0);
        }
    }

    public boolean isWord(String word) {
        word = word.toUpperCase();
        return dictionary.contains(word);
    }
}