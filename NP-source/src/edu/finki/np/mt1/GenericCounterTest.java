package edu.finki.np.mt1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class GenericCounterTest {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    GenericCounter<Integer> counterInt = new GenericCounter<Integer>();
    scanner.nextLine();
    for (int i = 0; i < n; ++i) {
      int x = scanner.nextInt();
      counterInt.count(x);
    }
    System.out.println("=====INTEGERS=====");
    System.out.println(counterInt);
    n = scanner.nextInt();
    scanner.nextLine();
    GenericCounter<String> counterString = new GenericCounter<String>();
    for (int i = 0; i < n; i++) {
      String s = scanner.nextLine();
      counterString.count(s);
    }
    System.out.println("=====STRINGS=====");
    System.out.println(counterString);
    n = scanner.nextInt();
    scanner.nextLine();
    GenericCounter<Float> counterFloat = new GenericCounter<Float>();
    for (int i = 0; i < n; i++) {
      float f = scanner.nextFloat();
      counterFloat.count(f);
    }
    System.out.println("=====FLOATS=====");
    System.out.println(counterFloat);
    scanner.close();
  }
}

class GenericCounter<T> {
  List<CountableElement<T>> elements;

  GenericCounter() {
    elements = new ArrayList<CountableElement<T>>();
  }

  public void count(T element) {
    for (int i = 0; i < elements.size(); ++i) {
      CountableElement<T> el = elements.get(i);
      if (el.isEqual(element)) {
        el.increment();
        return;
      }
    }
    elements.add(new CountableElement<T>(element));
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < elements.size(); ++i) {
      CountableElement<T> el = elements.get(i);
      sb.append(String.format("%s|%s\n", el.element.toString(), countToStar(el.count)));
    }
    return sb.toString();
  }

  static String countToStar(int c) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < c; ++i) {
      sb.append("*");
    }
    return sb.toString();
  }
}

class CountableElement<T> {
  int count;
  T element;

  public CountableElement(T element) {
    this.element = element;
    count = 1;
  }

  public void increment() {
    ++count;
  }

  public boolean isEqual(T element) {
    return this.element.equals(element);
  }
}
