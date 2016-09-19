package mk.ukim.finki.np.av5;

import java.util.Random;

public class Finalists {
	public static void main(String[] args) {
		boolean[] picked = new boolean[30];
		int totalPicked = 0;
		int[] pickedNum = new int[3];
		while(totalPicked < 3) {
			Random r = new Random();
			int pick = r.nextInt(30);
			if(!picked[pick]) {
				picked[pick] = true;
				pickedNum[totalPicked] = pick + 1;
				totalPicked++;
			}
		}
		for(int p : pickedNum) {
			System.out.println(p);
		}
	}
}
