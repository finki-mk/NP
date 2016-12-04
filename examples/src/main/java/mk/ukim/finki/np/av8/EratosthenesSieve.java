package mk.ukim.finki.np.av8;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.IntStream;

public class EratosthenesSieve {

    static boolean isPrime(int n) {
        return n >= 2 && IntStream.rangeClosed(2, n / 2)
                .noneMatch(x -> n % x == 0);
    }

    List<Integer> getPrimes(int n) {
        List<Integer> primes = new ArrayList<>();
        IntStream.rangeClosed(2, n)
                .forEach(primes::add);
        for (int i = 0; i < primes.size(); ++i) {
            int currentPrime = primes.get(i);
            ListIterator<Integer> it = primes.listIterator(i + 1);
            while (it.hasNext()) {
                if (it.next() % currentPrime == 0) {
                    it.remove();
                }
            }
        }
        return primes;
    }

    public static void main(String[] args) {
        /*for (int i = 0; i < 1000; ++i) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }*/
        EratosthenesSieve sieve = new EratosthenesSieve();
        List<Integer> primes = sieve.getPrimes(1000);
        primes.forEach(System.out::println);

    }
}
