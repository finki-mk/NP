package edu.finki.np.ex2;

import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeMap;

public class FrequencyCounterTest {
	public static void main(String[] args) {
		CharacterFrequency cf = new CharacterFrequency();
		Map<Character, Integer> counts = cf.count(System.in);
		System.out.println(counts.size());
		System.out.println("BY CHARACHTER");
		cf.printSortedByCharacter();
		System.out.println("BY COUNT");
		cf.printSortedByCount();
		
	}
}

interface ICount {
	Map<Character, Integer> count(InputStream inputStream);

	void printSortedByCount();

	void printSortedByCharacter();
}

class CharacterFrequency implements ICount {
	private Map<Character, Integer> count;

	@Override
	public Map<Character, Integer> count(InputStream inputStream) {
		count = new TreeMap<Character, Integer>();
		Scanner scanner = new Scanner(inputStream);
		while (scanner.hasNext()) {
			String line =  scanner.nextLine();
			for(char c : line.toCharArray()) {
				Integer i = count.get(c);
				if (i == null) {
					count.put(c, 1);
				} else {
					count.put(c, i + 1);
				}
			}
		}
		return count;
	}

	@Override
	public void printSortedByCount() {
		Map<Integer, Character> sorted = new TreeMap<Integer, Character>(new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				return b- a;
			};
		});
		for (Entry<Character, Integer> entry : count.entrySet()) {
			sorted.put(entry.getValue(), entry.getKey());
		}
		for (Entry<Integer, Character> entry : sorted.entrySet()) {
			System.out.println(String.format("%d : %c", entry.getKey(),
					entry.getValue()));
		}
	}

	@Override
	public void printSortedByCharacter() {
		for (Entry<Character, Integer> entry : count.entrySet()) {
			System.out.println(String.format("%c : %d", entry.getKey(),
					entry.getValue()));
		}
	}

}
