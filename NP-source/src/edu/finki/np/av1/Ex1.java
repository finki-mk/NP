package edu.finki.np.av1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Ex1 {
	public static void main(String[] args) {
		IntStream.range(0, 10)
		.forEach(x -> System.out.println(x));
		
		for(int i = 0; i < 10; ++i) {
			System.out.println(i);
		}
		
		
		
		for (int a = 0; a < 1000; a++) {
			for (int b = a + 1; b < 1000; b++) {
				if ((a * a + b * b + 1) % (a + b) == 0) {
					System.out.println(String.format("%d %d", a, b));
				}
			}
		}
	}
}
