package edu.finki.np.av5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ProductsBill {
	private static final int TOTAL_PRODUCTS = 3;
	private static final double SALES_TAX = 6.25;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<Product> products = new ArrayList<Product>();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] parts = line.split(" ");
			StringBuilder name = new StringBuilder();
			for (int i = 0; i < parts.length - 2; ++i) {
				name.append(parts[i]);
				name.append(" ");
			}
			Product p = new Product(name.toString().trim(),
					Integer.parseInt(parts[parts.length - 2]),
					Float.parseFloat(parts[parts.length - 1]));
			products.add(p);
		}
		System.out.printf("%-30s%10s%10s%10s\n", "Name", "Quantity", "Price",
				"Total");
		float subtotal = 0;
		Collections.sort(products);
		for (Product p : products) {
			System.out.println(p);
			subtotal += p.getTotal();
		}
		System.out.printf("%-50s%10.2f\n", "Subtotal", subtotal);
		System.out.printf("%-50s%10.2f\n", String.format("%.2f%% sales tax", SALES_TAX), subtotal * SALES_TAX / 100);
		System.out.printf("%-50s%10.2f\n", "Total", subtotal * (1 + SALES_TAX / 100));

	}

}

class Product implements Comparable<Product> {
	private String name;
	private int quantity;
	private float price;

	public Product(String name, int quantity, float price) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public float getTotal() {
		return quantity * price;
	}

	@Override
	public String toString() {
		return String.format("%-30s%10d%10.2f%10.2f", name, quantity, price,
				quantity * price);
	}

	@Override
	public int compareTo(Product o) {
		if(name.compareTo(o.name) == 0) {
			return Float.compare(price, o.price);
		}
		return name.compareTo(o.name);
	}
	
	
	
	

}