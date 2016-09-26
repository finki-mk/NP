package mk.ukim.finki.np.av1;

import java.util.stream.LongStream;

/**
 * Some stream tests
 */
public class Testing {
    static final long RANGE = 1_000_000_000;

    public static void main(String[] args) {
        //System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");
        long start = System.currentTimeMillis();
        double result = LongStream.range(0, RANGE)
                //.parallel()
                .filter(Testing::divBy5)
                .average().getAsDouble();
        System.out.println(result);
        System.out.println("Time: " + (System.currentTimeMillis() - start));
    }

    static boolean divBy5(long x) {
        return divisible(x, 5);
    }

    static boolean divisible(long x, int n){
        return x % n == 0;
    }
}
