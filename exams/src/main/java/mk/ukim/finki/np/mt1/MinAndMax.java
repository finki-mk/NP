package mk.ukim.finki.np.mt1;

public class MinAndMax {
    public static void main(String[] args) throws ClassNotFoundException {
        MinMax<String> strings = new MinMax<>();
        strings.add("b");
        strings.add("b");
        strings.add("a");

        strings.add("a");
        strings.add("a");
        strings.add("c");
        strings.add("c");
        strings.add("c");
        strings.add("d");
        System.out.println(strings);
    }
}

class MinMax<T extends Comparable<T>> {
    T min;
    T max;
    int total;
    int minCount;
    int maxCount;

    public MinMax() {
        total = 0;
        minCount = 0;
        maxCount = 0;
    }

    public void add(T element) {
        if (total == 0) {
            min = element;
            max = element;
        }
        ++total;
        if (element.compareTo(min) < 0) {
            minCount = 1;
            min = element;
        } else {
            if (element.compareTo(min) == 0) {
                minCount++;
            }
        }
        if (element.compareTo(max) > 0) {
            maxCount = 1;
            max = element;
        } else {
            if (element.compareTo(max) == 0) {
                maxCount++;
            }
        }
    }

    public T min() {
        return min;
    }

    public T max() {
        return max;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        T min = min();
        T max = max();
        sb.append(String.format("%s %s %d\n", min, max, total
                - (maxCount + minCount)));
        return sb.toString();
    }

}
