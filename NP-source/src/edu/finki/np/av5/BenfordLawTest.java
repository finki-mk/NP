package edu.finki.np.av5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BenfordLawTest {
	/*
	 * librarybooks.txt
	 * (* Library holdings (# of books in each library), *)
	   (* collected by Christian Ayotte.                 *)
	   (* Labels not available.                          *)
	   
	   livejournal.txt
	   (* LiveJournal data collected by Shirley Man from *)
	   (* http://www.livejournal.com/stats/stats.txt     *)
	   (* Number of new accounts on LiveJournal,         *)
	   (* day by day from 2000/1/1 to 2005/2/28          *)
	   (* Individual data are NOT labelled.              *)
	   
	   sunspots.txt
	   (* Sunspot data collected by Robin McQuinn from *)
	   (* http://sidc.oma.be/html/sunspot.html         *)

	 */
	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Enter filename: ");
		Scanner scanner = new Scanner(System.in);
		String fileName = scanner.nextLine();
		scanner = new Scanner(new FileInputStream(fileName));
		String line;
		int[] counts = new int[10];
		int totalCount = 0;
		while((line = scanner.nextLine()) != null) {
			String[] parts = line.split(" ");
			System.out.println();
			int number = Integer.parseInt(parts[parts.length - 1]);
			int first = firstDigit(number);
			counts[first]++;
			totalCount++;
		}
		scanner.close();
		for(int i = 1; i <= 9; ++i) {
			System.out.println(String.format("%d : %.3f\n", i, counts[i] * 100. / totalCount));
		}
		
		
	}
	
	static int firstDigit(int a) {
		while(a > 9) {
			a /= 10;
		}
		return a;
	}
}
