package lab12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {

    private static final int TABLE_SIZE = 45000;
    private Set<String> dict;

    public Dictionary (int capacity) {
        dict = new HashSet<String>();
    }

    private List<List<String>> hashTable;

    private int numEntries;
    private int numCollisions;
    private int longestChain;

    public Dictionary(String filePath) {
        hashTable = new ArrayList<>(TABLE_SIZE);
        for (int i = 0; i < TABLE_SIZE; i++) {
            hashTable.add(new ArrayList<>());
        }
        numEntries = 0;
        numCollisions = 0;
        longestChain = 0;
        generateDict(filePath);
    }

    private void generateDict(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    String cleanedWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
                    if (!cleanedWord.isEmpty() && cleanedWord.length() <= 7) {
                        int hash = hashFunction(cleanedWord);
                        List<String> chain = hashTable.get(hash);
                        hashTable.add(chain);
                        if (chain.isEmpty()) {
                            numEntries++;
                        } else {
                            numCollisions++;
                        }
                        chain.add(cleanedWord);
                        if (chain.size() > longestChain) {
                            longestChain = chain.size();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addWord() {

    }



    public int getNumEntries() {
        return numEntries;
    }

    public int getNumCollisions() {
        return numCollisions;
    }

    public int getLongestChain() {
        return longestChain;
    }

    public boolean containsWord(String word) {
        String cleanedWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
        int hash = hashFunction(cleanedWord);
        return hashTable.get(hash).contains(cleanedWord);
    }

    private int hashFunction(String word) {
        int p = 31; // Prime number for polynomial rolling hash
        int m = TABLE_SIZE; // Size of the hash table
        int hash = 0;
        int power = 1;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            hash = (hash + (c - 'a' + 1) * power) % m;
            power = (power * p) % m;
        }

        return hash;
    }

    public static String normalize(String word) {
        word = word.toLowerCase();
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public String[] lookup(String word) {
        String normalizedWord = normalize(word);
        int hash = hashFunction(normalizedWord);
        List<String> chain = hashTable.get(hash);
        List<String> matchingWords = new ArrayList<>();
        for (String chainWord : chain) {
            if (normalizedWord.equals(normalize(chainWord))) {
                matchingWords.add(chainWord);
            }
        }
        return matchingWords.toArray(new String[0]);
    }

    public static void main (String[] args) {
        Dictionary d = new Dictionary("/Users/leon/Documents/HTW/Info2/HTW-Info2/src/lab12/words.txt");
        System.out.println(Arrays.toString(d.lookup("Aborn")));

    }

}
