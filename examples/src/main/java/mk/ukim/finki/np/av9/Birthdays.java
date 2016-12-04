package mk.ukim.finki.np.av9;

import java.util.*;

public class Birthdays {
    static final int NUM_TRIALS = 5000;

    private final int maxPeople;

    public Birthdays(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public Map<Integer, Double> simulate(int numTrials) {
        Random rand = new Random();
        Map<Integer, Double> probabilities = new TreeMap<>();
        for (int numPeople = 2; numPeople <= maxPeople; ++numPeople) {
            double probability = simulation(numPeople, numTrials, rand);
            probabilities.put(numPeople, probability);
        }
        return probabilities;
    }

    private double simulation(int numPeople, int numTrials, Random random) {
        int positiveEvents = 0;
        for (int i = 0; i < numTrials; ++i) {
            if (singleExperiment(numPeople, random)) {
                ++positiveEvents;
            }
        }
        return positiveEvents * 1.0 / NUM_TRIALS;
    }

    private boolean singleExperiment(int numPeople, Random random) {
        int sameBirthday = 1;
        Set<Integer> room = new HashSet<>();
        for (int i = 0; i < numPeople; ++i) {
            int birthday = random.nextInt(365) + 1;
            if (!room.add(birthday)) {
                ++sameBirthday;
            }
        }
        return sameBirthday >= 2;
    }

    public static void main(String[] args) {
        Birthdays birthdays = new Birthdays(50);
        Map<Integer, Double> probabilities = birthdays.simulate(NUM_TRIALS);
        probabilities.forEach((key, value) ->
                System.out.printf("For %d people, the probability of two birthdays is about %.3f\n", key, value));
    }
}
