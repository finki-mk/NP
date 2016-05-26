package edu.finki.np.av6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ErastothenesSieve {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 2; i <= 100; ++i) {
			list.add(i);
		}
		
		for(int i = 0; i < list.size(); ++i) {
			Iterator<Integer> it = list.listIterator(i + 1);
			while(it.hasNext()) {
				if(it.next() % list.get(i) == 0) {
					it.remove();
				}
			}
			/*for(int j = i + 1; j < list.size(); ++j) {
				if(list.get(j) % list.get(i) == 0) list.remove(j);
			}*/
		}
		
		for(Integer i : list) {
			System.out.println(i);
		}
	}
}












