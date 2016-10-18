package mk.ukim.finki.np.av5;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * File path for test: examples/data/grades.txt
 */
public class CalculateGradesImproved {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();
        List<Student> students = loadStudents(filename);
        students.stream()
                .sorted()
                .forEach(each -> System.out.println(String.format("%s %s %c",
                        each.getFirstName(), each.getLastName(), each.getGrade())));
        int[] distribution = findDistribution(students);
        printDistribution(distribution);
        filename = scanner.nextLine();
        writeToFile(students, filename);
    }

    static List<Student> loadStudents(String fileName) {
        try {
            return Files.lines(Paths.get(fileName))
                    .map(Student::fromString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    static int[] findDistribution(List<Student> students) {
        return students.stream()
                .map(Student::getGrade)
                .collect(() -> new int[6],
                        (ints, character) -> ints[character - 'A']++,
                        (left, right) -> Arrays.setAll(left, i -> left[i] + right[i])
                );
    }

    static void printDistribution(int[] distribution) {
        IntStream.range(0, distribution.length)
                .mapToObj(i -> String.format("%c : %d", 'A' + i, distribution[i]))
                .forEach(System.out::println);
    }

    static void writeToFile(List<Student> students, String fileName) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName), StandardOpenOption.CREATE)) {
            students.forEach(each -> {
                try {
                    writer.write(each.toString());
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            });
        } catch (IOException e) {
            System.exit(-1);
        }
    }
}

