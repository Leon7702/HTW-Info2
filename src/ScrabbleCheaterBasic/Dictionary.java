package ScrabbleCheaterBasic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary {
	
	private BufferedReader reader;
	private HashTable table;
	
	public Dictionary(int size) {
		table = new HashTable(size);
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
	
	public Dictionary(int size, int wordSize) {
		table = new HashTable(size);
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
		
		while (!(line == null)) {
			line = reader.readLine();
			if(line == null) return;
			count++;
			table.put(line);
		}
		System.out.println(count);
	}
	
	public void fillHashTable(int size) throws IOException {
		String line = "";
		int count = 0;
		
		while (!(line == null)) {
			line = reader.readLine();
			if(line == null) return;
			count++;
			if(line.length() == size)table.put(line);
		}
		System.out.println(count);
	}
	
	public HashTable getTable() {
		return table;
	}
	
}
