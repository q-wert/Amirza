import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class WordGenerator {
    private static final Set<String> dictionary = new HashSet<>();

    public static void main(String[] args) {
        getDataBase();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a set of letters: ");
        String letters = scanner.nextLine();

        List<String> words = new ArrayList<>();

        ArrayList<String> sub = subset(letters, 3);
        for (String s : sub) {
            System.out.println(s + "::");
            words.addAll(generateWords(letters));
        }

        System.out.println("Words generated from the letters: ");
        for (String word : words) {
            System.out.println(word);
        }
    }


    public static ArrayList<String> subset(String letters, int minSize) {
//        int len = letters.length();
//        int temp = 0;
//        //Total possible subsets for string of size n is n*(n+1)/2
//        String arr[] = new String[len * (len + 1) / 2];
//
//        //This loop maintains the starting character
//        for (int i = 0; i < len; i++) {
//            //This loop adds the next character every iteration for the subset to form and add it to the array
//            for (int j = i; j < len; j++) {
//                arr[temp] = letters.substring(i, j + 1);
//                temp++;
//            }
//        }
//        ArrayList<String> subset = new ArrayList<>();
//        for (String s : arr) {
//            if (s.length()-1 >= minSize) subset.add(s);
//        }
//        return subset;
        int n = letters.length();
        ArrayList<String> subset = new ArrayList<>();
        // Run a loop from 0 to 2^n
        for (int i = 0; i < (1 << n); i++) {
            StringBuilder s = new StringBuilder();
            int m = 1; // m is used to check set bit in binary representation.
            // Print current subset
            for (int j = 0; j < n; j++) {
                if ((i & m) > 0) {
                    s.append(letters.charAt(j));
                }
                m = m << 1;
            }

            if (s.length() >= minSize) {
                subset.add(s.toString());
            }
        }
        return subset;
    }

    public static List<String> generateWords(String letters) {
        List<String> words = new ArrayList<>();
        permute("", letters, words);
        return words;
    }

    private static void permute(String prefix, String letters, List<String> words) {
        int n = letters.length();
        if (n == 0 && dictionary.contains(prefix)) {
            words.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permute(prefix + letters.charAt(i), letters.substring(0, i) + letters.substring(i + 1, n), words);
            }
        }
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
        while (scanDoc.hasNext()) {
            str = scanDoc.next();
            dictionary.add(str);
//            System.out.println(str);
//            System.out.println(wordsChar.get(wordsChar.size()-1));
        }

    }
}