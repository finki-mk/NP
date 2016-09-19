package mk.ukim.finki.np.av6;

import java.util.ArrayList;
import java.util.Collection;

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

	public static <T> void printReverse(Collection<? extends T> collection) {
		int size = collection.size();
		Object[] array = collection.toArray();
		for (int i = size - 1; i >= 0; --i) {
			System.out.println(array[i]);
		}
	}

	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<String>();
		a.add("F");
		a.add("I");
		a.add("N");
		a.add("K");
		a.add("I");
		printReverse(a);
	}
}
