package edu.finki.np.av6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CountCollection {
	public static int count(Collection<Collection<String>> c, String str) {
		int count = 0;
		for (Collection<String> sub : c) {
			for (String s : sub) {
				if (s.equals(str)) {
					++count;
				}
			}
		}
		return count;
	}

	public static void printReverse(Collection<?> collection) {
		int size = collection.size();
		Object[] array = collection.toArray();
		for (int i = size - 1; i >= 0; --i) {
			System.out.println(array[i]);
		}
	}

	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<String>();
		a.add("А");
		a.add("Џ");
		a.add("В");
		a.add("Ј");
		a.add("Г");
		Collections.sort(a);
		printReverse(a);
	}
}
