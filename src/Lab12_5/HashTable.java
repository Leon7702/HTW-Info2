package Lab12_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class HashTable {
    private int capacity;                           // size of HashTable
    private HashMap<String, Integer> collisionMap;  // Map to store collisions
    private LinkedList<String>[] hashArray;         // Linked List Array

    private int collisionCount = 0;

    public HashTable(int capacity) {
        collisionMap = new HashMap<>();
        this.capacity = capacity;
        hashArray = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            hashArray[i] = new LinkedList<>();      // Initialize a new LinkedList for each Index of the LinkedList array
        }
    }

    public void insertWord(String word) {
        String normalizedWord = normalizeWord(word);
        if (collisionMap.containsKey(normalizedWord)) {
            // If the normalized word was a collision before, add it to the new index in the hash table
            hashArray[collisionMap.get(normalizedWord)].add(word);
            return;
        }
        int index = calculateHash(normalizedWord);
        if (!checkCollision(index, word)) {
            // If there is no collision, add the word to the corresponding index in the hash table
            hashArray[index].add(word);
        } else {
            // If there is a collision, find an empty index, update the collision map, and add the word there
            index = findEmptyIndex();
            collisionMap.put(normalizedWord, index);
            hashArray[index].add(word);
            collisionCount++;
        }
    }

    public boolean containsWord(String word) {
        // Check if the hash table contains a specific word
        String normalizedWord = normalizeWord(word);
        int index = calculateHash(normalizedWord);
        LinkedList<String> bucket = hashArray[index];
        return bucket.contains(word);
    }

    public LinkedList<String> getList(int index) {
        // Get the linked list at a specific index in the hash table
        return hashArray[index];
    }

    private int calculateHash(String word) { // Calculate the hash value for the word
        int hashCode = word.hashCode();
        int index = Math.abs(hashCode % capacity);
        return index;
    }

    public boolean checkPermutation(String str1, String str2) {
        // Check if two strings are permutations of each other
        String normalized1 = normalizeWord(str1);
        String normalized2 = normalizeWord(str2);
        return normalized1.equals(normalized2);
    }

    public String normalizeWord(String input) {
        // Normalize a word by converting it to lowercase and sorting its characters
        char[] charArray = input.toLowerCase().toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    private boolean checkCollision(int index, String word) {
        // Check if there is a collision at a given index
        if (hashArray[index].isEmpty()) return false;
        String normIndexString = normalizeWord(hashArray[index].getFirst());
        return !checkPermutation(normIndexString, word);
    }

    private int findEmptyIndex() {
        // Find an empty index in the hash table
        for (int i = 0; i < capacity; i++) {
            if (hashArray[i].isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    public int countWords() {
        // Count the total number of words in the hash table
        int count = 0;
        for (LinkedList<String> list : hashArray) {
            count += list.size();
        }
        return count;
    }

    public int getCollisionCount() {
        // Get the total number of collisions
        return collisionCount;
    }

    public ArrayList<LinkedList<String>> getLongestChain() {
        // Get the linked lists with the longest chain
        ArrayList<LinkedList<String>> lists = new ArrayList<>();
        int highestCount = 0;
        for (int i = 0; i < hashArray.length; i++) {
            LinkedList<String> currentList = hashArray[i];
            if (currentList.size() > highestCount) {
                highestCount = currentList.size();
                lists.clear();
                lists.add(currentList);
            } else if (currentList.size() == highestCount) {
                lists.add(currentList);
            }
        }
        return lists;
    }

    public LinkedList<String> lookup(String word) {
        //return linked list of a specific word
        String normalized = normalizeWord(word);
        if (collisionMap.containsKey(normalized)) {
            return hashArray[collisionMap.get(normalized)];
        }
        int index = calculateHash(normalized);
        if (checkCollision(index, word)) return null;
        return hashArray[index];
    }

    public int getNumberOfEntries() {
        LinkedList<String> list = null;
        int count = 0;
        for (int i = 0; i < hashArray.length; i++) {
            list = hashArray[i];
            if (list == null || list.isEmpty()) {
                // Do nothing
            } else {
                count++;
            }
        }
        return count;
    }
}
