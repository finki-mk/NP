package edu.finki.np.ex3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class PlazhaTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		Plazha plazha = new Plazha(n);
		n = scanner.nextInt();
		scanner.nextLine();
		int smetka = -1;
		for (int i = 0; i < n; ++i) {
			int chador = scanner.nextInt();
			String ime = scanner.next();
			int kolichina = scanner.nextInt();
			double cena = scanner.nextDouble();
			Narachka narachka = new Narachka(ime, kolichina, cena);
			plazha.narachaj(chador, narachka);
			smetka = chador;
		}
		System.out.println("Smetka " + smetka);
		plazha.smetka(smetka);
		System.out.println("Slobodni chadori");
		plazha.slobodni();
	}
}

class Narachka {
	private String ime;
	private int kolichina;
	private double cena;

	public Narachka(String ime, int kolichina, double cena) {
		this.ime = ime;
		this.kolichina = kolichina;
		this.cena = cena;
	}

	public double presmetajCena() {
		return this.kolichina * this.cena;
	}

}

class Chador {
	private int broj;
	private List<Narachka> narachki;

	public Chador(int broj) {
		this.broj = broj;
		narachki = new ArrayList<Narachka>();
	}

	public void dodadiNarachka(Narachka n) {
		narachki.add(n);
	}

	public double smetka() {
		double suma = 0;
		for (Narachka n : narachki) {
			suma += n.presmetajCena();
		}
		narachki.clear();
		return suma;
	}

	public boolean sloboden() {
		return narachki.size() == 0;
	}
}

class Plazha {
	private Map<Integer, Chador> chadori;

	public Plazha(int n) {
		chadori = new HashMap<Integer, Chador>();
		for (int i = 1; i <= n; ++i) {
			chadori.put(i, new Chador(i));
		}
	}

	public void narachaj(int i, Narachka n) {
		Chador chador = chadori.get(i);
		chador.dodadiNarachka(n);
	}

	public double smetka(int i) {
		return chadori.get(i).smetka();
	}

	public void slobodni() {
		for (Entry<Integer, Chador> entry : chadori.entrySet()) {
			if (entry.getValue().sloboden()) {
				System.out.println(entry.getKey());
			}
		}
	}
}
