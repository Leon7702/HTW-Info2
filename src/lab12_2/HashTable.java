package lab12_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HashTable {
    private static class Node {
        private final String key;
        private String value;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Node>[] table;
    private int capacity;
    private int size; // Track the number of entries
    private int collisions; // Track the number of collisions
    private int longestChain; // Track the length of the longest chain

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.table = new LinkedList[capacity];
        this.size = 0;
        this.collisions = 0;
        this.longestChain = 0;
    }

    private int hash(String key) {
        String normalizedKey = normalize(key);
        return Math.floorMod(normalizedKey.hashCode(), capacity);
    }
    public void put(String value) {
        String key = Integer.toString(value.hashCode());
        int index = hash(key);
        LinkedList<Node> listOfNodes = table[index];

        if (listOfNodes == null) {
            listOfNodes = new LinkedList<>();
            table[index] = listOfNodes;
        }

        for (Node node : listOfNodes) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }

        listOfNodes.add(new Node(key, value));

        // Update statistics
        size++;
        if (listOfNodes.size() > 1) {
            collisions++;
        }
        if (listOfNodes.size() > longestChain) {
            longestChain = listOfNodes.size();
        }
    }

    public static String normalize(String word) {
        word = word.toLowerCase();
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public String get(String value) {
        String key = Integer.toString(value.hashCode());
        int index = hash(key);
        LinkedList<Node> listOfNodes = table[index];

        if (listOfNodes != null) {
            for (Node node : listOfNodes) {
                if (node.key.equals(key)) {
                    return node.value;
                }
            }
        }
        return null;
    }

    public void remove(String value) {
        String key = Integer.toString(value.hashCode());
        int index = hash(key);
        LinkedList<Node> listOfNodes = table[index];

        if (listOfNodes != null) {
            for (Node node : listOfNodes) {
                if (node.key.equals(key)) {
                    listOfNodes.remove(node);

                    // Update statistics
                    size--;
                    return;
                }
            }
        }
    }

    public void printTable() {
        for (int i = 0; i < capacity; i++) {
            LinkedList<Node> listOfNodes = table[i];
            if (listOfNodes != null) {
                System.out.print("Index " + i + ": ");
                for (Node node : listOfNodes) {
                    System.out.print(node.value + " -> ");
                }
                System.out.println();
            }
        }
    }

    public int getNumberOfEntries() {
        return size;
    }

    public int getNumberOfCollisions() {
        return collisions;
    }

    public int getLongestChain() {
        return longestChain;
    }

    public String[] lookup(String word) {
        String key = Integer.toString(word.hashCode());
        int index = hash(key);
        LinkedList<Node> listOfNodes = table[index];

        if (listOfNodes != null) {
            List<String> matchingWords = new ArrayList<>();
            for (Node node : listOfNodes) {
                if (node.key.equals(key)) {
                    matchingWords.add(node.value);
                }
            }
            return matchingWords.toArray(new String[0]);
        }

        return new String[0];
    }
}
