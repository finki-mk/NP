package edu.finki.np.lab2;

public class PizzaItem extends Item {
	private PizzaType type;

	public enum Type {
		Standard, Pepperoni, Vegetarian;
	}

	public PizzaItem(String type) throws InvalidPizzaTypeException {
		try {
			this.type = PizzaType.valueOf(type);
		} catch (Exception e) {
			throw new InvalidPizzaTypeException();
		}
	}

	@Override
	public int getPrice() {
		return type.getPrice();
	}
}

enum PizzaType {
	Standard(1), Peperoni(2), Vegetarian(3);
	private int value;
	private int price;

	private PizzaType(int value) {
		this.value = value;
		if (value == 1)
			price = 10;
		else if (value == 2)
			price = 12;
		else if (value == 3) {
			price = 8;
		}
	}

	public int getPrice() {
		return price;
	}
}

class InvalidPizzaTypeException extends Exception {
}
