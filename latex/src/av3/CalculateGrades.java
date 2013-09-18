package edu.finki.np.av3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

class Student implements Comparable<Student> {
	private String firstName;
	private String lastName;
	private int exam1;
	private int exam2;
	private int exam3;
	private char grade;
	private double total;

	public Student(String firstName, String lastName, int exam1, int exam2,
			int exam3) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.exam1 = exam1;
		this.exam2 = exam2;
		this.exam3 = exam3;
		this.total = exam1 * .25 + exam2 * .3 + exam3 * .45;
		if (this.total >= 91) {
			this.grade = 'A';
		} else if (this.total >= 81) {
			this.grade = 'B';
		} else if (this.total >= 71) {
			this.grade = 'C';
		} else if (this.total >= 61) {
			this.grade = 'D';
		} else {
			this.grade = 'F';
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getExam1() {
		return exam1;
	}

	public int getExam2() {
		return exam2;
	}

	public int getExam3() {
		return exam3;
	}

	public char getGrade() {
		return grade;
	}

	public double getTotal() {
		return total;
	}

	@Override
	public int compareTo(Student o) {
		if (this.total < o.total) {
			return 1;
		} else if (this.total > o.total) {
			return -1;
		} else {
			return 0;
		}
	}

	@Override
	public String toString() {
		return String.format("%s %s %d %d %d %.0f %c", firstName, lastName,
				exam1, exam2, exam3, total, grade);
	}

}
