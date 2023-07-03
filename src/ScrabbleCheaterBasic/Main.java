package ScrabbleCheaterBasic;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) {
		HashTable table;
		int size = 70234;
		size = 33202;
		Dictionary dic = new Dictionary(size, 7);
		table = dic.getTable();
		for(int i = 0; i < size; i++) {
			System.out.println(i + ": ");
			for (int j = 0; j < table.get(i).size(); j++) {
				String str = table.get(i).get(j);
				System.out.println(str);
			}
			System.out.println();
		}
		System.out.println("----- Statistics -----");
		
		System.out.println("Elements in HashTable: " + table.countElements());
		System.out.println();
		System.out.println("Collisions while creating: " + table.getCollisionCount());
		System.out.println();
		
		ArrayList<LinkedList<String>> longestChains = table.getLongestChain();
		printLists(longestChains, "Longest Chains: ");
		
		// Searching words:
		String searching = "claimed";
		LinkedList<String> foundList = table.lookupString(searching);
		printList(foundList, "Words with Letters \"" + searching + "\": " );
		
		System.out.println();
		System.out.println("How many lists: " + table.howManyLists());
		
	}
	
	private static void printList(LinkedList<String> list, String text) {
		if(list == null || list.isEmpty() ) {
			System.out.println();
			System.out.println(text + "No Elements");
			return;
		}
		System.out.println(text + "(Size " + list.size() + ")");
		System.out.print("---> {");
		for(String str : list) {
			System.out.print(str + ", ");
		}
		System.out.print("}");
		System.out.println();
	}
	
	private static void printLists(ArrayList<LinkedList<String>> lists, String text) {
		if(lists == null || lists.isEmpty()) {
			System.out.println();
			System.out.println(text + "No Elements");
			return;
		}
		System.out.println(text + "(Size " + lists.get(0).size() + ")");
		for(int i = 0; i < lists.size(); i++) {
			System.out.print("---> {");
			for(int j = 0; j < lists.get(i).size(); j++) {
				System.out.print(lists.get(i).get(j) + ", ");
			}
			System.out.println("}");
		}
		System.out.println();
	}
}
