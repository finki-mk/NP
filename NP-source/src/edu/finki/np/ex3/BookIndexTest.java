package edu.finki.np.ex3;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class BookIndexTest {
	public static void main(String[] args) {
		BookIndex bookIndex = new BookIndex();
		System.out.println("TOTAL TOKENS");
		System.out.println(bookIndex.build(System.in));
		System.out.println("FREQUENCY");
		bookIndex.printFrequency();
		System.out.println("INDEX");
		bookIndex.print();
	}

}

interface IIndex {
	int build(InputStream inputStream);

	void printFrequency();

	void print();
}

class BookIndex implements IIndex {

	Map<String, List<Integer>> index;
	Map<String, Integer> frequency;

	@Override
	public int build(InputStream inputStream) {
		Scanner scanner = new Scanner(inputStream);
		int i = 1;
		frequency = new TreeMap<String, Integer>();
		index = new TreeMap<String, List<Integer>>();
		while (scanner.hasNext()) {
			String key = scanner.next();
			List<Integer> list = index.get(key);
			if (list == null) {
				list = new ArrayList<Integer>();
			}
			list.add(i++);
			index.put(key, list);
			Integer count = frequency.get(key);
			if (count == null) {
				count = 0;
			}
			count++;
			frequency.put(key, count);
		}
		scanner.close();
		return i - 1;
	}

	@Override
	public void printFrequency() {
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(
				frequency.entrySet());
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				return o2.getValue() - o1.getValue();
			}
		});
		for (Entry<String, Integer> entry : list) {
			System.out.printf("%s : %d\n", entry.getKey(), entry.getValue());
		}
	}

	@Override
	public void print() {
		for (Entry<String, List<Integer>> entry : index.entrySet()) {
			System.out.printf("%s : %s\n", entry.getKey(), entry.getValue());
		}

	}

}