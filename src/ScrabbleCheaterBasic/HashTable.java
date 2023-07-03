package ScrabbleCheaterBasic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class HashTable {
	private int size;
    private LinkedList<String>[] table;
    private HashMap<String, Integer> collisions;
    
    private int countCollisions = 0;

    public HashTable(int size) {
        this.size = size;
        collisions = new HashMap<>();
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    public void put(String key) {
    	String normalized = normalizeString(key);
    	if (collisions.containsKey(normalized)) {
    		table[collisions.get(normalized)].add(key);
    		return;
    	}
        int index = hashFunction(normalized);
        if(!isCollision(index, key)) {
        	table[index].add(key);
        } else {
        	index = findEmptyIndex();
        	collisions.put(normalized, index);
        	table[index].add(key);
        	countCollisions++;
        }
    }
        

    public boolean contains(String key) {
    	String normalized = normalizeString(key);
        int index = hashFunction(normalized);
        LinkedList<String> bucket = table[index];
        return bucket.contains(key);
    }
    
    public LinkedList<String> get(int index) {
    	return table[index];
    }
    
	private int hashFunction(String word) {
		int hash = word.hashCode();
		int n = Math.abs(hash % size);
		return n;
    }
	
    public  boolean arePermutations(String str1, String str2) {
        // Normalize the strings
        String normalizedStr1 = normalizeString(str1);
        String normalizedStr2 = normalizeString(str2);

        // Compare the normalized strings
        return normalizedStr1.equals(normalizedStr2);
    }
	
	
	public String normalizeString(String input) {
        char[] charArray = input.toLowerCase().toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
	
	private boolean isCollision(int index, String element) {
		if(table[index].isEmpty()) return false;
		String normalizedString = normalizeString(table[index].getFirst());
		return !arePermutations(normalizedString, element);
	}
	
	private int findEmptyIndex() {
		for (int i = 0; i < 100000; i++) {
			if(table[i].isEmpty()) {
				return i;
			}
		}
		return 0;
	}
	
	// Statistical functions and other
	
	public int countElements() {
		int count = 0;
		for(LinkedList<String> list: table) {
			count += list.size();
		}
		return count;
	}
	
	public int getCollisionCount() {
		return countCollisions;
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
		String normalized = normalizeString(str);
		
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
