package edu.finki.np.ex3;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class BodyFormTest {
	public static void main(String[] args) {
		BodyForm bodyForm = new BodyForm();
		bodyForm.readData(System.in);
		System.out.println("BY MAX");
		bodyForm.printByWeight(System.out, 1);
		System.out.println("BY AVG");
		bodyForm.printByWeight(System.out, 2);
	}
}

class BodyForm {
	private ArrayList<Person> persons;

	public BodyForm() {
		persons = new ArrayList<Person>();
	}

	void readData(InputStream inputStream) {
		Scanner scanner = new Scanner(inputStream);
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] parts = line.split(" ");
			String name = parts[0];
			Person person = new Person(name);
			for (int i = 1; i < parts.length; ++i) {
				person.addMeasurment(Float.parseFloat(parts[i]));
			}
            persons.add(person);
		}
		scanner.close();
	}

	void printByWeight(OutputStream outputStream, int type) {
		PrintWriter pw = new PrintWriter(outputStream);
		if (type == 1) {
			Collections.sort(persons, new MaxComparator());
		}
		if (type == 2) {
			Collections.sort(persons, new AvgComparator());
		}		
		for (Person person : persons) {
			pw.println(person);
		}
		pw.flush();
	}

}

class Person {
	String name;
	ArrayList<Float> measurements;
	float max;
	float sum;
	float avg;

	public Person(String name) {
		this.name = name;
		measurements = new ArrayList<Float>();
		sum = 0;
		max = Float.MIN_VALUE;
	}

	public void addMeasurment(float weightMeasurement) {
		measurements.add(weightMeasurement);
		sum += weightMeasurement;
		if (weightMeasurement > max) {
			max = weightMeasurement;
		}
		avg = sum / measurements.size();
	}


	@Override
	public String toString() {		
		return String.format("%s MAX : %.1f kg, AVG : %.1f kg", name, max, avg);
	}
}

class MaxComparator implements Comparator<Person> {
	@Override
	public int compare(Person p1, Person p2) {
        if(p1.max < p2.max) return -1;
        else if(p1.max > p2.max) return 1;
        else return 0;
	}
}

class AvgComparator implements Comparator<Person> {
	@Override
	public int compare(Person p1, Person p2) {
		if(p1.avg < p2.avg) return -1;
        else if(p1.avg > p2.avg) return 1;
        else return 0;
	}
}