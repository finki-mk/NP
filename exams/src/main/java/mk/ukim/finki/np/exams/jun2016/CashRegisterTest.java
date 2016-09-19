package mk.ukim.finki.np.exams.jun2016;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by tdelev on 26.5.16.
 */
public class CashRegisterTest {
  public static void main(String[] args) {
    CashRegister cashRegister = new CashRegister();
    cashRegister.readBills(System.in);
    System.out.println("========== Latest 5 bills ==========");
    cashRegister.print(System.out, 0, 5);
    System.out.println("========== Top 5 by price ==========");
    cashRegister.print(System.out, 1, 5);
  }
}

class CashRegister {
  List<FiscalBill> bills;

  public CashRegister() {
    bills = new ArrayList<>();
  }

  public void readBills(InputStream inputStream) {
    Scanner scanner = new Scanner(inputStream);
    FiscalBill fiscalBill = null;
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (line == null || line.length() == 0) break;
      String[] parts = line.split("\\s+");
      if (parts.length > 1) {
        // this is a product item
        ProductItem productItem = new ProductItem(parts[3],
          Double.parseDouble(parts[2]), Integer.parseInt(parts[0]));
        fiscalBill.addProduct(productItem);
      } else {
        if (fiscalBill != null) {
          bills.add(fiscalBill);
        }
        // this is a date
        fiscalBill = new FiscalBill(line);
      }
    }
    scanner.close();
  }

  public void print(OutputStream outputStream, int order, int top) {
    if (order == 0) {
      Collections.sort(bills, new DateComparator());
    } else if (order == 1) {
      Collections.sort(bills, new TotalComparator());
    }
    PrintWriter writer = new PrintWriter(outputStream);
    List<FiscalBill> sublist = bills.subList(0, top);
    for (FiscalBill fb : sublist) {
      writer.println(fb);
    }
    writer.flush();
  }
}

class DateComparator implements Comparator<FiscalBill> {
  @Override
  public int compare(FiscalBill o1, FiscalBill o2) {
    int date = o2.date.compareTo(o1.date);
    if (date == 0) {
      return Double.compare(o1.products.size(), o2.products.size());
    }
    return date;
  }
}

class TotalComparator implements Comparator<FiscalBill> {
  @Override
  public int compare(FiscalBill o1, FiscalBill o2) {
    int c = Double.compare(o2.total, o1.total);
    if (c == 0) {
      return Double.compare(o1.products.size(), o2.products.size());
    }
    return c;
  }
}

class FiscalBill {
  String date;
  List<ProductItem> products;
  double total;

  public FiscalBill(String date) {
    this.date = date;
    products = new ArrayList<>();
    total = 0;
  }

  public void addProduct(ProductItem product) {
    products.add(product);
    total += product.getTotal();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(date);
    sb.append("\n");
    for (ProductItem productItem : products) {
      sb.append(productItem);
      sb.append("\n");
    }
    sb.append(String.format("Total:%29.2f MKD", total));
    return sb.toString();
  }
}

class ProductItem {
  String name;
  double price;
  int quantity;

  public ProductItem(String name, double price, int quantity) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  public double getTotal() {
    return quantity * price;
  }

  @Override
  public String toString() {
    return String.format("%2d x %6.2f %-12s = %8.2f MKD", quantity, price, name, getTotal());
  }
}
