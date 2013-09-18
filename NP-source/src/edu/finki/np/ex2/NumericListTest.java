package edu.finki.np.ex2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class NumericListTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		NumericList<Integer> iList = new NumericList<Integer>();
		for (int i = 0; i < n; ++i) {
			iList.add(scanner.nextInt());
		}
		System.out.println("MAX Integer: " + iList.max());
		NumericList<Double> dList = new NumericList<Double>();
		for (int i = 0; i < n; ++i) {
			dList.add(scanner.nextDouble());
		}
		System.out.println("MIN Double: " + dList.min());
		NumericList<Float> fList = new NumericList<Float>();
		for (int i = 0; i < n; ++i) {
			fList.add(scanner.nextFloat());
		}
		System.out.println("AVARAGE Float: " + fList.avarage());
		System.out.println("NOT Sorted");
		System.out.println(iList.toString());
		System.out.println(dList.toString());
		System.out.println(fList.toString());
		List<Double> sorted = iList.sort();
		System.out.println(sorted);

	}
}

class NumericList<T extends Number> {
	private List<T> list;
	private Number max;
	private Number min;
	private Number sum;

	public NumericList() {
		list = new ArrayList<T>();
		sum = 0;
	}

	public void add(T element) {
		if (max == null) {
			max = element;
		}
		if (min == null) {
			min = element;
		}
		if (element.doubleValue() > max.doubleValue()) {
			max = element;
		}
		if (element.doubleValue() < min.doubleValue()) {
			min = element;
		}
		sum = sum.doubleValue() + element.doubleValue();
		list.add(element);
	}

	public double max() {
		return max.doubleValue();
	}

	public double min() {
		return min.doubleValue();
	}

	public double getSum() {
		return sum.doubleValue();
	}

	public double avarage() {
		return sum.doubleValue() / list.size();
	}

	public List<Double> sort() {
		ArrayList<Double> d = new ArrayList<Double>();
		for (Number n : list) {
			d.add(n.doubleValue());
		}
		Collections.sort(d);
		return d;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<T> iterator = list.iterator();
		if(iterator.hasNext()) {
			sb.append("[");
		}
		while (iterator.hasNext()) {
			sb.append(String.format("%.2f", iterator.next().doubleValue()));
			if (iterator.hasNext()) {
				sb.append(", ");
			} else {
				sb.append("]");
			}
		}
		return sb.toString();
	}
}
