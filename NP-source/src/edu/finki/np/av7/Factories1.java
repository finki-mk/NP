package edu.finki.np.av7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author gjorgji.madjarov
 */

public class Factories1 {

	public static List<String> find(int n) {
		if(n > 0) {
			List<String> res = new ArrayList<String>();
			res.add("fdf");
			return res;
		}
		
		return Collections.emptyList();
	}

	public static void main(String args[]) {
		// create an empty list
		List<String> emptylst = Collections.emptyList();
		System.out.println(emptylst.size());
		//emptylst.add("ELEMENT");
		
		List<String> res = find(0);
		for(String s : res) {
			System.out.println(s);
		}
		

	}
}
