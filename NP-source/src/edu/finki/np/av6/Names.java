package edu.finki.np.av6;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Names {
	public static void main(String[] args) {
		Scanner fileScanner = null;
		Map<String, Name> names = new HashMap<String, Name>();
		List<Name> repeating = new ArrayList<Name>();
		try {
			fileScanner = new Scanner(new FileReader("girlnames.txt"));
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] parts = line.split(" ");
				String name = parts[0];
				int count = Integer.parseInt(parts[1]);
				Name nameObject = new Name(name, count, 0);
				names.put(name, nameObject);
			}
			fileScanner.close();
			fileScanner = new Scanner(new FileReader("boynames.txt"));
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] parts = line.split(" ");
				String name = parts[0];
				int count = Integer.parseInt(parts[1]);
				if (names.containsKey(name)) {
					Name nameObject = names.get(name);
					nameObject.setCountBoys(count);
					repeating.add(nameObject);
				}
			}
			fileScanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Collections.sort(repeating);
		Collections.sort(repeating, new NameComparator());
		for (Name name : repeating) {
			System.out.println(name);
		}
		System.out.println("Sorted by boys count");
		Collections.sort(repeating, new BoysNamesCountComparator());
		for (Name name : repeating) {
			System.out.println(name);
		}
	}
}

class Name implements Comparable<Name> {
	String name;
	int countGirls;
	int countBoys;

	public Name(String name, int countGirls, int countBoys) {
		this.name = name;
		this.countGirls = countGirls;
		this.countBoys = countBoys;
	}

	public void setCountBoys(int countBoys) {
		this.countBoys = countBoys;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.format("%s %d %d", name, countGirls, countBoys);
	}

	@Override
	public int compareTo(Name n) {
		//return this.name.compareTo(n.name);
		return this.countGirls - n.countGirls;
	}

}
class NameComparator implements Comparator<Name> {

	@Override
	public int compare(Name n1, Name n2) {
		return n1.name.compareTo(n2.name);
	}
}

class GirlsNamesCountComparator implements Comparator<Name> {

	@Override
	public int compare(Name n1, Name n2) {
		return n1.countGirls - n2.countGirls;
	}
}
class BoysNamesCountComparator implements Comparator<Name> {

	@Override
	public int compare(Name n1, Name n2) {
		return n1.countBoys - n2.countBoys;
	}
}

