// Java Program to illustrate reading from Text File
// using Scanner Class
import java.io.File;
import java.util.Scanner;
import java.util.*;
public class Main
{
    public static String start = "CLAY";
    public static String end = "GOLD";
    public static int len;

    public static void main(String[] args) throws Exception
    {
        // pass the path to the file as a parameter
        ArrayList <String> dictionary = fetchDictionary(start);
        System.out.println((dictionary.size()));

        ArrayList <Integer> neighbours = nearestWords("CLAY", dictionary);
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

    public static ArrayList< Integer> nearestWords( String word, ArrayList<String> dictionary){
        ArrayList<Integer> neighbours = new ArrayList<Integer>();

        for (int i = 0; i <  dictionary.size(); i++) {
            if( match(word, dictionary.get(i)) ){
                neighbours.add(i);
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

    static boolean isAdjacent(String a, String b){
        int count=0;
        for(int i=0;i<a.length();i++)
        {
            if(a.charAt(i)!=b.charAt(i)) count++;
        }
        return count==1;
    }

    static int shorterLength(String src,String dest,ArrayList<String> dict){
        Queue<String> q1=new LinkedList<>();
        Queue<Integer> q2=new LinkedList<>();
        q1.add(src);
        q2.add(1);
        while(!q1.isEmpty()){
            String node = q1.peek();
            int length = q2.peek();
            q1.remove();
            q2.remove();
            for(int i=0;i<dict.size();i++){
                String current_word=dict.get(i);
                if(isAdjacent(node,current_word)){
                    q1.add(current_word);
                    q2.add(length+1);
                    dict.remove(current_word);
                }
                if(current_word==dest)
                    return q2.peek();
            }
        }
        return 0;
    }

}
