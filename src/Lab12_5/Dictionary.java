package Lab12_5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Dictionary {
    private BufferedReader reader;
    private Lab12_5.HashTable hashTable;

    public Dictionary(int capacity) {
        hashTable = new HashTable(capacity);
        String file = "/Users/leon/Documents/HTW/Info2/HTW-Info2/src/ScrabbleCheaterBasic/newWordList";
        try {
            FileReader fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);
            fillHashTable();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Dictionary(int capacity, int wordSize) {
        hashTable = new HashTable(capacity);
        String file = "/Users/leon/Documents/HTW/Info2/HTW-Info2/src/ScrabbleCheaterBasic/newWordList";
        try {
            FileReader fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);
            fillHashTable(wordSize);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fillHashTable() throws IOException {
        String line = "";
        int count = 0;

        while ((line = reader.readLine()) != null) {
            count++;
                hashTable.insertWord(line);
        }
    }

    public void fillHashTable(int wordSize) throws IOException {
        String line = "";
        int count = 0;

        while ((line = reader.readLine()) != null) {
            count++;
            if (line.length() == wordSize) {
                hashTable.insertWord(line);
            }
        }
        System.out.println(count);
    }

    public void printTable() {
        for(int i = 0; i < 75000; i++) {
            System.out.println(i + ": ");
            for (int j = 0; j < hashTable.getList(i).size(); j++) {
                String str = hashTable.getList(i).get(j);
                System.out.println(str);
            }
            System.out.println();
        }
    }

    public HashTable getHashTable() {
        return hashTable;
    }

    public void printStatistics() {
        System.out.println("INFORMATION");

        System.out.println("Number of entries: " + hashTable.countWords());
        System.out.println();
        System.out.println("Number of collisions: " + hashTable.getCollisionCount());
        System.out.println();

        ArrayList<LinkedList<String>> longestChains = hashTable.getLongestChain();
        System.out.println("Longest Chains: " + "(Size " + longestChains.get(0).size() + ")");
        for(int i = 0; i < longestChains.size(); i++) {
            System.out.print(">>> [");
            for(int j = 0; j < longestChains.get(i).size(); j++) {
                System.out.print(longestChains.get(i).get(j) + ", ");
            }
            System.out.println("]");
        }
        System.out.println();

        System.out.println("How many lists: " + hashTable.getNumberOfEntries());
        System.out.println();
    }
    
    public void scrabbleCheater(String word) {
        String scrabbleWord = word;
        LinkedList<String> matches = hashTable.lookup(scrabbleWord);

        System.out.println("Possible Words with: " + word );
        System.out.print(">>> [");
        for(String str : matches) {
            System.out.print(str + ", ");
        }
        System.out.print("]");
        System.out.println();
    }
}


