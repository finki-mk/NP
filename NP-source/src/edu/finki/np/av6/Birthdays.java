package edu.finki.np.av6;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Birthdays {
	static final int NUM_TRIALS = 5000;
	static final int N = 50;

	public static void main(String[] args) {
		Random random = new Random();

		for (int numberOfPeople = 2; numberOfPeople < N; ++numberOfPeople) {
			int positiveEvents = 0;
			for (int j = 0; j < NUM_TRIALS; ++j) {
				Set<Integer> duplicates = new HashSet<Integer>();
				int same = 0;

				for (int i = 0; i < numberOfPeople; ++i) {
					int birthday = random.nextInt(365);
					if (!duplicates.add(birthday)) {
						++same;
					}
				}
				if (same > 0) {
					++positiveEvents;
				}
			}
			System.out.printf("For %d people the prob. is: %f\n", numberOfPeople, positiveEvents * 100. / NUM_TRIALS);

		}

	}
}







