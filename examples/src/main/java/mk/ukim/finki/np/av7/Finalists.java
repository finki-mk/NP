package mk.ukim.finki.np.av7;

import java.util.List;

public class Finalists {
    public static void main(String[] args) {
        RandomPicker picker = new RandomPicker(30);
        List<Integer> picked = picker.pick(3);
        System.out.println(picked);
    }
}
