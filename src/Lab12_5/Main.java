package Lab12_5;

public class Main {
    public static void main(String[] args) {
        HashTable table;
        Dictionary dict = new Dictionary(75000);
        table = dict.getHashTable();

        dict.printTable();

        dict.printStatistics();
        dict.scrabbleCheater("claimed");

    }
}

