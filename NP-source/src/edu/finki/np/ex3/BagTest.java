package edu.finki.np.ex3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class BagTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.nextLine();
		Bag<String> bagOfStrings = new Bag<String>(n - 1);
		for (int i = 0; i < n; ++i) {
			try {
				bagOfStrings.add(scanner.nextLine());
			} catch (BagFullException e) {
				System.out.println(e.getMessage());
			}
		}
        scanner.nextLine();
		n = scanner.nextInt();        
		scanner.nextLine();
		Bag<Integer> bagOfInts = new Bag<Integer>(n);
		for (int i = 0; i < n; ++i) {
			try {
				bagOfInts.add(scanner.nextInt());
			} catch (BagFullException e) {
				System.out.println(e.getMessage());
			}
		}
		scanner.close();
		System.out.println("Bag of Strings");
		System.out.println(bagOfStrings);
		System.out.println("Bag of ints");
		System.out.println(bagOfInts);

	}
}

class Bag<T> {
	private int N;
	private List<T> elements;

	public Bag(int N) {
		this.N = N;
		elements = new ArrayList<T>(N);
	}

	public void add(T element) throws BagFullException {
		if (elements.size() == N)
			throw new BagFullException();
		elements.add(element);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<T> it = elements.iterator();
		while (it.hasNext()) {
			T element = it.next();
			sb.append(element.toString());
			if(it.hasNext()) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}

}

class BagFullException extends Exception {
	public BagFullException() {
		super("Bag full exception");
	}
}
