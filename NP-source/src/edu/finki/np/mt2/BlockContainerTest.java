package edu.finki.np.mt2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class BlockContainerTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		BlockContainer blockContainer = new BlockContainer(n);
		scanner.nextLine();
		int last = -1;
		while (scanner.hasNext()) {
			int element = scanner.nextInt();
			blockContainer.add(element);
			last = element;
		}
		System.out.println(blockContainer);
		blockContainer.remove(last);
		blockContainer.sort();
		System.out.println(blockContainer);
	}
}

class BlockContainer {
	private List<Set<Integer>> elements;
	private int n;

	public BlockContainer(int n) {
		this.n = n;
		elements = new ArrayList<Set<Integer>>();
	}

	public void add(Integer a) {
		if (elements.size() == 0) {
			Set<Integer> s = new TreeSet<Integer>();
			s.add(a);
			elements.add(s);
		} else {
			Set<Integer> s = elements.get(elements.size() - 1);
			if (s.size() < n) {
				s.add(a);
			} else {
				s = new TreeSet<Integer>();
				s.add(a);
				elements.add(s);
			}
		}
	}

	public void remove(Integer a) {
		if (elements.size() > 0) {
			Set<Integer> s = elements.get(elements.size() - 1);
			s.remove(a);
			if (s.size() == 0) {
				elements.remove(elements.size() - 1);
			}
		}
	}

	public void sort() {
		if (elements.size() > 0) {
			Set<Integer> s = elements.get(elements.size() - 1);
			if (s.size() < n) {
				List<Set<Integer>> sublist = elements.subList(0,
						elements.size() - 1);
				Collections.sort(sublist, new SetComparator());
			} else {
				Collections.sort(elements, new SetComparator());
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < elements.size(); ++i) {
			sb.append(elements.get(i).toString());
			if (i < elements.size() - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}
}

class SetComparator implements Comparator<Set<Integer>> {

	@Override
	public int compare(Set<Integer> set1, Set<Integer> set2) {
		int s1 = 0;
		int s2 = 0;
		Iterator<Integer> it1 = set1.iterator();
		Iterator<Integer> it2 = set2.iterator();
		while (it1.hasNext()) {
			s1 += it1.next();
			s2 += it2.next();
		}
		return s1 - s2;
	}

}
