package mk.ukim.finki.np.mt1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Transactions 2015/11/14 first midterm
 */
public class TransactionsTest {
    public static void main(String[] args) {
        Transactions transactions = new Transactions();
        List<String> invalid = transactions.readData(System.in);
        System.out.println("=== INVALID DATES ===");
        invalid.forEach(System.out::println);
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
        transactions = new ArrayList<>();
    }

    static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    public static final Predicate<String> validDate = line -> {
        String[] parts = line.split(";");
        String[] date = parts[0].split("/");
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);
        if (day < 0) return false;
        if (isLeapYear(year)) {
            if (month == 2) {
                if (day > 29) return false;
            } else {
                if (day > Constants.DAYS[month - 1]) return false;
            }
        } else {
            if (day > Constants.DAYS[month - 1]) return false;
        }
        return true;
    };

    public List<String> readData(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Map<Boolean, List<String>> partitions = reader.lines()
                .collect(Collectors.partitioningBy(validDate, Collectors.toList()));
        transactions = partitions.get(true).stream()
                .map(Transaction::fromString)
                .collect(Collectors.toList());

        return partitions.get(false).stream()
                .map(line -> line.split(";")[0])
                .collect(Collectors.toList());
    }

    public void print(int year) {
        List<Transaction> filtered = transactions.stream()
                .sorted()
                .filter(each -> each.year == year)
                .collect(Collectors.toList());
        filtered.forEach(System.out::println);
        double total = filtered.stream()
                .mapToDouble(Transaction::toMKD)
                .sum();

        System.out.printf("Balance: %10d MKD\n", (int) Math.round(total));
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

    public static Transaction fromString(String line) {
        String[] parts = line.split(";");
        String[] date = parts[0].split("/");
        return new Transaction(Integer.parseInt(date[0]), Integer.parseInt(date[1]),
                Integer.parseInt(date[2]), parts[2], Float.parseFloat(parts[1]));
    }

    public float toMKD() {
        if (this.currency.equalsIgnoreCase(Transaction.EUR)) {
            return value * Constants.EUR;
        } else if (this.currency.equalsIgnoreCase(Transaction.USD)) {
            return value * Constants.USD;
        } else return value;
    }
}
