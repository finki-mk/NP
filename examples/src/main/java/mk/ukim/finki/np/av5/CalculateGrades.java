package mk.ukim.finki.np.av5;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CalculateGrades {
    static final String FILE_NAME = "examples/data/grades.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = null;
        try {
            students = loadStudents(new FileInputStream(FILE_NAME));
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }
        Collections.sort(students);
        for (Student student : students) {
            System.out.println(String.format("%s %s %c", student.getFirstName(),
                    student.getLastName(), student.getGrade()));
        }
        int[] gradesDistribution = findGradeDistribution(students);
        String outputFile = scanner.nextLine();

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(outputFile))) {
            for (Student student : students) {
                printWriter.println(student);
            }
            for (int i = 0; i < gradesDistribution.length; ++i) {
                printWriter.println(String.format("%c : %d", 'A' + i, gradesDistribution[i]));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    static List<Student> loadStudents(InputStream inputStream) throws IOException {
        ArrayList<Student> students = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                Student student = Student.fromString(line);
                students.add(student);
            }
        }
        return students;
    }

    static int[] findGradeDistribution(List<Student> students) {
        int[] gradesDistribution = new int[5];
        for (Student student : students) {
            gradesDistribution[student.getGrade() - 'A']++;
        }
        return gradesDistribution;
    }
}

