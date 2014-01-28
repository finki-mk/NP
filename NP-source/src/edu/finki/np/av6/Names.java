package edu.finki.np.av6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Names {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileInputStream("girlnames.txt"));
		Map<String, Integer> names = new TreeMap<String, Integer>();
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] parts = line.split(" ");
			String name = parts[0];
			int countGirs = Integer.parseInt(parts[1]);
			names.put(name, countGirs);
		}
		scanner.close();
		scanner = new Scanner(new FileInputStream("boynames.txt"));
		List<Name> duplicates = new ArrayList<Name>();
		while(scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] parts = line.split(" ");
			String name = parts[0];
			int countBoys = Integer.parseInt(parts[1]);
			if(names.containsKey(name)) {
				Name n = new Name(name, names.get(name), countBoys);
				duplicates.add(n);
			}
		}
		scanner.close();
		Collections.sort(duplicates, new BoysNameCountComparator());
		for(Name name : duplicates) {
			System.out.println(name);
		}
		
	}
}

class Name {
	private String name;
	private int countGirs;
	private int countBoys;
	public Name(String name, int countGirs, int countBoys) {
		this.name = name;
		this.countGirs = countGirs;
		this.countBoys = countBoys;
	}
	
	@Override
	public String toString() {
		return String.format("%s %d %d", name, countGirs, countBoys);
	}
	
	public String getName() {
		return name;
	}
	
	public int getCountGirs() {
		return countGirs;
	}
	
	public int getCountBoys() {
		return countBoys;
	}
	
}

class NameComparator implements Comparator<Name> {

	@Override
	public int compare(Name o1, Name o2) {
		return o1.getName().compareTo(o2.getName());
	}
	
}

class GirlNameCountComparator implements Comparator<Name> {

	@Override
	public int compare(Name o1, Name o2) {
		return o1.getCountGirs() - o2.getCountGirs();
	}
	
}

class BoysNameCountComparator implements Comparator<Name> {

	@Override
	public int compare(Name o1, Name o2) {
		return o2.getCountBoys() - o1.getCountBoys();
	}
	
}
