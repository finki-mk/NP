package edu.finki.np.av6;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Birthdays {
	static final int NUM_TRIALS = 5000;

	public static void main(String[] args) {
		for (int i = 2; i <= 50; ++i) {
			int counter = 0;
			for (int j = 0; j < NUM_TRIALS; ++j) {
				Set<Integer> room = new HashSet<Integer>();
				Random random = new Random();
				for (int k = 0; k < i; ++k) {
					int birthday = random.nextInt(365) + 1;
					//System.out.println(birthday);
					if (!room.add(birthday)) {
						++counter;
						break;
					}
				}
			}
			System.out.printf("For %d people, P = %.4f\n", i, 1. * counter
					/ NUM_TRIALS);
		}

	}
}
