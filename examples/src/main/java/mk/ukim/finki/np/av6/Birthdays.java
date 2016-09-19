package mk.ukim.finki.np.av6;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Birthdays {
	static final int NUM_TRIALS = 5000;

	public static void main(String[] args) {
		Random rand = new Random();

		for (int numPeople = 2; numPeople <= 50; ++numPeople) {
			int positiveEvents = 0;
			for (int j = 0; j < NUM_TRIALS; ++j) {
				int sameBirthday = 1;
				Set<Integer> room = new HashSet<Integer>();
				for (int i = 0; i < numPeople; ++i) {
					int birthday = rand.nextInt(365) + 1;
					if (!room.add(birthday)) {
						sameBirthday++;
					}
				}
				if (sameBirthday == 5) {
					positiveEvents++;
				}
			}
			System.out.printf("Probability: %d : %f\n", numPeople, positiveEvents * 1.0
					/ NUM_TRIALS);
		}
	}
}
