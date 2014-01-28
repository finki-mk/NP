package edu.finki.np.av6;

import java.util.Iterator;
import java.util.List;

public class ListsTest {
	public static boolean equals(List<Integer> l1, List<Integer> l2) {
		Iterator<Integer> it1 = l1.iterator();
		Iterator<Integer> it2 = l2.iterator();
		for(; it1.hasNext(); ) {
			if(!it1.next().equals(it2.next())) return false;
		}
		
		return true;
	}
}
