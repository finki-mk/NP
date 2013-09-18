package edu.finki.np.ex2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class UniversityTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.nextLine();
		String[] faculties = new String[n];
		int[] quotes = new int[n];
		for (int i = 0; i < n; ++i) {
			faculties[i] = scanner.next();
			quotes[i] = scanner.nextInt();
			scanner.nextLine();
		}
		University university = new University(faculties, quotes);
		n = scanner.nextInt();
		for (int i = 0; i < n; ++i) {
			String name = scanner.next();
			int points = scanner.nextInt();
			String faculty = scanner.next();
			scanner.nextLine();
			Student student = new Student(name, points);
			try {
				university.addStudent(student, faculty);
			} catch (FacultyFullException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("BY NAME");
		for (String f : faculties) {
			List<Student> res = university.sortedByName(f);
			System.out.println(f);
			for (Student s : res) {
				System.out.println(s);
			}
		}
		System.out.println("BY POINTS");
		for (String f : faculties) {
			List<Student> res = university.sortedByPoints(f);
			System.out.println(f);
			for (Student s : res) {
				System.out.println(s);
			}
		}
	}
}

class Student implements Comparable<Student> {
	private String name;
	private int points;

	public Student(String name, int points) {
		this.name = name;
		this.points = points;
	}

	@Override
	public String toString() {
		return String.format("%s : %d\n", name, points);
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	@Override
	public int compareTo(Student o) {
		if (this.points > o.points)
			return 1;
		else if (this.points < o.points)
			return -1;
		else
			return this.name.compareTo(o.name);
	}

}

class NameComparator implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		return s1.getName().compareTo(s2.getName());
	}

}

class PointsComparator implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		return s1.getPoints() - s2.getPoints();
	}
}

class University {
	private Map<String, Integer> limit;
	private Map<String, List<Student>> students;

	public University(String[] faculties, int[] quotes) {
		limit = new HashMap<String, Integer>();
		students = new HashMap<String, List<Student>>();
		for (int i = 0; i < faculties.length; ++i) {
			limit.put(faculties[i], quotes[i]);
		}
	}

	public void addStudent(Student student, String faculty)
			throws FacultyFullException {
		List<Student> list = students.get(faculty);
		int size = limit.get(faculty);
		if (list == null) {
			list = new ArrayList<Student>();
			list.add(student);
		} else {
			if (list.size() == size) {
				throw new FacultyFullException();
			}
		}
		list.add(student);
		students.put(faculty, list);
	}

	private List<Student> getSorted(String faculty,
			Comparator<Student> comparator) {
		List<Student> list = students.get(faculty);
		Collections.sort(list, comparator);
		return list;
	}

	public List<Student> sortedByPoints(String faculty) {
		return getSorted(faculty, new PointsComparator());
	}

	public List<Student> sortedByName(String faculty) {
		return getSorted(faculty, new NameComparator());
	}

	public List<Student> moreThan(int points) {
		List<Student> result = new ArrayList<Student>();
		for (Entry<String, List<Student>> entry : students.entrySet()) {
			for (Student s : entry.getValue()) {
				if (s.getPoints() > points) {
					result.add(s);
				}
			}
		}
		Collections.sort(result);
		return result;
	}
}

class FacultyFullException extends Exception {
	public FacultyFullException() {
		super("This faculty limit is exceeded.");
	}
}
