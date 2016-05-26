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
		ListIterator<Integer> it = positions.listIterator();
		boolean toRight = true;
		while(positions.size() != 1) {
			int x = 0;
			for(int i = 0; i < 3; ++i) {
				if(toRight && it.hasNext()) {
					x = it.next();
					System.out.printf("%d ->\n", x);
					if(!it.hasNext()) {
						toRight = false;
						it.previous();
					}
				} else if(!toRight && it.hasPrevious()) {
					x = it.previous();
					System.out.printf(" <- %d\n", x);
					if(!it.hasPrevious()) {
						toRight = true;
						it.next();
					}
				}
			}
			System.out.printf("Removing: %d %s\n", x, toRight ? "->" : "<-");
			it.remove();			
		}
		return positions.get(0);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		System.out.println("Winner: " + LuckySuitor.getWinner(6));
	}
}
