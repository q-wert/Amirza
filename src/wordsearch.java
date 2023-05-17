/**
 * wordsearch.java
 * A small java program that works as  a basic word search generator.
 * Takes in words to be 'hidden' creates and fills array with the words
 * and a mix of random characters to make them harder to find.
 *
 * @author MHowse.
 **/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

//todo duplicate in search array is omitted (اسباب is being treated as اسبا)
//todo sort by alphabetical order?
public class wordsearch {

    /**
     * Datafield declarations.
     */
    private static int width;
    private static int length;
    private static int wordCount;
    private static ArrayList<String> words;
    private static ArrayList<char[]> wordsChar;
    private static int[] positions;
    private static char[][] search;
    private static String input;
    /**
     * Int counter to count the errors.
     */
    private static int errorcount = 0;

    /*Main method*/
    public static void main(String[] args) {
//        ArrayList<Character> chartest = new ArrayList<>();
//        chartest.add('a');
//        chartest.add('e');
//        chartest.add('i');
//        chartest.add('o');
//        chartest.add('u');
//        chartest.add('a');
//        chartest.add('i');
//        chartest.add('e');
//        System.out.println(countOf(chartest,'a'));
        run();
    }

    public static void run() {

        words = new ArrayList<String>();
        wordsChar = new ArrayList<>();
        getDataBase();
//        char[] letters = getInput();
        Scanner scn = new Scanner(System.in);
        String inp = scn.next();
        while (!inp.equals("end")){
            char[] letters =  inp.toCharArray();

            for (String s:findWords(letters,3)) {
                System.out.println(s);
            }
            for (String s:findWords(letters,4)) {
                System.out.println(s);
            }

            for (String s:findWords(letters,5)) {
                System.out.println(s);
            }
            for (String s:findWords(letters,6)) {
                System.out.println(s);
            }
            for (String s:findWords(letters,7)) {
                System.out.println(s);
            }
            inp = scn.next();
        }

    }

    private static char[] getInput() {
        Scanner scan = new Scanner(System.in);
        System.out.println("type input");
        return scan.next().toCharArray();
    }

    private static HashSet<String> findWords(char[] array,int size) {
        HashSet<String> results = new HashSet<>();

        for (char[] w : wordsChar) {
            ArrayList<Character> arr = new ArrayList<>();
            for (Character l : array) {
                arr.add(l);
            }
            ArrayList<Character> searched = new ArrayList<>();

            int cnt = 0;
            for (int j = 0; j < arr.size(); j++) {
                boolean hasL = false;
                for (int i = 0; i < w.length; i++) {
                    if (w[i] == arr.get(j) && countOf(searched,w[i])<countOf(arr,w[i])) {
//                        System.out.println(w[i]+" "+countOf(searched,w[i])+"<"+countOf(arr,w[i]));
                        searched.add(w[i]);
                        hasL = true;
                        break;
                    }
                }
                if (hasL) {
                    cnt++;
                }
            }
            if (cnt == size && w.length <= cnt) {
                String res = "";
                for (char wl : w) {
                    res = res + wl;
                }
//                System.out.println(res);
                results.add(res);
            }
        }
        return results;
    }

    public static void getDataBase() {
        Scanner scanDoc;
        try {
            scanDoc = new Scanner(new FileReader(
                    "F:\\amirza.txt"));
//                    "F:\\amirzatest.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String str;
        wordCount = 0;
        while (scanDoc.hasNext()) {
            str = scanDoc.next();
            words.add(str);
            wordsChar.add(str.toCharArray());
//            System.out.println(wordsChar.get(wordsChar.size()-1));
        }

    }

    public static int countOf(ArrayList<Character> hay,Character needle){
        int cnt = 0;
        for (Character s:hay) {
            if (s.equals(needle)) cnt++;
        }
        return cnt;
    }

}//end class