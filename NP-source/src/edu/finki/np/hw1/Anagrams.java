package edu.finki.np.hw1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Anagrams {
	public static void findAll(InputStream inputStream) {
		Scanner fileScanner = null;
		Map<String, Set<String>> anagrams = new HashMap<String, Set<String>>();
		fileScanner = new Scanner(inputStream);
		while (fileScanner.hasNextLine()) {
			String word = fileScanner.nextLine();
			char[] w = word.toCharArray();
			Arrays.sort(w);
			String rep = new String(w);
			if (anagrams.containsKey(rep)) {
				Set<String> words = anagrams.get(rep);
				words.add(word);
			} else {
				Set<String> words = new TreeSet<String>();
				words.add(word);
				anagrams.put(rep, words);
			}
		}
		fileScanner.close();
		Iterator<Entry<String, Set<String>>> it = anagrams.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<String, Set<String>> entry = it.next();
			if (entry.getValue().size() >= 5) {
				Iterator<String> setIt = entry.getValue().iterator();
				while (setIt.hasNext()) {
					System.out.print(setIt.next());
					if(setIt.hasNext()) {
						System.out.print(" ");
					}
				}
				System.out.println();
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		findAll(new FileInputStream("anagrams.txt"));
	}
}
