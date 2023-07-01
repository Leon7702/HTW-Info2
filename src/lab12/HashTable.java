package lab12;
import java.util.LinkedList;

public class HashTable {
    // Creating a node to store our Strings
    private static class Node {
        private final String key;
        private String value;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    // Creating an array of LinkedList<Node> named table
    private LinkedList<Node>[] table;
    private int capacity;
    private int size; // Number of elements stored in the hash table
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    // Constructor for HashTable
    public HashTable(int initialCapacity) {
        this.capacity = initialCapacity;
        this.table = new LinkedList[capacity];
        this.size = 0;
    }

    private int hash(String key) {
        int result= key.hashCode() % capacity;
        if(result>0) {
            return result;
        }
        else{
            hash(key);

        }
        return  0;
    }

    private void resizeTable() {
        int newCapacity = capacity * 2;
        LinkedList<Node>[] newTable = new LinkedList[newCapacity];

        for (LinkedList<Node> list : table) {
            if (list != null) {
                for (Node node : list) {
                    int newIndex = hash(node.key);
                    LinkedList<Node> newList = newTable[newIndex];
                    if (newList == null) {
                        newList = new LinkedList<>();
                        newTable[newIndex] = newList;
                    }
                    newList.add(node);
                }
            }
        }

        table = newTable;
        capacity = newCapacity;
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
        size++;

        double loadFactor = (double) size / capacity;
        if (loadFactor > LOAD_FACTOR_THRESHOLD) {
            resizeTable();
        }
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
                    System.out.print("(" + node.key + ", " + node.value + ") ");
                }
                System.out.println();
            }
        }
    }
}
