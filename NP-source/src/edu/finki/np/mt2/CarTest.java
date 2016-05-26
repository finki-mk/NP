package edu.finki.np.mt2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CarTest {
	public static void main(String[] args) {
		CarCollection carCollection = new CarCollection();
		Scanner scanner = fillCollection(carCollection);
		carCollection.sortByPrice(true);
		System.out.println("Sorted By Price ASC");
		print(carCollection.getList());
		carCollection.sortByPrice(false);
		System.out.println("Sorted By Price DESC");
		print(carCollection.getList());
		String manufacturer = scanner.nextLine();
		System.out.printf("Filtered By Manufacturer: %s\n", manufacturer);
		List<Car> result = carCollection.filterByManufacturer(manufacturer);
		print(result);
	}

	static void print(List<Car> cars) {
		for (Car c : cars) {
			System.out.println(c);
		}
	}

	static Scanner fillCollection(CarCollection cc) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] parts = line.split(" ");
			Car car = new Car(parts[0], parts[1], Float.parseFloat(parts[2]),
					Float.parseFloat(parts[3]));
			cc.addCar(car);
		}
		return scanner;
	}
}

class Car {
	String manufacturer;
	String model;
	float price;
	float power;

	public Car(String manufacturer, String model, float price, float power) {
		this.manufacturer = manufacturer;
		this.model = model;
		this.price = price;
		this.power = power;
	}

	@Override
	public String toString() {
		return String.format("%s %s %d %d", manufacturer, model, (int)price, (int)power);
	}
}

class CarCollection {
	List<Car> cars;

	public CarCollection() {
		cars = new ArrayList<>();
	}

	public void addCar(Car car) {
		cars.add(car);
	}

	public void sortByPrice(boolean ascending) {
		Comparator<Car> comparator = new CarPriceComparator();
		if (!ascending) {
			comparator = Collections.reverseOrder(comparator);
		}
		Collections.sort(cars, comparator);
	}

	public List<Car> filterByManufacturer(String manufacturer) {
		List<Car> result = new ArrayList<>();
		for (Car car : cars) {
			if (car.manufacturer.equalsIgnoreCase(manufacturer)) {
				result.add(car);
			}
		}
		Collections.sort(result, new Comparator<Car>() {

			@Override
			public int compare(Car o1, Car o2) {
				return o1.model.compareTo(o2.model);
			}

		});
		return result;
	}

	public List<Car> getList() {
		return cars;
	}

}

class CarPriceComparator implements Comparator<Car> {

	@Override
	public int compare(Car o1, Car o2) {
		if (o1.price == o2.price) {
			return Float.compare(o1.power, o2.power);
		}
		return Float.compare(o1.price, o2.price);
	}

}
