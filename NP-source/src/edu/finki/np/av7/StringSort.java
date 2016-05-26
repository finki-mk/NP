package edu.finki.np.av7;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StringSort {
	static String[] test = new String[] { "string", "pogolemVodolzina", "mal",
			"alfaba" };

	public static void main(String[] args) {
		List<String> list = Arrays.asList(test);

		Collections.sort(list, specialComparator);
		for (String s : list) {
			System.out.println(s);
		}
	}

	static Comparator<String> specialComparator = new Comparator<String>() {

		@Override
		public int compare(String o1, String o2) {
			// TODO Auto-generated method stub
			return 0;
		}

	};

}

class MyStringComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		if (o1.length() != o2.length()) {
			return Integer.compare(o1.length(), o2.length());
		}
		return o1.compareTo(o2);
	}

}

class MyHappyStringComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		// TODO Auto-generated method stub
		return 0;
	}

}
