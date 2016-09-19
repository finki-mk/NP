package mk.ukim.finki.np.mt1;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Transactions 2015/11/14 first midterm
 */
public class TransactionsTest {
  public static void main(String[] args) {
    Transactions transactions = new Transactions();
    List<String> invalid = transactions.readData(System.in);
    System.out.println("=== INVALID DATES ===");
    for (String s : invalid) {
      System.out.println(s);
    }
    System.out.println("=== TRANSACTIONS 2001 ===");
    transactions.print(2001);
    System.out.println("=== TRANSACTIONS 2007 ===");
    transactions.print(2007);
    System.out.println("=== TRANSACTIONS 2013 ===");
    transactions.print(2013);
  }
}

class Constants {
  public static final float USD = 50;
  public static final float EUR = 61.5f;
  public static final int DAYS[] = {
    31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
  };
}

class Transactions {
  List<Transaction> transactions;

  public Transactions() {
    transactions = new ArrayList<Transaction>();
  }

  static boolean isLeapYear(int year) {
    return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
  }

  public List<String> readData(InputStream inputStream) {
    Scanner scanner = new Scanner(inputStream);
    List<String> invalid = new ArrayList<String>();
    while (scanner.hasNextLine()) {
      boolean ok = true;
      String line = scanner.nextLine();
      String[] parts = line.split(";");
      String[] date = parts[0].split("/");
      int year = Integer.parseInt(date[0]);
      int month = Integer.parseInt(date[1]);
      int day = Integer.parseInt(date[2]);
      if (day < 0) ok = false;
      if (isLeapYear(year)) {
        if (month == 2) {
          if (day > 29) ok = false;
        } else {
          if (day > Constants.DAYS[month - 1]) ok = false;
        }
      } else {
        if (day > Constants.DAYS[month - 1]) ok = false;
      }
      if (ok) {
        Transaction transaction = new Transaction(year, month, day, parts[2], Float.parseFloat(parts[1]));
        transactions.add(transaction);
      } else {
        invalid.add(parts[0]);
      }
    }
    return invalid;
  }

  public void print(int year) {
    float total = 0;
    Collections.sort(transactions);
    for (Transaction t : transactions) {
      if (t.year == year) {
        System.out.println(t);
        if (t.currency.equalsIgnoreCase(Transaction.EUR)) {
          total += t.value * Constants.EUR;
        } else if (t.currency.equalsIgnoreCase(Transaction.USD)) {
          total += t.value * Constants.USD;
        } else {
          total += t.value;
        }
      }
    }
    System.out.printf("Balance: %10.2f MKD\n", total);
  }
}

class Transaction implements Comparable<Transaction> {
  static final String USD = "USD";
  static final String EUR = "EUR";
  int year;
  int month;
  int day;
  String currency;
  float value;

  public Transaction(int year, int month, int day, String currency, float value) {
    this.year = year;
    this.month = month;
    this.day = day;
    this.currency = currency;
    this.value = value;
  }

  @Override
  public int compareTo(Transaction o) {
    if (year != o.year) return Integer.compare(year, o.year);
    else if (month != o.month) return Integer.compare(month, o.month);
    else if (day != o.day) return Integer.compare(day, o.day);
    return Float.compare(value, o.value);
  }

  @Override
  public String toString() {
    return String.format("%04d/%02d/%02d %10.2f %s", year, month, day, value, currency);
  }
}
