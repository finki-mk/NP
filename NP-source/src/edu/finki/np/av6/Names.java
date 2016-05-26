package edu.finki.np.av6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Names {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileInputStream("girlnames.txt"));
		Map<String, Name> names = new HashMap<String, Name>();
		readNames(scanner, names, true);
		scanner.close();
		scanner = new Scanner(new FileInputStream("boynames.txt"));
		readNames(scanner, names, false);
		scanner.close();
		for(String key : names.keySet()) {
			Name nameObj = names.get(key);
			if(nameObj.male > 0) {
				System.out.printf("%s %d %d %c\n",
						nameObj.name, nameObj.female, nameObj.male,
						nameObj.male > nameObj.female ? 'M' : 'F');
			}
		}
	}
	
	static void readNames(Scanner scanner, 
			Map<String, Name> names, boolean isFemale) {
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] parts = line.split("\\s+");
			String name = parts[0];
			int count = Integer.parseInt(parts[1]);
			Name nameObject;
			if(isFemale) {
				nameObject = new Name(name, 0, count);
				names.put(name, nameObject);
			} else {
				nameObject = names.get(name);
				if(nameObject != null) {
					nameObject.male = count;
				}
			}
			
		}
	}
	
	
	
	
}
class Name {
	String name;
	int male;
	int female;
	
	public Name(String name, int male, int female) {
		this.name = name;
		this.male = male;
		this.female = female;
	}
	
	
	
}

















