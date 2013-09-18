package edu.finki.np.mt2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MobilePhonesTest {
	public static void main(String[] args) {
		PhonesList mpl = new PhonesList();
		// Create list object
		fillList(mpl);
		mpl.sortByManufacturer();
		System.out.println("SORTED BY MANUFACTURER");
		for(Phone p : mpl.getPhones()) {
			System.out.println(p);
		}
		mpl.sortByPrice();
		System.out.println("SORTED BY PRICE");
		for(Phone p : mpl.getPhones()) {
			System.out.println(p);
		}
	}
	
	static void fillList(PhonesList mpl) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] parts = line.split(" ");
			Phone phone = new Phone(parts[0], parts[1], Float.parseFloat(parts[2]));
			mpl.addPhone(phone);
		}
	}
}

class PhonesList {
	private List<Phone> phones;
	
	public PhonesList() {
		phones = new ArrayList<Phone>();
	}
	
	public void addPhone(Phone phone) {
		phones.add(phone);
	}
	public void sortByPrice() {
		sort(new PriceComparator());
	}
	public void sortByManufacturer() {
		sort(new ManufacturerComparator());
	}
	
	public void removeWithSmallerPrice(float price) {
		Iterator<Phone> it = phones.iterator();
		while(it.hasNext()) {
			Phone mp = it.next();
			if(mp.getPrice() < price) {
				it.remove();
			}
		}
	}
	
	public List<Phone> getPhones() {
		return this.phones;
	}
	
	
	private void sort(Comparator<Phone> comparator) {
		Collections.sort(phones, comparator);
	}
}

class Phone {
	private String name;
	private String manufacturer;
	private float price;

	public Phone(String name, String manufacturer, float price) {
		this.name = name;
		this.manufacturer = manufacturer;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s %.2f", name, manufacturer, price);
	}
}

class ManufacturerComparator implements Comparator<Phone> {

	@Override
	public int compare(Phone o1, Phone o2) {
		return o1.getManufacturer().compareTo(o2.getManufacturer());
	}
}

class PriceComparator implements Comparator<Phone> {

	@Override
	public int compare(Phone o1, Phone o2) {
		if (o1.getPrice() < o2.getPrice())
			return -1;
		else if (o1.getPrice() > o2.getPrice())
			return 1;
		else
			return 0;
	}
}