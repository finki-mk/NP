package mk.ukim.finki.np.av7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class BenfordLawTest {
    static final String INPUT_FILE = "examples/data/librarybooks.txt";
    /*
     * librarybooks.txt
	 * (* Library holdings (# of books in each library), *)
	   (* collected by Christian Ayotte.                 *)
	   (* Labels not available.                          *)
	   
	   livejournal.txt
	   (* LiveJournal data collected by Shirley Man from *)
	   (* http://www.livejournal.com/stats/stats.txt     *)
	   (* Number of new accounts on LiveJournal,         *)
	   (* day by day from 2000/1/1 to 2005/2/28          *)
	   (* Individual data are NOT labelled.              *)
	   
	   sunspots.txt
	   (* Sunspot data collected by Robin McQuinn from *)
	   (* http://sidc.oma.be/html/sunspot.html         *)

	 */

    public static void main(String[] args) throws FileNotFoundException {
        NumbersReader numbersReader = new LineNumbersReader();
        List<Integer> numbers = numbersReader.read(new FileInputStream(INPUT_FILE));
        BenfordLawTest benfordLawTest = new BenfordLawTest();
        int[] count = benfordLawTest.counts(numbers);
        CountVisualizer visualizer = new CountVisualizer(100);
        visualizer.visualize(System.out, count);
    }

    public int[] counts(List<Integer> numbers) {
        int[] result = new int[10];
        for (Integer number : numbers) {
            int digit = firstDigit(number);
            result[digit]++;
        }
        return result;
    }

    public int[] countsFunc(List<Integer> numbers) {
        return numbers.stream()
                .map(BenfordLawTest::firstDigit)
                .map(x -> {
                    int[] res = new int[10];
                    res[x]++;
                    return res;
                })
                .reduce(new int[10], (left, right) -> {
                    Arrays.setAll(left, i -> left[i] + right[i]);
                    return left;
                });
    }

    static int firstDigit(int num) {
        while (num >= 10) {
            num /= 10;
        }
        return num;
    }
}
