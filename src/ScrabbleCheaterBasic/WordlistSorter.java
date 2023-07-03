package ScrabbleCheaterBasic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WordlistSorter {
	private String filename;
	private File writingFile;
	private BufferedReader reader;
	private FileWriter writer;
	
	public WordlistSorter() {
		filename = "/Users/janniselsner/eclipse-workspace/InfoLabs/src/ScrabbleCheaterBasic/wordlist";
		
		writingFile = new File("/Users/janniselsner/eclipse-workspace/InfoLabs/src/ScrabbleCheaterBasic/newWordList");
		try {
			FileReader fileReader = new FileReader(filename);
			reader = new BufferedReader(fileReader);
			writer = new FileWriter(writingFile);
			writeValidWordsToFile();
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		} catch (IOException e) {
			System.out.println("IO Exception!");
		}
	}
	
	public void writeToConsole() throws IOException {
		String line = "";
		for(int i = 0; i < 10; i++) {
			line = reader.readLine();
			System.out.println(line);
		}
		reader.close();
	}
	
	public void writeValidWordsToFile() throws IOException {
		String line = "";
		int count = 0;
		while(!(line == null)) {
			
			line = reader.readLine();
			
			if(line == null) return;
			
			if(line.length() > 7 || line.length() < 2) {
				count++;
			} else {
				writer.write(line);
				writer.write("\n");
			}
		}
		System.out.println("Unvalid words: " + count);
		writer.close();
		reader.close();
	}
}
