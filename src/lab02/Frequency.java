package lab02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Frequency {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub


        getFrequency();


    }

    private static void getFrequency() throws IOException {

        String filename = "cano.txt";
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        HashMap<Character, Integer> countMap = new HashMap<Character, Integer>();
        File file = new File("frequencies.txt");
        FileWriter fw = new FileWriter("frequencies.txt");

        if(br.read() != -1) {

            int character;

            while(br.ready()) {

                character = br.read();
                while (character =='\r') {
                    character = br.read();
                }
                char c = (char) character;

                if(countMap.containsKey(c)) {
                    countMap.put((char) c, countMap.get(c) + 1);
                }else {
                    countMap.put((char) c, 1);
                }
            }
        }else {
            System.out.println("The file is empty");
        }

        file.createNewFile();


        countMap.entrySet().stream().forEach((entry) -> {

            String content = "\n" + entry.getKey() + ": " + "*".repeat(entry.getValue() / 1000);

            try {
                fw.write(content, 0, content.length());
                fw.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

    }

    private char returnChar(BufferedReader br) throws IOException {

        int result = br.read();

        while (result =='\r') {
            result = br.read();
        }

        return (char) result;

    }

}
