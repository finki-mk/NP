package edu.finki.np.av7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class ArrangeLetters {
	public String arrange(String input) {
		String[] words = input.split(" ");
		List<String> result = new ArrayList<String>();
		for(String w : words) {
			char[] wc = w.toCharArray();
			Arrays.sort(wc);
			result.add(String.valueOf(wc));
		}
		Collections.sort(result);
		StringBuilder s = new StringBuilder();
		Iterator<String> it = result.iterator();
		while(it.hasNext()) {
			s.append(it.next());
			if(it.hasNext()) {
				s.append(" ");
			}
		}
		return s.toString();
		
	}
	
	public static void main(String[] args) {
		ArrangeLetters al = new ArrangeLetters();
		System.out.println(al.arrange("kO pSk sO"));
	}
}
