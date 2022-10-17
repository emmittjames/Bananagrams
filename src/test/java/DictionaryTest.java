package bananagrams;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class DictionaryTest {
    Dictionary dictionary;

    @BeforeEach
    public void setupTestFactory() {
        dictionary = new Dictionary();
    }

    @Test
    public void testBasicWords() {
        assertTrue(dictionary.isWord("hello"));
        assertTrue(dictionary.isWord("BoAtS"));
        assertTrue(dictionary.isWord("SPINE"));
        assertTrue(dictionary.isWord("water"));
        assertFalse(dictionary.isWord("watsdfgafer"));
        assertFalse(dictionary.isWord("qwertyui"));
    }
}