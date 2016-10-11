package mk.ukim.finki.np.av6;

import java.util.Scanner;

public class ProductsBill {
	private static final int TOTAL_PRODUCTS = 3;
	private static final double SALES_TAX = 6.25;

	public static void main(String[] args) {
		Product[] products = new Product[TOTAL_PRODUCTS];
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < TOTAL_PRODUCTS; i++) {
			System.out.printf("Product %d\n", i + 1);
			System.out.print("Name: ");
			String name = scanner.nextLine();
			System.out.print("Quantity: ");
			int quantity = scanner.nextInt();
			System.out.print("Price: ");
			double price = scanner.nextDouble();
			products[i] = new Product(name, quantity, price);
			scanner.nextLine();
		}
		double total = 0;
		System.out.printf("%-30s%10s%10s%10s\n", "Name", "Quantity", "Price",
				"Total");
		for (int i = 0; i < TOTAL_PRODUCTS; i++) {
			total += products[i].getTotal();
			System.out.printf("%-30s%10d%10.2f%10.2f\n", products[i].getName(),
					products[i].getQuantity(), products[i].getPrice(),
					products[i].getTotal());
		}
		System.out.printf("%-30s%30.2f\n", "Subtotal", total);
		System.out.printf("%-30s%30.2f\n",
				String.format("%.2f%% sales tax", SALES_TAX), SALES_TAX
						* total / 100);
		System.out.printf("%-30s%30.2f\n", "Total",
				total *= (1. + SALES_TAX / 100));
	}
}

class Product {
	private String name;
	private int quantity;
	private double price;

	public Product(String name, int quantity, double price) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	public double getTotal() {
		return quantity * price;
	}

}
