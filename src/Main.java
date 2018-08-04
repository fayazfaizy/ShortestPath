// Java Program to illustrate reading from Text File
// using Scanner Class
import java.io.File;
import java.util.Scanner;
import java.util.*;
public class Main
{
    public static String source = "CLAY";
    public static String destination = "GOLD";
    public static int len;

    public static void main(String[] args) throws Exception
    {
        // pass the path to the file as a parameter
        ArrayList <String> dictionary = fetchDictionary(source);
        System.out.println((dictionary.size()));

        ArrayList <String> neighbours = nearestWords("CLAY", dictionary);
        System.out.println((neighbours));

        System.out.println(fetchPosition("AAHS", dictionary));


    }

    public static ArrayList <String> fetchDictionary( String word) throws Exception{
        File file =
                new File("/Users/mmohammadrafi/Downloads/sowpods.txt");
        Scanner sc = new Scanner(file);

        len = word.length();

        ArrayList <String> dictionary = new ArrayList<String>();

        while (sc.hasNextLine()){
            String temp = sc.nextLine();
            if( temp.length() == len){
                dictionary.add(temp);
            }
        }

        return dictionary;
    }

    public static ArrayList< String> nearestWords( String word, ArrayList<String> dictionary){
        ArrayList<String> neighbours = new ArrayList<String>();

        int max = score(word);
        for ( String currentWord : dictionary) {
            if( match(word, currentWord) && max < score(currentWord)){
                neighbours.add(currentWord);
            }
        }
        return neighbours;
    }

    public static Boolean match ( String w1, String w2){
        int mismatch = 0;

        for(int i = 0; i < w1.length(); i++ ){
            if( w1.charAt(i) != w2.charAt(i)){
                mismatch++;
            }
        }

        return (mismatch == 1 );
    }

    public static int fetchPosition(String word, ArrayList<String> dictionary){

        for( int i = 0; i < dictionary.size(); i++){
            if( dictionary.get(i).equals(word)){
                return i;
            }
        }
        return -1;
    }

    public static int score( String word){
        int count = 0;

        for( int i = 0; i < word.length(); i++){
            if(word.charAt(i) == destination.charAt(i)){
                count++;
            }
        }
        return count;
    }


}
