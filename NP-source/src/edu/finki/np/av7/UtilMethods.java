package edu.finki.np.av7;

/**
 *
 * @author Gore
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilMethods {
	public static void main(String args[]) {
		// create array list object
		List<String> arrlist = new ArrayList<String>();
		// populate the list
		arrlist.add("C");
		arrlist.add("A");
		arrlist.add("B");
		arrlist.add("X");
		arrlist.add("D");
		arrlist.add("C");
		arrlist.add("C");

		// check frequensy of 'C'
		int freq = Collections.frequency(arrlist, "C");
		Map<String, Integer> count = new HashMap<String, Integer>();
		for(String s : arrlist) {
			Integer i = count.get(s);
			if(i == null) {
				i = 0;
			}
			++i;
			count.put(s, i);
		}
		System.out.println(count);

		System.out.println("Frequency of 'C' is: " + freq);
	}
}
