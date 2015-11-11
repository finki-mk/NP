package edu.finki.np.av3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CalculateGrades {

	public static void main(String[] args) throws FileNotFoundException {
		readGrades(new FileInputStream("grades.txt"));
	}

	static void readGrades(InputStream is) throws FileNotFoundException {
		List<Student> students = new ArrayList<Student>();
		Scanner scanner = new Scanner(is);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] parts = line.split("\\s+");
			String[] name = parts[0].split(":");
			String[] exams = parts[1].split(":");
			Student student = new Student(name[0], name[1],
					Integer.parseInt(exams[0]), Integer.parseInt(exams[1]),
					Integer.parseInt(exams[2]));
			students.add(student);
		}
		int[] freq = new int[2];
		for (Student s : students) {
			System.out.println(s);
			freq[s.grade - 'A']++;
		}
		System.out.println(Arrays.toString(freq));
		Collections.sort(students);
		OutputStream output = new FileOutputStream("res.txt");
		PrintWriter writer = new PrintWriter(output);
		for (Student s : students) {
			writer.println(s.toFile());
		}
		// writer.flush();
		writer.close();
	}
}

class Student implements Comparable<Student> {
	String firstName;
	String lastName;
	int exam1;
	int exam2;
	int exam3;
	char grade;
	double total;

	public Student(String firstName, String lastName, int exam1, int exam2,
			int exam3) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.exam1 = exam1;
		this.exam2 = exam2;
		this.exam3 = exam3;
		total = .25 * exam1 + .30 * exam2 + .45 * exam3;
		if (total >= 90 && total <= 100) {
			grade = 'A';
		} else {
			grade = 'B';
		}
	}

	public Student(String line) {

	}

	public String toFile() {
		return String.format("%s %s %d %d %d %.1f %c", lastName, firstName,
				exam1, exam2, exam3, total, grade);
	}

	@Override
	public String toString() {
		return String.format("%s %s %c", firstName, lastName, grade);
	}

	@Override
	public int compareTo(Student o) {
		if (this.total < o.total)
			return -1;
		else if (this.total > o.total)
			return 1;
		else {
			int name = this.firstName.compareTo(o.firstName);
			if (name == 0) {
				return this.lastName.compareTo(o.lastName);
			}
			return name;
		}
	}

}
