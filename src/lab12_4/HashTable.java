package lab12_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HashTable {
    private List<String>[] buckets;
    private int entries;
    private int collisions;
    private List<String> longestChain;

    public HashTable(int tableSize) {
        buckets = new ArrayList[tableSize];
        for (int i = 0; i < tableSize; i++) {
            buckets[i] = new ArrayList<>();
        }
        entries = 0;
        collisions = 0;
        longestChain = new ArrayList<>();
    }

    public void insert(String word) {
        String normalizedWord = normalize(word);
        int hash = getHash(normalizedWord);
        List<String> bucket = buckets[hash];
        if (isCollision(bucket, normalizedWord)) {
            int index = findEmptyIndex();
            buckets[index].add(word);
            collisions++;
        } else {
            bucket.add(word);
            if (bucket.size() > longestChain.size()) {
                longestChain = bucket;
            }
        }
        entries++;
    }

    public boolean contains(String word) {
        String normalizedWord = normalize(word);
        int hash = getHash(normalizedWord);
        List<String> bucket = buckets[hash];
        return bucket.contains(word);
    }

    public List<String> getBucket(int index) {
        return buckets[index];
    }

    public int getSize() {
        return buckets.length;
    }

    public int getEntries() {
        return entries;
    }

    public int getCollisions() {
        return collisions;
    }

    public List<String> getLongestChain() {
        return longestChain;
    }

    private String normalize(String word) {
        char[] charArray = word.toLowerCase().toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    private int getHash(String word) {
        return Math.abs(word.hashCode()) % buckets.length;
    }

    private boolean isCollision(List<String> bucket, String normalizedWord) {
        for (String word : bucket) {
            if (arePermutations(normalize(word), normalizedWord)) {
                return true;
            }
        }
        return false;
    }

    private boolean arePermutations(String str1, String str2) {
        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);
        return Arrays.equals(charArray1, charArray2);
    }

    private int findEmptyIndex() {
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i].isEmpty()) {
                return i;
            }
        }
        return 0;
    }

    public void printHashTable() {
        for (int i = 0; i < getSize(); i++) {
            List<String> bucket = getBucket(i);
            System.out.print("Bucket " + i + ": ");
            if (bucket.isEmpty()) {
                System.out.println("Empty");
            } else {
                System.out.println(bucket);
            }
        }
    }
}
