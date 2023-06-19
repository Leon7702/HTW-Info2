package lab02;

import java.io.*;

public class Histogram {

    public static void main(String[] args) throws IOException {

        String filename = "cano.txt";
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);

        String filename2 = "testP2.txt";
        FileWriter fw = new FileWriter(filename2);
//		BufferedWriter bw = new BufferedWriter(fw);

        File file = new File ("testP2.txt");
        file.createNewFile();

        String input = "We love lab with Sasha :) ";
        int input2 = 5;
        float input3 = (float) 2.2;

        fw.write(input);
        fw.write(input2);
        fw.write((int) input3);

        fw.flush();

        System.out.println("Now reading from the file:");


        if(br.read() != -1) {

            while(br.ready()) {

                System.out.print(returnChar(br));
            }
        }else {
            System.out.println("The file is empty");
        }
    }

    private static char returnChar(BufferedReader br) throws IOException {

        int result = br.read();

        while (result =='\r') {
            result = br.read();
        }

        return (char) result;

    }

}

