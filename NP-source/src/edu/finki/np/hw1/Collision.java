package edu.finki.np.hw1;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by tdelev on 8.12.15.
 */
public class Collision {
  static final int TRIALS = 1000;

  public double probability(int[] assignments, int ids) {
    int s1 = 0;
    int s2 = 0;
    Random random = new Random();
    for (int i = 0; i < TRIALS; ++i) {
      Set<Integer> unique1 = new HashSet<>();
      Set<Integer> unique2 = new HashSet<>();
      int collision1 = 0;
      int collision2 = 0;
      for (int j = 0; j < assignments.length; ++j) {
        Set<Integer> memory = new HashSet<>();
        for (int k = 0; k < assignments[j]; ++k) {
          int id = random.nextInt(ids);
          if (!unique1.add(id)) {
            collision1++;
          }

          while (!memory.add(id)) {
            id = random.nextInt(ids);
          }
          if (!unique2.add(id)) {
            collision2++;
          }
        }
      }
      if (collision1 > 0) {
        ++s1;
      }
      if (collision2 > 0) {
        ++s2;
      }
    }
    double p1 = 1 - (s1 * 1.0 / TRIALS);
    double p2 = 1 - (s2 * 1.0 / TRIALS);

    return p2 - p1;
  }

  public static void main(String[] args) {
    System.out.println(new Collision().probability(new int[]
        {10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000},
      2147483647));
  }
}
