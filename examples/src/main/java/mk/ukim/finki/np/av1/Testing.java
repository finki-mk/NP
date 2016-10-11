package mk.ukim.finki.np.av1;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Some stream tests
 */
public class Testing {
    static final long RANGE = 1_000_000_000;
    static final long SMALL_RANGE = 5;

    public static void main(String[] args) {
        //System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");
        /*long start = System.currentTimeMillis();
        long result = LongStream.range(1, SMALL_RANGE)
                //.parallel()
                //.filter(Testing::divBy5)
                .reduce(1, (a, b) -> a * b);
        System.out.println(result);
        System.out.println("Time: " + (System.currentTimeMillis() - start));*/
        System.out.println(unSort("_*&b x  This is a normal sentence, with 7 words.  y "));
        //System.out.printf(unSort("aA,.-A-ab?Aa"));
    }

    static boolean divBy5(long x) {
        return divisible(x, 5);
    }

    static boolean divisible(long x, int n) {
        return x % n == 0;
    }

    static String unSort(String input) {
        String[] words = input.split("[^a-zA-Z]+");
        String[] separators = input.split("[a-zA-Z]+");
        List<String> filtered = Arrays.stream(separators)
                .filter(s -> s.length() > 0)
                .collect(Collectors.toList());
        Arrays.sort(words);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.length; ++i) {
            result.append(words[i]);
            if (i < filtered.size()) {
                result.append(filtered.get(i));
            }
        }
        return result.toString();
    }
}
