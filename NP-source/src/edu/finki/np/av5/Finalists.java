package edu.finki.np.av5;

import java.util.Arrays;
import java.util.Random;

public class Finalists {
	public static void main(String[] args) {
		boolean[] picked = new boolean[30];
		int[] winners = new int[3];
		int count = 0;
		Random r = new Random();
		while (count < 3) {

			int number = r.nextInt(30);
			if (!picked[number]) {
				winners[count] = number + 1;
				count++;
				picked[number] = true;
			}
		}
		System.out.println(Arrays.toString(winners));

	}
}
