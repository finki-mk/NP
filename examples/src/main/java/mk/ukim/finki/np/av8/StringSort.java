package mk.ukim.finki.np.av8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class StringSort {
    static final String[] TEST_WORDS = new String[]{"string", "pogolemVodolzina", "mal",
            "alfaba"};

    public static void main(String[] args) {
        List<String> list = Arrays.asList(TEST_WORDS);
        list.stream()
                .sorted(
                        Comparator.comparing(String::length)
                                .thenComparing(Function.identity())
                ).forEach(System.out::println);

    }
}

class StringComparator implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 != len2) {
            return len1 - len2;
        } else {
            return s1.compareTo(s2);
        }
    }
}
