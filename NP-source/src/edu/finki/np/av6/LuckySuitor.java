package edu.finki.np.av6;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class LuckySuitor {
	public static int getWinner(int n) {
		List<Integer> positions = new ArrayList<Integer>();
		for(int i = 1; i <= n; ++i) {
			positions.add(i);
		}
		boolean toRight = true;
		ListIterator<Integer> it = positions.listIterator();
		while(positions.size() != 1) {
			int last = -1;
			for(int i = 0; i < 3; ++i) {
				if(toRight && it.hasNext()) {
					last = it.next();
					System.out.println(last);
					System.out.println(toRight ? "->" : "<-");
					if(!it.hasNext()) {
						toRight = false;
						//last = it.previous();
					}
				} else {
					last = it.previous();
					System.out.println(last);
					System.out.println(toRight ? "->" : "<-");
					if(!it.hasPrevious()) {
						toRight = true;
						//last = it.next();
					}
				}
			}
			System.out.println(toRight ? "->" : "<-");
			System.out.println("REMOVING: " + last);
			it.remove();
		}
		return positions.get(0);
	}
	
	public static void main(String[] args) {
		System.out.println("Winner: " + LuckySuitor.getWinner(6));
	}
}
