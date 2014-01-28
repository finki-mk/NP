package edu.finki.np.av6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ErastothenesSieve {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 2; i <= 100; ++i) {
			list.add(i);
		}
		ListIterator<Integer> li = list.listIterator(list.size());
		while(li.hasPrevious()) {
			System.out.println(li.previous());
		}
		//System.out.println(li.previous());
		/*for(int i = 0; i < list.size(); ++i) {
			Iterator<Integer> iterator = list.listIterator(i + 1);
			for(int j = i + 1; j < list.size(); ++j) {
				if(list.get(j) % list.get(i) == 0 ) {
					list.remove(j);
				}
			}
			while(iterator.hasNext()) {
				if(iterator.next() % list.get(i) == 0) {
					iterator.remove();
				}
			}
		}*/
		
		for(int i = 0; i < list.size(); ++i) {
			System.out.printf("%d ", list.get(i));
		}
	}
}
