package mk.ukim.finki.np.homework.hw1;

import java.util.Random;

/**
 * Created by tdelev on 8.12.15.
 */
public class HappyBalls {
    public static final int TRIALS = 10000;

    public static void main(String[] args) {
        Random random = new Random();
        int events = 0;
        for (int i = 0; i < TRIALS; ++i) {
            int correct = 0;
            for (int j = 0; j < 10; ++j) {
                int pick = random.nextInt(3);
                if (pick == 0) {
                    ++correct;
                }
            }
            if (correct >= 6) {
                ++events;
            }
        }
        System.out.printf("%.10f\n", events * 1. / TRIALS);
    }
}
