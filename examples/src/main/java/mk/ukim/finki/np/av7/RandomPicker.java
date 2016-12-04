package mk.ukim.finki.np.av7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Random picker
 */
public class RandomPicker {
    private final int n;

    public RandomPicker(int n) {
        this.n = n;
    }

    public List<Integer> pick(int x) {
        Random random = new Random();
        List<Integer> picked = new ArrayList<>();
        while (picked.size() != x) {
            int pick = random.nextInt(n) + 1;
            if (!picked.contains(pick)) {
                picked.add(pick);
            }
        }
        return picked;
    }
}
