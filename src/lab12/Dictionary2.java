package lab12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary2 {

    private HashTable dictionary;

    public Dictionary2(int capacity) {
        generateDict("/Users/leon/Documents/HTW/Info2/HTW-Info2/src/lab12/words.txt", capacity);
    }

    private void generateDict(String filePath,int capacity) {
        dictionary = new HashTable(capacity);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    String cleanedWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
                    if (!cleanedWord.isEmpty() && cleanedWord.length() <= 7) {
                        dictionary.put(cleanedWord);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        dictionary.printTable();
    }

    public static void main(String[] args) {
        Dictionary2 d = new Dictionary2(45000);
    }
}


