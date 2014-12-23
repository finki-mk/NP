package edu.finki.np.av6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ErastothenesSieve {
	public static void main(String[] args) {
		List<Integer> primes = new ArrayList<Integer>();
		for(int i = 2; i < 10000; ++i) {
			primes.add(i);
		}
		for(int i = 0; i < primes.size(); ++i) {
			Iterator<Integer> it = primes.listIterator(i + 1);
			while(it.hasNext()) {
				if(it.next() % primes.get(i) == 0) { 
					it.remove();
				}
			}
		}
		for(Integer p : primes) {
			System.out.println(p);
		}
	}
}
