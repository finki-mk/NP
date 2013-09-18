package edu.finki.np.mt2;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.TreeMap;

public class WordStatsTest {
	public static void main(String[] args) {
		String[] words = readWords();
		WordStats stats = new WordStats(words);
		words = readWords();
		for(String key : words) {
			stats.search(key);
		}
	}

	static String[] readWords() {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.nextLine();
		String[] list = new String[n];
		for(int i = 0; i < n; i++) {
			String word = scanner.nextLine();
			list[i] = word;
		}
		return list;
	}
}

class WordStats {
	Map<String, List<String>> stats;

	public WordStats(String[] words) {
		stats = new TreeMap<String, List<String>>();
        process(words);
	}

	private void process(String[] words) {
		for (String s : words) {
			int len = s.length();
			for (int i = 1; i <= 3 && i <= len; ++i) {
				List<String> list = null;
				String key;
				if(s.length() > i) {
					key = s.substring(0, i);
				} else {
					key = s;
				}
				if (!stats.containsKey(key)) {
					list = new ArrayList<String>();
					list.add(s);
					stats.put(key, list);
				} else {
					list = stats.get(key);
					list.add(s);
				}
			}
		}
	}

	public void print() {
		for (Entry<String, List<String>> entry : stats.entrySet()) {
			System.out.printf("%s : %d", entry.getKey(), entry.getValue()
					.size());
		}
	}

	public void search(String str) {
		List<String> list = stats.get(str);
		System.out.print(str + " : ");
		for (String s : list) {
			System.out.print(s);
			System.out.println(" ");
		}
	}
}
