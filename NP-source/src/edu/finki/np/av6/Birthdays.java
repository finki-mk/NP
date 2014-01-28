package edu.finki.np.av6;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Birthdays {
	static final int NUM_TRIALS = 100000;

	public static void main(String[] args) {
		for(int i = 2; i <= 100; ++i) {
			Random random = new Random();
			int count = 0;
			for(int j = 0; j < NUM_TRIALS; ++j) {
				Set<Integer> generated = new HashSet<Integer>();
				for(int k = 0; k < i; ++k) {
					int birthday = random.nextInt(365) + 1;
					if(!generated.add(birthday)) {
						++count;
						break;
					}				
				}				
			}
			System.out.printf("%d persons, %.15f\n", i, 1. * count / NUM_TRIALS);
		}

	}
}
