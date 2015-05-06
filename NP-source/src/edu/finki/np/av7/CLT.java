package edu.finki.np.av7;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class CLT {
	public static final int N = 1000;
	public static void main(String[] args) {
		Map<Integer, Integer> count = new TreeMap<Integer, Integer>();
		for(int i = 0; i < N; ++i) {
			float sum = 0;
			for(int j = 0; j < N; ++j) {
				sum += Math.random();
			}
			int x = (int)(sum * 1000 / N);
			//System.out.println("x: " + x);
			Integer c = count.get(x);
			if(c == null) {
				c = 0;
			}
			++c;
			count.put(x, c);
		}
		for(Entry<Integer, Integer> entry : count.entrySet()) {
			System.out.printf("%d: ", entry.getKey());
			for(int i = 0; i < entry.getValue(); ++i) {
				System.out.print("-");
			}
			System.out.println();
		}
		
	}
}
