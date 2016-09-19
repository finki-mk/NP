package mk.ukim.finki.np.av7;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StringSort {
	static String[] test = new String[] { "string", "pogolemVodolzina", "mal",
			"alfaba" };

	public static void main(String[] args) {
		List<String> list = Arrays.asList(test);
		Collections.sort(list, new StringComparator());
		for (String s : list) {
			System.out.println(s);
		}
	}
}

class StringComparator implements Comparator<String> {

	@Override
	public int compare(String s1, String s2) {
		int len1 = s1.length();
		int len2 = s2.length();
		if (len1 != len2) {
			return len1 - len2;
		} else {
			return s1.compareTo(s2);
		}
	}
}
