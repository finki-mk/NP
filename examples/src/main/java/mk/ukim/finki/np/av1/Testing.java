package mk.ukim.finki.np.av1;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Some stream tests
 */
public class Testing {
    static final long RANGE = 2_000_000_000;
    static final long SMALL_RANGE = 5;

    static final List<String> words = Arrays.asList("word1", "hello", "a", "b");

    public static void main(String[] args) {
        /*//System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");
        long start = System.currentTimeMillis();
        long result = LongStream.range(1, RANGE)
                .parallel()
                //.filter(Testing::divBy5)
                .reduce(1, (a, b) -> a * b);
        System.out.println("Time: " + (System.currentTimeMillis() - start));
        System.out.println(result);
        //System.out.println(unSort("_*&b x  This is a normal sentence, with 7 words.  y "));
        //System.out.printf(unSort("aA,.-A-ab?Aa"));

        *//*StringJoiner joiner = new StringJoiner("\", \"", "[\"", "\"]");
        for(String w : words) {
            //joiner.add(String.format("\"%s\"",w));
            //joiner.add(w);
        }
        System.out.println(joiner.toString());*//*

       // System.out.println(words.stream().collect(Collectors.joining(", ", "[", "]")));*/
        findSum(1, 10);
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

    static void findSum(int a, int b) {
        int sum = IntStream.range(a, b)
                .map(x -> x)
                .reduce(1, (left, right) -> left * right);
        System.out.println(sum);
    }
}
