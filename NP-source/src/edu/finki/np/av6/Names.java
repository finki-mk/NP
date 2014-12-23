package edu.finki.np.av6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import edu.finki.np.av6.CountComparator.CompareType;

public class Names {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileInputStream("girlnames.txt"));
		Map<String, Integer> names = new HashMap<String, Integer>();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] parts = line.split(" ");
			String name = parts[0];
			names.put(name, Integer.parseInt(parts[1]));
		}
		scanner = new Scanner(new FileInputStream("boynames.txt"));
		List<Name> duplicates = new ArrayList<Name>();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] parts = line.split(" ");
			String name = parts[0];
			Integer girlsCount = names.get(name);
			if (girlsCount != null) {
				Name nameObj = new Name(name, Integer.parseInt(parts[1]),
						girlsCount);
				duplicates.add(nameObj);
			}
		}
		Collections.sort(duplicates, new CountComparator(CompareType.GIRLS));
		for (Name name : duplicates) {
			System.out.println(name);
		}
	}
}

class CountComparator implements Comparator<Name> {
	enum CompareType {
		GIRLS,
		BOYS
	}
	CompareType type;
	public CountComparator(CompareType type) {
		this.type = type;
	}

	@Override
	public int compare(Name o1, Name o2) {
		if(type == CompareType.GIRLS) {
			return Integer.compare(o1.getGirlsCount(), o2.getGirlsCount());
		} else {
			return Integer.compare(o1.getBoysCount(), o2.getBoysCount());
		}
	}
}


class RatioComparator implements Comparator<Name> {

	@Override
	public int compare(Name o1, Name o2) {
		return Double.compare(o1.getRatio(), o2.getRatio());
	}
}

class Name implements Comparable<Name> {
	private String name;
	private int boysCount;
	private int girlsCount;

	public Name(String name, int boysCount, int girlsCount) {
		this.name = name;
		this.boysCount = boysCount;
		this.girlsCount = girlsCount;
	}

	@Override
	public String toString() {
		return String.format("%-15s%10d%10d%7.2f", name, boysCount, girlsCount,
				boysCount * 1.0 / girlsCount);
	}

	@Override
	public int compareTo(Name o) {
		return name.compareTo(o.name);
	}

	public String getName() {
		return name;
	}

	public int getBoysCount() {
		return boysCount;
	}

	public int getGirlsCount() {
		return girlsCount;
	}
	
	public double getRatio() {
		return boysCount * 1. / girlsCount;
	}

}
