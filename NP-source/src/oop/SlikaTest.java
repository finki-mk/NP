package oop;

import java.util.Scanner;

public class SlikaTest {
	public static void main(String[] args) throws CloneNotSupportedException {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		GeometriskaSlika[] gsliki = new GeometriskaSlika[n];
		n = scanner.nextInt();
		Krug krug = null;
		Pravoagolnik pravoagolnik = null;
		for (int i = 0; i < n; ++i) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			float radius = scanner.nextFloat();
			krug = new Krug(x, y, radius);
			gsliki[i] = krug;
		}
		n = scanner.nextInt();
		for (int i = 0; i < n; ++i) {
			int x1 = scanner.nextInt();
			int y1 = scanner.nextInt();
			int x2 = scanner.nextInt();
			int y2 = scanner.nextInt();
			pravoagolnik = new Pravoagolnik(x1, y1, x2, y2);
			gsliki[gsliki.length - 1 - i] = pravoagolnik;
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
		Slika slika = new Slika(gsliki);
		System.out.println("VKUPNO PLOSHTINA");
		System.out.printf("%.2f\n", slika.vkupnaPloshtina());
		System.out.println("CLONE");
		Figuri figura = new Figuri(krug, pravoagolnik);
		Figuri cloned = (Figuri)figura.clone();
		figura.translate(5, 10);
        System.out.println("ORIGINAL");
		System.out.println(figura);
		System.out.println("CLONED");
		System.out.println(cloned);
	}
}

interface GeometriskaSlika extends Cloneable {
	float ploshtina();

	void pomesti(int dx, int dy);

	boolean ednakvi(GeometriskaSlika gs);
}

class Pravoagolnik implements GeometriskaSlika {
	private int x1, y1, x2, y2;

	public Pravoagolnik(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	public float ploshtina() {
		return (x2 - x1) * (y1 - y2);
	}

	@Override
	public void pomesti(int dx, int dy) {
		x1 += dx;
		x2 += dx;
		y1 += dy;
		y2 += dy;
	}

	@Override
	public boolean ednakvi(GeometriskaSlika gs) {
		return Math.abs(ploshtina() - gs.ploshtina()) < 0.05;
	}

	@Override
	public String toString() {
		return String.format("P = (%d, %d), W = %d, H = %d", x1, y1, x2
				- x1, y1 - y2);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

class Krug implements GeometriskaSlika, Cloneable {
	private int x, y;
	private float radius;

	public Krug(int x, int y, float radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	@Override
	public float ploshtina() {
		return radius * radius * 3.14f;
	}

	@Override
	public void pomesti(int dx, int dy) {
		x += dx;
		y += dy;
	}

	@Override
	public boolean ednakvi(GeometriskaSlika gs) {
		return Math.abs(ploshtina() - gs.ploshtina()) < 0.05;
	}

	@Override
	public String toString() {
		return String.format("R = %.1f, c = (%d, %d)", radius, x, y);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

class Figuri implements Cloneable {
	private Krug krug;
	private Pravoagolnik pravoagolnik;

	public Figuri(Krug krug, Pravoagolnik pravoagolnik) {
		this.krug = krug;
		this.pravoagolnik = pravoagolnik;
	}

	public void translate(int dx, int dy) {
		krug.pomesti(dx, dy);
		pravoagolnik.pomesti(dx, dy);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Figuri figuri = (Figuri) super.clone();
		figuri.krug = (Krug) krug.clone();
		figuri.pravoagolnik = (Pravoagolnik) pravoagolnik.clone();
		return figuri;
	}

	@Override
	public String toString() {
		return String
				.format("KRUG: %s\nPRAVOAGOLNIK: %s", krug, pravoagolnik);
	}
}

class Slika {
	private GeometriskaSlika[] sliki;

	public Slika(GeometriskaSlika[] sliki) {
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