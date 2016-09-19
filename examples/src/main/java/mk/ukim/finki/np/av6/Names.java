package mk.ukim.finki.np.av6;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Name name : repeating) {
			System.out.println(name);
		}
	}
}

class Name {
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

}
