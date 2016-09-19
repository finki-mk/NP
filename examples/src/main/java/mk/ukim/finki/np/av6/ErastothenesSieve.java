package mk.ukim.finki.np.av6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ErastothenesSieve {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 2; i <= 100; ++i) {
			list.add(i);
		}
		for (int i = 0; i < list.size(); ++i) {
			Iterator<Integer> iterator = list.listIterator(i + 1);
			while (iterator.hasNext()) {
				if (iterator.next() % list.get(i) == 0) {
					iterator.remove();
				}
			}
		}

		for (int i = 0; i < list.size(); ++i) {
			System.out.printf("%d ", list.get(i));
		}
	}
}
