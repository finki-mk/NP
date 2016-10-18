package mk.ukim.finki.np.av5;

import java.io.*;
import java.util.Comparator;
import java.util.stream.IntStream;

public class LongestPalindrome {
    public static final String FILENAME = "examples/data/words.txt";

    public static void main(String[] args) {
        try {
            //System.out.println(findLongest(new FileInputStream(FILENAME)));
            System.out.println(findLongestFunc(new FileInputStream(FILENAME)));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Standard imperative solution, reading line by line
     *
     * @param inputStream The input stream with the data
     * @return The longest palindrome string
     * @throws IOException
     */
    static String findLongest(InputStream inputStream) throws IOException {
        String longest = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String word;
            while ((word = reader.readLine()) != null) {
                if (isPalindrome(word)) {
                    if (longest == null) {
                        longest = word;
                    } else {
                        if (word.length() >= longest.length()) {
                            longest = word;
                        }
                    }
                }
            }
        }
        return longest;
    }

    /**
     * Functional solution with following steps: 1. filter palindromes, 2. get max by length (longest)
     *
     * @param inputStream The input stream with data
     * @return The longest palindrome string
     * @throws IOException
     */
    static String findLongestFunc(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines()
                    .filter(LongestPalindrome::isPalindromeFunc)
                    .max(Comparator.comparing(String::length))
                    .orElse(null);
        }
    }

    static boolean isPalindrome(String word) {
        int len = word.length();
        for (int i = 0; i < len / 2; i++) {
            if (word.charAt(i) != word.charAt(len - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    static boolean isPalindromeFunc(String word) {
        return IntStream.range(0, word.length() / 2)
                .allMatch(i -> word.charAt(i) == word.charAt(word.length() - 1 - i));
    }
}
