package Lab12_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class HashTable {
    private int size;
    private HashMap<String, Integer> collisions;
    private LinkedList<String>[] table;

    private int collisionCounter = 0;

    public HashTable(int size) {
        collisions = new HashMap<>();
        this.size = size;
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    public void insert(String k) {
        String normWord = normalize(k);
        if (collisions.containsKey(normWord)) {
            table[collisions.get(normWord)].add(k);
            return;
        }
        int index = hashFunction(normWord);
        if(!isCollision(index, k)) {
            table[index].add(k);
        } else {
            index = emptyIndex();
            collisions.put(normWord, index);
            table[index].add(k);
            collisionCounter++;
        }
    }


    public boolean contains(String k) {
        String normWord = normalize(k);
        int index = hashFunction(normWord);
        LinkedList<String> bucket = table[index];
        return bucket.contains(k);
    }

    public LinkedList<String> get(int index) {
        return table[index];
    }

    private int hashFunction(String word) {
        int hash = word.hashCode();
        int index = Math.abs(hash % size);
        return index;
    }

    public boolean checkPermutation(String str1, String str2) {
        String norm1 = normalize(str1);
        String norm2 = normalize(str2);

        return norm1.equals(norm2);
    }


    public String normalize(String input) {
        char[] charArray = input.toLowerCase().toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    private boolean isCollision(int index, String element) {
        if(table[index].isEmpty()) return false;
        String normString = normalize(table[index].getFirst());
        return !checkPermutation(normString, element);
    }

    private int emptyIndex() {
        for (int i = 0; i < 100000; i++) {
            if(table[i].isEmpty()) {
                return i;
            }
        }
        return 0;
    }

    public int countElements() {
        int count = 0;
        for(LinkedList<String> list: table) {
            count += list.size();
        }
        return count;
    }

    public int getCollisionCount() {
        return collisionCounter;
    }

    public ArrayList<LinkedList<String>> getLongestChain() {
        ArrayList<LinkedList<String>> lists = new ArrayList<>();
        int highestCount = 0;
        for(int i = 0; i < table.length; i++) {
            LinkedList<String> currentList = table[i];
            if(currentList.size() > highestCount) {
                highestCount = currentList.size();
                lists.clear();
                lists.add(currentList);
            } else if(currentList.size() == highestCount) {
                lists.add(currentList);
            }
        }
        return lists;
    }

    public LinkedList<String> lookupString(String str) {
        String normalized = normalize(str);

        if(collisions.containsKey(normalized)) {
            return table[collisions.get(normalized)];
        }

        int index = hashFunction(normalized);
        if(isCollision(index, str)) return null;
        return table[index];
    }

    public int howManyLists() {
        LinkedList<String> list = null;
        int count = 0;
        for(int i = 0; i < table.length; i++) {
            list = table[i];
            if(list == null || list.isEmpty()) {

            } else {
                count++;
            }
        }
        return count;
    }













}

