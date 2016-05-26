package edu.finki.np.hw1;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by tdelev on 8.12.15.
 */
public class Archer {
  public static final int TRIALS = 10000;

  public void simulate(double p1, double p2, double p3) {
    Random random = new Random();
    double[] p = new double[]{p1, p2, p3};
    int event1 = 0;
    int event2 = 0;
    for (int i = 0; i < TRIALS; ++i) {
      int pick = random.nextInt(p.length);
      double v = p[pick];
      double x1 = random.nextDouble();
      if (x1 < v) {
        ++event1;
      }
      for (int j = 0; j < 3; ++j) {
        double x2 = random.nextDouble();
        if (x2 < p[j]) {
          ++event2;
          break;
        }
      }
    }
    System.out.printf("%d%%\n", (int) (event1 * 100. / TRIALS));
    System.out.printf("%d%%\n", (int) (event2 * 100. / TRIALS));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    double p1 = scanner.nextDouble();
    double p2 = scanner.nextDouble();
    double p3 = scanner.nextDouble();
    new Archer().simulate(p1, p2, p3);
    scanner.close();
  }
}
