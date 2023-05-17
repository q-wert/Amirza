
// Name: Aayush Khanna	
// USC NetID: aayushkh	
// CS 455 PA4
// Spring 2017

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A dictionary of all anagram sets. Note: the processing is case-sensitive; so
 * if the dictionary has all lower case words, you will likely want any string
 * you test to have all lower case letters too, and likewise if the dictionary
 * words are all upper case.
 */

public class AnagramDictionary {

    private final static int ZERO = 0;
    private final static int ONE = 1;
    private Map<Character, Integer> singleWord;
    private Map<Map<Character, Integer>, ArrayList<String>> dictionaryMap;

    public AnagramDictionary() {
        singleWord = new TreeMap<Character, Integer>();
        dictionaryMap = new HashMap<Map<Character, Integer>, ArrayList<String>>();
    }


    public AnagramDictionary(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            ArrayList<String> temp = new ArrayList<String>();
            dictionaryMap = new HashMap<Map<Character, Integer>, ArrayList<String>>();
            while ((line = br.readLine()) != null) {
                singleWord = new TreeMap<Character, Integer>();
                for (int i = 0; i < line.length(); i++) {
                    char letter = line.charAt(i);
                    int count = singleWord.containsKey(letter) ? singleWord.get(letter) : ZERO;
                    singleWord.put(letter, count + ONE);
                }
                temp = dictionaryMap.containsKey(singleWord) ? dictionaryMap.get(singleWord) : new ArrayList<String>();
                temp.add(line);
                dictionaryMap.put(singleWord, temp);
            }
        }
    }
    public ArrayList<String> getAnagramsOf(String s) {
        String convertedString = s.toLowerCase();
        Map<Character, Integer> tempWord = new TreeMap<Character, Integer>();
        for (int i = 0; i < convertedString.length(); i++) {
            char letter = convertedString.charAt(i);
            int count = tempWord.containsKey(letter) ? tempWord.get(letter) : ZERO;
            tempWord.put(letter, count + ONE);
        }
        ArrayList<String> temp = dictionaryMap.containsKey(tempWord) ? dictionaryMap.get(tempWord)
                : new ArrayList<String>();
        return temp;
    }

}