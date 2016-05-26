package edu.finki.np.hw1;

import java.util.Random;

/**
 * Created by tdelev on 8.12.15.
 */
public class ProductsTest {
  static final int TRIALS = 10000;

  public static void main(String[] args) {
    int[] numbers = new int[100];
    for (int i = 0; i < numbers.length; ++i) {
      if (i >= 90) {
        numbers[i] = 1;
      } else {
        numbers[i] = 0;
      }
    }
    Random random = new Random();
    int v1 = 0;
    int v2 = 0;
    int picked;
    for (int i = 0; i < TRIALS; ++i) {
      picked = 0;
      for (int j = 0; j < 15; ++j) {
        picked += numbers[random.nextInt(numbers.length)];
      }
      if (picked == 1) {
        ++v1;
      }
      if (picked >= 2) {
        ++v2;
      }
    }
    System.out.printf("V1: %f\n", v1 * 1.0 / TRIALS);
    System.out.printf("V2: %f\n", v2 * 1.0 / TRIALS);
  }
}
