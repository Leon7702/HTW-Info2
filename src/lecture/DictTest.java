package lecture;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DictTest {
    private int size; // Size of the hash table
    private List<LinkedList<String>> table; // Hash table

    public DictTest(int size) {
        this.size = size;
        table = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            table.add(new LinkedList<>());
        }
    }

    public void loadWordsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String word = line.trim().toLowerCase();
                int index = hash(word);
                table.get(index).add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNumberOfEntries() {
        int count = 0;
        for (LinkedList<String> list : table) {
            count += list.size();
        }
        return count;
    }

    public int getNumberOfCollisions() {
        int count = 0;
        for (LinkedList<String> list : table) {
            if (list.size() > 1) {
                count += list.size() - 1;
            }
        }
        return count;
    }

    public int getLongestChainLength() {
        int maxLength = 0;
        for (LinkedList<String> list : table) {
            maxLength = Math.max(maxLength, list.size());
        }
        return maxLength;
    }

    public void fixHashFunction() {
        int newSize = size * 2; // Double the size of the hash table
        List<LinkedList<String>> newTable = new ArrayList<>(newSize);
        for (int i = 0; i < newSize; i++) {
            newTable.add(new LinkedList<>());
        }

        for (LinkedList<String> list : table) {
            for (String word : list) {
                int index = improvedHash(word);
                newTable.get(index).add(word);
            }
        }

        size = newSize; // Update the size to the new size
        table = newTable; // Assign the new hash table
    }

    public List<String> lookup(String word) {
        int index = hash(word);
        return table.get(index);
    }

    private int hash(String word) {
        return Math.abs(word.hashCode() % size);
    }

    private int improvedHash(String word) {
        int hash = 0;
        for (char c : word.toCharArray()) {
            hash += c - 'a' + 1; // Map 'a' to 1, 'b' to 2, and so on
        }
        return Math.abs(hash % size);
    }

    public static void main(String[] args) {
        DictTest dictionary = new DictTest(100);
        dictionary.loadWordsFromFile("/Users/leon/Documents/HTW/Info2/HTW-Info2/src/lab12/words.txt");

        System.out.println("Number of entries: " + dictionary.getNumberOfEntries());
        System.out.println("Number of collisions: " + dictionary.getNumberOfCollisions());
        System.out.println("Longest chain length: " + dictionary.getLongestChainLength());

        dictionary.fixHashFunction();
        System.out.println("After fixing hash function:");
        System.out.println("Number of entries: " + dictionary.getNumberOfEntries());
        System.out.println("Number of collisions: " + dictionary.getNumberOfCollisions());
        System.out.println("Longest chain length: " + dictionary.getLongestChainLength());

        String letters = "abcdefgh";
        List<String> permutations = dictionary.lookup(letters);
        System.out.println("Permutations of " + letters + ":");
       /* for (String word : permutations) {
            System.out.println(word);
        }*/
    }
}

