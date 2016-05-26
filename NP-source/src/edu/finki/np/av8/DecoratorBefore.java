package edu.finki.np.av8;

public class DecoratorBefore {

	static class Coffee {
		public void doIt() {
			System.out.print("Coffee");
		}
	}

	static class CoffeeWithSugar extends Coffee {
		public void doIt() {
			super.doIt();
			doX();
		}

		private void doX() {
			System.out.print(" Sugar ");
		}
	}

	static class CoffeeWithCinamon extends Coffee {
		public void doIt() {
			super.doIt();
			doY();
		}

		public void doY() {
			System.out.print(" Cinamon ");
		}
	}

	static class CofeeWithMilk extends Coffee {
		public void doIt() {
			super.doIt();
			doZ();
		}

		public void doZ() {
			System.out.print(" Milk ");
		}
	}

	static class CoffeeWithSugarAndCinamon extends CoffeeWithSugar {
		private CoffeeWithCinamon obj = new CoffeeWithCinamon();

		public void doIt() {
			super.doIt();
			obj.doY();
		}
	}

	static class CoffeeWithSugarCinamonAndMilk extends CoffeeWithSugar {
		private CoffeeWithCinamon obj1 = new CoffeeWithCinamon();
		private CofeeWithMilk obj2 = new CofeeWithMilk();

		public void doIt() {
			super.doIt();
			obj1.doY();
			obj2.doZ();
		}
	}

	public static void main(String[] args) {
		Coffee[] array = { new CoffeeWithSugar(),
				new CoffeeWithSugarAndCinamon(),
				new CoffeeWithSugarCinamonAndMilk() };
		for (int i = 0; i < array.length; i++) {
			array[i].doIt();
			System.out.println();
		}
	}
}