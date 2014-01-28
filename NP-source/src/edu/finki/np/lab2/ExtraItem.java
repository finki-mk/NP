package edu.finki.np.lab2;

public class ExtraItem extends Item {
	private ExtraType type;

	public ExtraItem(String type) throws InvalidExtraTypeException {
		try {
			this.type = ExtraType.valueOf(type);
		} catch (Exception e) {
			throw new InvalidExtraTypeException();
		}
	}

	@Override
	public int getPrice() {
		return type.getPrice();
	}

}

enum ExtraType {
	Ketchup(1), Coke(2);
	private int value;
	private int price;

	private ExtraType(int value) {
		this.value = value;
		if (value == 1)
			price = 3;
		else if (value == 2)
			price = 5;
	}

	public int getPrice() {
		return price;
	}
}

class InvalidExtraTypeException extends Exception {

}
