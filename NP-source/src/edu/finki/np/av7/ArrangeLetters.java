package edu.finki.np.av7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class ArrangeLetters {
	public String arrange(String input) {
		String[] words = input.split(" ");
		List<String> wordsList = new ArrayList<String>();
		for(String w : words) {
			char[] wa = w.toCharArray();
			Arrays.sort(wa);
			wordsList.add(new String(wa));
		}
		Collections.sort(wordsList);
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < wordsList.size(); ++i) {
			result.append(wordsList.get(i));
			if(i != wordsList.size() - 1) {
				result.append(" ");
			}
		}
		return result.toString();
	}
	
	
	public static void main(String[] args) {
		ArrangeLetters al = new ArrangeLetters();
		System.out.println(al.arrange("kO pSk sO"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
