package np2014;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CarTest {
	public static void main(String[] args) {
		CarCollection carCollection = new CarCollection();
		String manufacturer = fillCollection(carCollection);
		carCollection.sortByPrice(true);
		System.out.println("=== Sorted By Price ASC ===");
		print(carCollection.getList());
		carCollection.sortByPrice(false);
		System.out.println("=== Sorted By Price DESC ===");
		print(carCollection.getList());
		System.out.printf("=== Filtered By Manufacturer: %s ===\n",
				manufacturer);
		List<Car> result = carCollection.filterByManufacturer(manufacturer);
		print(result);
	}

	static void print(List<Car> cars) {
		for (Car c : cars) {
			System.out.println(c);
		}
	}

	static String fillCollection(CarCollection cc) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] parts = line.split(" ");
			if (parts.length < 4)
				return parts[0];
			Car car = new Car(parts[0], parts[1], Integer.parseInt(parts[2]),
					Float.parseFloat(parts[3]));
			cc.addCar(car);
		}
		scanner.close();
		return "";
	}
}

class Car implements Comparable<Car> {
	String manufacturer;
	String model;
	int price;
	float power;

	public Car(String manufacturer, String model, int price, float power) {
		this.manufacturer = manufacturer;
		this.model = model;
		this.price = price;
		this.power = power;
	}

	@Override
	public int compareTo(Car o) {
		if (this.price == o.price) {
			return Float.compare(power, o.power);
		}
		return Integer.compare(price, o.price);
	}

}

class CarCollection {
	List<Car> cars;

	public CarCollection() {
		this.cars = new ArrayList<Car>();
	}

	public void addCar(Car car) {
		this.cars.add(car);
	}

	public void sortByPrice(boolean ascending) {
		if (ascending) {
			Collections.sort(cars, Collections.reverseOrder());
		} else {
			Collections.sort(cars);
		}
	}

	public List<Car> filterByManufacturer(String manufacturer) {
		List<Car> result = new ArrayList<Car>();
		for (Car c : cars) {
			if (c.manufacturer.equalsIgnoreCase(manufacturer)) {
				result.add(c);
			}
		}
		Collections.sort(result, new ModelComparator());
		return result;
	}

	public List<Car> getList() {
		return cars;
	}

}

class ModelComparator implements Comparator<Car> {

	@Override
	public int compare(Car o1, Car o2) {
		return o1.model.compareTo(o2.model);
	}

}
