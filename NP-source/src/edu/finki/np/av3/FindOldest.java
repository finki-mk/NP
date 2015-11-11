package edu.finki.np.av3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FindOldest {
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(new FileInputStream(args[0]));
			int maxAge = 0;
			String maxName = null;
			while (scanner.hasNextLine()) {
				/*String name = scanner.next();
				int age = scanner.nextInt();*/
				String line = scanner.nextLine();
				String[] parts = line.split("\\s+");
				int age = Integer.parseInt(parts[1]);
				String name = parts[0];
				if (age > maxAge) {
					maxName = name;
					maxAge = age;
				}
			}
			System.out.println(maxName);
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
