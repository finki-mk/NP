package edu.finki.np.av5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BenfordLawTest {
	/*
	 * librarybooks.txt (* Library holdings (# of books in each library), *) (*
	 * collected by Christian Ayotte. *) (* Labels not available. *)
	 * 
	 * livejournal.txt (* LiveJournal data collected by Shirley Man from *) (*
	 * http://www.livejournal.com/stats/stats.txt *) (* Number of new accounts
	 * on LiveJournal, *) (* day by day from 2000/1/1 to 2005/2/28 *) (*
	 * Individual data are NOT labelled. *)
	 * 
	 * sunspots.txt (* Sunspot data collected by Robin McQuinn from *) (*
	 * http://sidc.oma.be/html/sunspot.html *)
	 */

	static int firstDigit(int n) {
		if (n <= 9)
			return n;
		else
			return firstDigit(n / 10);
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(System.in);
		String fileName = scanner.nextLine();
		Scanner fileScanner = new Scanner(new FileInputStream(fileName));
		int[] digits = new int[10];
		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
			if (line == null || line.isEmpty())
				continue;
			String[] parts = line.split(" ");
			String num = parts[parts.length - 1];
			char d = num.charAt(0);
			int number = d - '0';
			System.out.println("N: " + number);

			digits[number]++;
			// int number = Integer.parseInt(line);
			// int d = firstDigit(n);
		}
		fileScanner.close();
		for (int i = 1; i < digits.length; ++i) {
			System.out.printf("%d : %d\n", i, digits[i]);
		}

	}
}
