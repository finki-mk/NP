package mk.ukim.finki.np.mt1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 */
public class GenericCounterTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        GenericCounter<Integer> counterInt = new GenericCounter<Integer>();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            int x = scanner.nextInt();
            counterInt.count(x);
        }
        System.out.println("=====INTEGERS=====");
        System.out.println(counterInt);
        n = scanner.nextInt();
        scanner.nextLine();
        GenericCounter<String> counterString = new GenericCounter<String>();
        for (int i = 0; i < n; i++) {
            String s = scanner.nextLine();
            counterString.count(s);
        }
        System.out.println("=====STRINGS=====");
        System.out.println(counterString);
        n = scanner.nextInt();
        scanner.nextLine();
        GenericCounter<Float> counterFloat = new GenericCounter<Float>();
        for (int i = 0; i < n; i++) {
            float f = scanner.nextFloat();
            counterFloat.count(f);
        }
        System.out.println("=====FLOATS=====");
        System.out.println(counterFloat);
        scanner.close();
    }
}

class GenericCounter<T> {
    List<CountableElement<T>> elements;

    GenericCounter() {
        elements = new ArrayList<>();
    }

    public void count(T element) {
        final Predicate<CountableElement<T>> equal = e -> e.isEqual(element);
        CountableElement<T> found = elements.stream().filter(equal).findFirst().orElse(null);
        if (found != null) {
            found.increment();
        } else {
            elements.add(new CountableElement<>(element));
        }
    }

    @Override
    public String toString() {
        return elements.stream()
                .map(el -> String.format("%s|%s", el.element.toString(), countToStar(el.count)))
                .collect(Collectors.joining("\n", "", "\n"));
    }

    static String countToStar(int c) {
        return IntStream.range(0, c)
                .mapToObj(i -> "*")
                .collect(Collectors.joining(""));
    }
}

class CountableElement<T> {
    int count;
    T element;

    public CountableElement(T element) {
        this.element = element;
        count = 1;
    }

    public void increment() {
        ++count;
    }

    public boolean isEqual(T element) {
        return this.element.equals(element);
    }
}
