package oop;

import java.util.Scanner;

public class FiguriTest {
	public static void main(String[] args) throws CloneNotSupportedException {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		GeometriskaFigura[] gsliki = new GeometriskaFigura[n];
		n = scanner.nextInt();
		Triagolnik triagolnik = null;
		Kvadrat kvadrat = null;
		for (int i = 0; i < n; ++i) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			int c = scanner.nextInt();
			triagolnik = new Triagolnik(a, b, c);
			gsliki[i] = triagolnik;
		}
		n = scanner.nextInt();
		for (int i = 0; i < n; ++i) {
			int a = scanner.nextInt();
			kvadrat = new Kvadrat(a);
			gsliki[gsliki.length - 1 - i] = kvadrat;
		}
		System.out.println("PLOSHTINA");
		for (int i = 0; i < gsliki.length; ++i) {
			System.out.printf("%.2f\n", gsliki[i].ploshtina());
		}
		System.out.println("EDNAKVI");
		System.out.println(gsliki[0]);
		System.out.println(gsliki[gsliki.length - 1]);
		System.out.println(gsliki[0].ednakvi(gsliki[gsliki.length - 1]) ? "DA"
				: "NE");
		GSlika slika = new GSlika(gsliki);
		System.out.println("VKUPNO PLOSHTINA");
		System.out.printf("%.2f\n", slika.vkupnaPloshtina());
		System.out.println("CLONE");
		Par par = new Par(triagolnik, kvadrat);
		Par cloned = (Par)par.clone();
		par.zgolemi(5);
        System.out.println("ORIGINAL");
		System.out.println(par);
		System.out.println("CLONED");
		System.out.println(cloned);
	}
}

interface GeometriskaFigura {
	float ploshtina();

	void zgolemi(int d);

	boolean ednakvi(GeometriskaFigura gf);
}

class Triagolnik implements GeometriskaFigura {
	private int a, b, c;

	public Triagolnik(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public float ploshtina() {
		float s = (a + b + c) / 2.f;
		return (float)Math.sqrt(s * (s - a) * (s - b) * (s - c));
	}

	@Override
	public void zgolemi(int d) {
		a += d;
		b += d;
		c += d;
	}

	@Override
	public boolean ednakvi(GeometriskaFigura gf) {
		return Math.abs(ploshtina() - gf.ploshtina()) < 0.1;
	}

	@Override
	public String toString() {
		return String.format("T (a = %d, b = %d, c = %d)", a, b, c);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

class Kvadrat implements GeometriskaFigura, Cloneable {
	private int a;

	public Kvadrat(int a) {
		this.a = a;
	}

	@Override
	public float ploshtina() {
		return a * a;
	}

	@Override
	public void zgolemi(int d) {
		a += d;
	}

	@Override
	public boolean ednakvi(GeometriskaFigura gf) {
		return Math.abs(ploshtina() - gf.ploshtina()) < 0.1;
	}

	@Override
	public String toString() {
		return String.format("K (a = %d)", a);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

class Par implements Cloneable {
	private Triagolnik triagolnik;
	private Kvadrat kvadrat;

	public Par(Triagolnik triagolnik, Kvadrat kvadrat) {
		this.triagolnik = triagolnik;
		this.kvadrat = kvadrat;
	}

	public void zgolemi(int d) {
		triagolnik.zgolemi(d);
		kvadrat.zgolemi(d);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Par par = (Par) super.clone();
		par.triagolnik = (Triagolnik) triagolnik.clone();
		par.kvadrat = (Kvadrat) kvadrat.clone();
		return par;
	}

	@Override
	public String toString() {
		return String
				.format("TRIAGOLNIK: %s\nKVADRAT: %s", triagolnik, kvadrat);
	}
}

class GSlika {
	private GeometriskaFigura[] sliki;

	public GSlika(GeometriskaFigura[] sliki) {
		this.sliki = sliki;
	}

	public float vkupnaPloshtina() {
		float p = 0;
		for (int i = 0; i < sliki.length; ++i) {
			p += sliki[i].ploshtina();
		}
		return p;
	}
}