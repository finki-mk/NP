package mk.ukim.finki.np.av7;

import java.util.Arrays;


public class ArrangeLetters {
	public String arrange(String input) {
		String[] parts = input.split(" ");
		for(int i = 0; i < parts.length; ++i) {
			char[] w = parts[i].toCharArray();
			Arrays.sort(w);
			parts[i] = new String(w);
		}
		Arrays.sort(parts);
		StringBuilder res = new StringBuilder();
		for(int i = 0; i < parts.length; ++i) {
			res.append(parts[i]);
			if(i != parts.length - 1) {
				res.append(" ");
			}
		}
		return res.toString();
	}
}
