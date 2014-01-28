package edu.finki.np.mt1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class WordStat {
	private Map<String, List<String>> words;
	
	public WordStat(String[] w) {
		words = new TreeMap<String, List<String>>();
		for(String word : w) {
			for(int size = 1; size <= 3; ++size) {
				if(word.length() >= size) {
					String prefix = word.substring(0, size);
					List<String> list = words.get(prefix);
					if(list == null) {
						list = new ArrayList<String>();
						words.put(prefix, list);
					}
					list.add(word);
				}
			}
		}
		
	}
	
	public void print() {
		for(String prefix : words.keySet()) {
			List<String> list = words.get(prefix);
			System.out.printf("%s : %d", prefix, list.size());
		}
	}
	
	public void search(String prefix) {
		List<String> list = words.get(prefix);
		System.out.printf("%s : ", prefix);
		for(String s : list) {
			System.out.print(s);
			System.out.print(" ");
		}
	}
}
