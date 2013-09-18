package edu.finki.np.av5;

import java.io.BufferedReader;
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
	
	public static void main(String[] args) {
		System.out.println("Enter filename: ");
		Scanner scanner = new Scanner(System.in);
		String filename = scanner.nextLine();
		BufferedReader fileReader = null;
		int[] counts = new int[10];
		int total = 0;
		try {
			fileReader = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = fileReader.readLine()) != null) {
				if(line.length() > 0) {
					String[] parts = line.split(" ");
					int num;
					if(parts.length > 0) {
						num = Integer.parseInt(parts[parts.length - 1]);
					} else {
						num = Integer.parseInt(line); 
					}
					int firstDigit = firstDigit(num);
					counts[firstDigit]++;
					total++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i = 1; i < counts.length; i++) {
			System.out.printf("%d : %d : %.2f%%\n", i, counts[i], counts[i] * 100. / total);
		}
	}
	
	static int firstDigit(int num) {
		while(num >= 10) {
			num /= 10;
		}
		return num;
	}
}
