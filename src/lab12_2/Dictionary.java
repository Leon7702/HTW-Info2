package lab12_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Dictionary {
    private HashTable dictionary;

    public Dictionary(int capacity) {
        dictionary = new HashTable(capacity);
    }

    public void generateDict(String filePath) {
        //reading in the file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            //as long as there is something left to read...
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    //only allow a-z and A-Z
                    String cleanedWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
                    //clean the word
                    if (!cleanedWord.isEmpty() && cleanedWord.length() <= 7) {
                        //normalizing word so we find the right bucket

                        dictionary.put(cleanedWord);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String normalize(String word) {
        word = word.toLowerCase();
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public void printTable() {
        dictionary.printTable();
    }
    public int getNumberOfEntries() {
        return dictionary.getNumberOfEntries();
    }

    public int getNumberOfCollisions() {
        return dictionary.getNumberOfCollisions();
    }

    public int getLongestChain() {
        return dictionary.getLongestChain();
    }

    public String get(String value) {
        return dictionary.get(value);
    }

    public String[] lookup(String word) {
        return dictionary.lookup(word);
    }

    public static void main(String[] args) {
        Dictionary dict = new Dictionary(45000);
        dict.generateDict("/Users/leon/Documents/HTW/Info2/HTW-Info2/src/lab12_2/newWordList");
        dict.printTable();
        System.out.println(dict.getLongestChain());





    }
}
