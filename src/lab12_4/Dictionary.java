package lab12_4;

import java.io.BufferedReader;
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
        } catch (IOException e) {
            System.out.println("Error reading the file!");
            e.printStackTrace();
        } finally {
            closeReader();
        }
    }

    public Dictionary(int size, int wordSize) {
        table = new HashTable(size);
        String file = "/Users/leon/Documents/HTW/Info2/HTW-Info2/src/ScrabbleCheaterBasic/newWordList";
        try {
            FileReader fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);
            fillHashTable(wordSize);
        } catch (IOException e) {
            System.out.println("Error reading the file!");
            e.printStackTrace();
        } finally {
            closeReader();
        }
    }

    private void fillHashTable() throws IOException {
        String line;
        int count = 0;

        while ((line = reader.readLine()) != null) {
            count++;
            table.insert(line);
        }

        System.out.println(count);
    }

    private void fillHashTable(int size) throws IOException {
        String line;
        int count = 0;

        while ((line = reader.readLine()) != null) {
            if (line.length() == size) {
                count++;
                table.insert(line);
            }
        }

        System.out.println(count);
    }

    private void closeReader() {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error closing the reader!");
            e.printStackTrace();
        }
    }

    public HashTable getTable() {
        return table;
    }

    public void printTable() {
        table.printHashTable();
    }
}
