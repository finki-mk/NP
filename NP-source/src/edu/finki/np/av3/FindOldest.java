package edu.finki.np.av3;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FindOldest {
	public static void main(String[] args) {
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(new FileReader(args[0]));
			String oldestName = null;
			int maxAge = Integer.MIN_VALUE;
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] parts = line.split(" ");
				String name = parts[0];
				int age = Integer.parseInt(parts[1]);
				if (age > maxAge) {
					maxAge = age;
					oldestName = name;
				}
			}
			fileScanner.close();
			System.out.println("Name: " + oldestName);
			System.out.println("Age: " + maxAge);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
