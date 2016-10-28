package mk.ukim.finki.np.av5;

import java.io.*;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Argument: examples/data/persons.txt
 */
public class FindOldest {
    public static void main(String[] args) {
        try {
            findWithScanner(new FileInputStream(args[0]));
            //findWithStream(new FileInputStream(args[0]));
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Imperative solution using {@link Scanner}
     */
    static void findWithScanner(InputStream inputStream) {
        try (Scanner scanner = new Scanner(inputStream)) {
            String oldestName = null;
            int maxAge = Integer.MIN_VALUE;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\s+");
                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                if (age > maxAge) {
                    maxAge = age;
                    oldestName = name;
                }
            }
            System.out.println("Name: " + oldestName);
            System.out.println("Age: " + maxAge);
        }
    }

    /**
     * Functional solution using {@link BufferedReader} and map-reduce
     */
    static void findWithStream(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String[] max = reader.lines()
                .map(line -> line.split("\\s+"))
                .reduce(new String[]{}, (left, right) -> {
                    if (left.length == 0) return right;
                    if (right.length == 0) return left;
                    int leftAge = Integer.parseInt(left[1]);
                    int rightAge = Integer.parseInt(right[1]);
                    return leftAge > rightAge ? left : right;
                });
        System.out.println("Name: " + max[0]);
        System.out.println("Age: " + max[1]);
    }
}
