package edu.finki.np.av6;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class LuckySuitor {
	public static int getWinner(int n) {
		List<Integer> positions = new ArrayList<Integer>();
		for (int i = 0; i < n; ++i) {
			positions.add(i + 1);
		}
		ListIterator<Integer> listIterator = positions.listIterator();
		boolean toRight = true;
		while (positions.size() != 1) {
			int last = -1;
			for (int i = 0; i < 3; ++i) {
				if (listIterator.hasNext() && toRight) {
					last = listIterator.next();
					if (!listIterator.hasNext()) {
						toRight = false;
						listIterator.previous();
					}
					System.out.println("->: " + last);
				} else {
					if (listIterator.hasPrevious()) {
						last = listIterator.previous();
						if (!listIterator.hasPrevious()) {
							toRight = true;
							listIterator.next();
						}
						System.out.println("<-: " + last);
					}
				}
			}
			System.out.println("Remove: " + last);
			listIterator.remove();
			System.out.println("DIR: " + (toRight ? "->" : "<-"));
		}
		return positions.get(0);
	}
	
	public static void main(String[] args) {
		System.out.println("Winner: " + LuckySuitor.getWinner(9));
	}
}
