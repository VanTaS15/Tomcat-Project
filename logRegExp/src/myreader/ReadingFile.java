package myreader;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ReadingFile {

    public static void main(String[] args) {
        FileReader myFile = null;
        BufferedReader buff = null;
        StringTokenizer matcher;

        try {
            myFile = new FileReader("alf.log");
            buff = new BufferedReader(myFile);

            while (true) {
                String line = buff.readLine();
                if (line == null) {
                    break;
                }
                matcher = new StringTokenizer(line);
                System.out.println("tokens = " + matcher.countTokens());
                System.out.println(line);
                System.out.println("1: " + matcher.nextToken());
                System.out.println("2: " + matcher.nextToken());
                System.out.println("3: " + matcher.nextToken());
                System.out.println("4: " + matcher.nextToken());
                System.out.println("5: " + matcher.nextToken());
                System.out.println("6: " + matcher.nextToken());
                System.out.println("7: " + matcher.nextToken());
                System.out.println("8: " + matcher.nextToken());
                System.out.println("9: " + matcher.nextToken());   
//                System.out.println("10: " + matcher.nextToken("]"));
//                matcher.nextToken(" ");
//                System.out.println("11: " + matcher.nextToken("\"")); 

                System.out.println("10: " + matcher.nextToken());
                                System.out.println("11: " + matcher.nextToken()); 

                System.out.println("12: " + matcher.nextToken());
                System.out.println("13: " + matcher.nextToken(" ")); 
                System.out.println("14: " + matcher.nextToken());
                System.out.println("15: " + matcher.nextToken()); 
                System.out.println("16: " + matcher.nextToken());
                System.out.println("17: " + matcher.nextToken());
                System.out.println("18: " + matcher.nextToken());  
                System.out.println("19: " + matcher.nextToken());  
                System.out.println("20: " + matcher.nextToken());                
                System.out.println("21: " + matcher.nextToken());    
               
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                buff.close();
                myFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
