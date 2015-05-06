package edu.finki.np.av5;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class F1Test {
	public static void main(String[] args) {
		F1Race f1Race = new F1Race();
		f1Race.readResults(System.in);
		f1Race.printSorted(System.out);
	}
}

class F1Race {
	List<Driver> drivers;

	public F1Race() {
		drivers = new ArrayList<Driver>();
	}

	public void readResults(InputStream inputStream) {
		Scanner scanner = new Scanner(inputStream);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] parts = line.split(" ");
			Driver driver = new Driver(parts[0], Driver.timeToInt(parts[1]),
					Driver.timeToInt(parts[2]), Driver.timeToInt(parts[3]));
			drivers.add(driver);
		}
	}

	public void printSorted(OutputStream outputStream) {
		Collections.sort(drivers);
		PrintWriter writer = new PrintWriter(outputStream);
		for(Driver driver : drivers) {
			writer.println(driver);
		}
		writer.flush();
	}
}

class Driver implements Comparable<Driver> {
	String name;
	int lap1;
	int lap2;
	int lap3;

	public Driver(String name, int lap1, int lap2, int lap3) {
		this.name = name;
		this.lap1 = lap1;
		this.lap2 = lap2;
		this.lap3 = lap3;
	}

	public static int timeToInt(String time) {
		String[] parts = time.split(":");
		int min = Integer.parseInt(parts[0]);
		int sec = Integer.parseInt(parts[1]);
		int ms = Integer.parseInt(parts[2]);
		return min * 60 * 1000 + sec * 1000 + ms;
	}
	
	public String minTimeString() {
		int minTime = minTime();
		int min = (minTime / 1000) / 60;
		minTime -= (min * 60) * 1000;
		int sec = minTime / 1000;
		int ms = minTime % 1000;
		return String.format("%d:%02d:%03d", min, sec, ms);
	}
	
	public int minTime() {
		return Math.min(lap1, Math.min(lap2, lap3));
	}

	@Override
	public int compareTo(Driver o) {
		return Integer.compare(minTime(), o.minTime());
	}
	
	@Override
	public String toString() {
		return String.format("%-10s%10s", name, minTimeString());
	}

}