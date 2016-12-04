package mk.ukim.finki.np.av7;

import java.util.Arrays;
import java.util.stream.Collectors;


public class ArrangeLetters {
    public String arrange(String input) {
        String[] parts = input.split("\\s+");
/*        IntStream.range(0, parts.length)
                .mapToObj(i -> {
                    char[] part = parts[i].toCharArray();
                    Arrays.sort(part);
                    return new String(part);
                }).sorted();*/

        for (int i = 0; i < parts.length; ++i) {
            char[] w = parts[i].toCharArray();
            Arrays.sort(w);
            parts[i] = new String(w);
        }
        return Arrays.stream(parts)
                .sorted()
                .collect(Collectors.joining(" "));
    }
}
