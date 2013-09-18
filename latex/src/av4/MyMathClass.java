package edu.finki.np.av4;

import java.util.ArrayList;

public class MyMathClass {

	public static double standardDeviation(ArrayList<? extends Number> array) {
		double sum = 0;
		for (Number n : array) {
			sum += n.doubleValue();
		}
		double avg = sum / array.size();
		sum = 0;
		for (Number n : array) {
			sum += (avg - n.doubleValue()) * (avg - n.doubleValue());
		}
		return Math.sqrt(sum / array.size());
	}

	public static void main(String[] args) {
		ArrayList<Integer> ints = new ArrayList<Integer>();
		ints.add(1);
		ints.add(10);
		ints.add(20);
		ints.add(30);
		ints.add(40);
		ints.add(50);
		System.out.println(String.format("STD: %.2f",
				MyMathClass.standardDeviation(ints)));
		ArrayList<String> strings = new ArrayList<String>();
		// MyMathClass.standardDeviation(strings); compilation error
	}

}
