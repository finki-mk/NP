package mk.ukim.finki.np.av5;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CalculateGrades {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();
        BufferedReader fileReader = null;
        ArrayList<Student> students = new ArrayList<Student>();
        int[] gradesDistribution = new int[5];
        try {
            fileReader = new BufferedReader(new FileReader(filename));
            String line = null;
            while ((line = fileReader.readLine()) != null) {
                String[] parts = line.split(":");
                int exam1 = Integer.parseInt(parts[2]);
                int exam2 = Integer.parseInt(parts[3]);
                int exam3 = Integer.parseInt(parts[4]);
                Student s = new Student(parts[0], parts[1], exam1, exam2, exam3);
                students.add(s);
                if (s.getGrade() == 'A') {
                    gradesDistribution[0]++;
                } else if (s.getGrade() == 'B') {
                    gradesDistribution[1]++;
                } else if (s.getGrade() == 'C') {
                    gradesDistribution[2]++;
                } else if (s.getGrade() == 'D') {
                    gradesDistribution[3]++;
                } else {
                    gradesDistribution[4]++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(students);
        for (Student s : students) {
            System.out.println(String.format("%s %s %c", s.getFirstName(),
                    s.getLastName(), s.getGrade()));
        }
        String outputFile = scanner.nextLine();
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileWriter(outputFile));
            for (Student s : students) {
                printWriter.println(s);
            }
            printWriter.println(String.format("A : %d", gradesDistribution[0]));
            printWriter.println(String.format("B : %d", gradesDistribution[1]));
            printWriter.println(String.format("C : %d", gradesDistribution[2]));
            printWriter.println(String.format("D : %d", gradesDistribution[3]));
            printWriter.println(String.format("F : %d", gradesDistribution[4]));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
        }

    }
}

