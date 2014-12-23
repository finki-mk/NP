package edu.finki.np.av5;

import java.util.Random;

public class Finalists {
	public static void main(String[] args) {
		Random r = new Random();
		boolean[] generated = new boolean[30];
		int count = 0;
		while(count != 9) {
			int n = r.nextInt(30) + 1;
			if(!generated[n - 1]) {
				System.out.println(n);
				count++;
				generated[n - 1] = true;
			}
		}
	}
}
