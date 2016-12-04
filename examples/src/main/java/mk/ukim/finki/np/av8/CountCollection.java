package mk.ukim.finki.np.av8;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CountCollection {


    public static int count(Collection<Collection<String>> c, String str) {
        int count = 0;
        for (Collection<String> sub : c) {
            for (String s : sub) {
                if (s.equals(str)) {
                    ++count;
                }
            }
        }
        return count;
    }

    static long countFunc(Collection<Collection<String>> collection, String str) {
        return collection.stream()
                .mapToLong(coll ->
                        coll.stream()
                                .filter(s -> s.equals(str))
                                //.peek(s -> System.out.println("s: " + s))
                                .count()

                )//.peek(i -> System.out.println("i: " + i))
                .sum();
    }

    public static <T> void printReverse(Collection<? extends T> collection) {
        int size = collection.size();
        Object[] array = collection.toArray();
        for (int i = size - 1; i >= 0; --i) {
            System.out.println(array[i]);
        }
    }


    static <T> void reversePrint(Collection<? extends T> collection) {
        Object[] array = collection.toArray(new Object[collection.size()]);
        for (int i = array.length - 1; i >= 0; --i) {
            System.out.println(array[i]);
        }
    }

    static <T> boolean equals(List<T> left, List<T> right) {
        if (left.size() != right.size()) {
            return false;
        } else {
            /*for (int i = 0; i < left.size(); ++i) {
                if (!left.get(i).equals(right.get(i))) {
                    return false;
                }
            }*/
            Iterator<T> itLeft = left.iterator();
            Iterator<T> itRight = right.iterator();
            while (itLeft.hasNext()) {
                T l = itLeft.next();
                T r = itRight.next();
                if (!l.equals(r)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<String> first = Arrays.asList("abc", "xyz", "hello");
        List<String> second = Arrays.asList("abc", "abc", "hello", "world");
        Collection<Collection<String>> collection =
                Arrays.asList(first, second);
        System.out.println(countFunc(collection, "abc"));
        reversePrint(second);
        System.out.println(equals(first, second));

        final List<String> filtered = new ArrayList<>();
        Consumer<String> addToFiltered = each -> filtered.add(each);

        first.forEach(addToFiltered);
        System.out.println("Filtered");
        filtered.forEach(System.out::println);

        Function<String, Predicate<String>> filterByChar =
                str -> (each -> each.contains(str));

        Predicate<String> containsL = each -> each.contains("l");

        Supplier<Integer> randInt = () -> (int) (Math.random() * 100);


        first.stream()
                .filter(filterByChar.apply("l"))
                .forEach(System.out::println);

        //filtered = fs.collect(Collectors.toList());
        for (int i = 0; i < 100; ++i) {
            System.out.println(randInt.get());
        }

    }

    static Predicate<String> filterByLetter(String letter) {
        return each -> each.contains(letter);
    }

}
