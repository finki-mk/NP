package edu.finki.np.av8;

import java.util.HashMap;
import java.util.Map;

import jdk.nashorn.internal.runtime.linker.JavaAdapterFactory;

public class DecoratorAfter {

	interface Drink {
		void doIt();
	}

	static class Coffee implements Drink {
		public void doIt() {
			System.out.print("Coffee ");
		}
	}
	
	static class Tea implements Drink {
		@Override
		public void doIt() {
			System.out.print("Tea ");
		}
	}

	static abstract class DecoratedDrink implements Drink {
		private Drink core;

		public DecoratedDrink(Drink inner) {
			core = inner;
		}

		public void doIt() {
			core.doIt();
		}
	}

	static class Milk extends DecoratedDrink {
		public Milk(Drink inner) {
			super(inner);
		}
		
		@Override
		public void doIt() {
			super.doIt();
			doMilk();
		}

		private void doMilk() {
			System.out.print(" Milk ");
		}
	}

	static class Sugar extends DecoratedDrink {
		public Sugar(Drink inner) {
			super(inner);
		}

		public void doIt() {
			super.doIt();
			doSugar();
		}

		private void doSugar() {
			System.out.print(" Sugar ");
		}
	}

	static class Cinamon extends DecoratedDrink {
		public Cinamon(Drink inner) {
			super(inner);
		}

		public void doIt() {
			super.doIt();
			doCinamon();
		}

		private void doCinamon() {
			System.out.print(" Cinamon ");
		}
	}
	
	static class Rum extends DecoratedDrink {
		public Rum(Drink inner) {
			super(inner);
		}

		public void doIt() {
			super.doIt();
			doRum();
		}

		private void doRum() {
			System.out.print(" Rum ");
		}
	}
	
	static class Honey extends DecoratedDrink {

		public Honey(Drink inner) {
			super(inner);
		}
		
		@Override
		public void doIt() {
			super.doIt();
			System.out.print(" Honey ");
		}
		
	}

	public static void main(String[] args) {
		
		Drink drink = new Milk(new Coffee());
		Drink teaWithHoney = new Honey(new Tea());
		
		teaWithHoney = new Rum(teaWithHoney);
		teaWithHoney = new Sugar(teaWithHoney);
		
		Drink doubleMilk = new Milk(new Milk(new Coffee()));
		
		drink.doIt();
		System.out.println();
		teaWithHoney.doIt();
		System.out.println();
		doubleMilk.doIt();
		System.out.println();
		
		Drink teaWithMilk = new Milk(new Tea());
		teaWithMilk.doIt();
		System.out.println();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}