package edu.finki.np.av7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fruit implements Comparable<Fruit> {
	private String fruitName;

	private int quantity;

	public String getFruitName() {
		return fruitName;
	}

	public void setFruitName(String fruitName) {
		this.fruitName = fruitName;
	}

	public Fruit(String fruitName, int quantity) {
		super();
		this.fruitName = fruitName;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return this.fruitName + " " + this.quantity;
	}

	@Override
	public int compareTo(Fruit compareFruit) {
		// ascending order
		return this.quantity - compareFruit.quantity;
		// descending order
		// return compareQuantity - this.quantity;
	}

	public static Comparator<Fruit> FruitNameComparator = new Comparator<Fruit>() {
		public int compare(Fruit fruit1, Fruit fruit2) {
			String fruitName1 = fruit1.fruitName.toUpperCase();
			String fruitName2 = fruit2.fruitName.toUpperCase();
			// ascending order
			return fruitName1.compareTo(fruitName2);
			// descending order
			// return fruitName2.compareTo(fruitName1);
		}
	};

	public static void main(String[] args) {
		List<Fruit> fruits = new ArrayList<Fruit>();

		fruits.add(new Fruit("Pineapple", 2));
		Fruit orange = new Fruit("Orange", 9);
		fruits.add(new Fruit("Apple", 8));
		fruits.add(new Fruit("sfadd", 8));
		fruits.add(new Fruit("Banana", 5));
		fruits.add(orange);
		
		System.out.println(fruits);
		System.out.println("shuffle");
		Collections.shuffle(fruits);
		
		
		System.out.println(fruits);
		System.out.println("sorted");
		Collections.sort(fruits);
		int i = Collections.binarySearch(fruits, orange);
		System.out.println("I: " + i);
		System.out.println(fruits);
		System.out.println("reverse");
		Collections.reverse(fruits);
		System.out.println(fruits);
		System.out.println("rotate: -3");
		Collections.rotate(fruits, -3);
		System.out.println(fruits);
		
		Collections.swap(fruits, 0, 3);
		
		
		
	}
}