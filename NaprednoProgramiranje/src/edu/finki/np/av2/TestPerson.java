package edu.finki.np.av2;

public class TestPerson {

	private Person p = new Person();

	public static void main(String[] args) {
		Person q = new Person();
		// System.out.println(p);
		System.out.println(q);
		System.out.println(q.NO_SSN);
		// System.out.println(q.SSN);
		System.out.println(q.name);
		System.out.println(Person.NO_SSN); //
		// System.out.println(Person.SSN);

	}

}

class Person {
	public static final int NO_SSN = -1;
	private int SSN = 0;
	String name = null;
}
