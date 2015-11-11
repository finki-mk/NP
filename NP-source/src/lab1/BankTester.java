package lab1;

import java.util.Arrays;
import java.util.Scanner;

public class BankTester {

  public static void main(String[] args) {
    Scanner jin = new Scanner(System.in);
    String testType = jin.nextLine();
    if (testType.equals("typical_usage")) {
      testTypicalUsage(jin);
    } else if (testType.equals("equals")) {
      testEquals();
    }
    jin.close();
  }

  private static void testEquals() {
    Account a1 = new Account("Andrej", "20.00$");
    Account a2 = new Account("Andrej", "20.00$");
    Account a3 = new Account("Andrej", "30.00$");
    Account a4 = new Account("Gajduk", "20.00$");
    if (!(a1.equals(a1) && !a1.equals(a2) && !a2.equals(a1) && !a3.equals(a1)
      && !a4.equals(a1)
      && !a1.equals(null))) {
      System.out.println("Your account equals method does not work properly.");
      return;
    }
    if (a1.getID() == a2.getID() || a1.getID() == a3.getID() || a1.getID() == a4.getID()
      || a2.getID() == a3.getID() || a2.getID() == a4.getID() || a3.getID() == a4.getID()) {
      System.out.println("Different accounts have the same IDS. This is not allowed");
      return;
    }
    FlatAmountProvisionTransaction fa1 = new FlatAmountProvisionTransaction(10, 20, "20.00$", "10.00$");
    FlatAmountProvisionTransaction fa2 = new FlatAmountProvisionTransaction(10, 20, "20.00$", "10.00$");
    FlatAmountProvisionTransaction fa3 = new FlatAmountProvisionTransaction(20, 10, "20.00$", "10.00$");
    FlatAmountProvisionTransaction fa4 = new FlatAmountProvisionTransaction(10, 20, "50.00$", "50.00$");
    FlatAmountProvisionTransaction fa5 = new FlatAmountProvisionTransaction(30, 40, "20.00$", "10.00$");
    FlatPercentProvisionTransaction fp1 = new FlatPercentProvisionTransaction(10, 20, "20.00$", 10);
    FlatPercentProvisionTransaction fp2 = new FlatPercentProvisionTransaction(10, 20, "20.00$", 10);
    FlatPercentProvisionTransaction fp3 = new FlatPercentProvisionTransaction(20, 10, "20.00$", 10);
    FlatPercentProvisionTransaction fp4 = new FlatPercentProvisionTransaction(10, 20, "50.00$", 10);
    FlatPercentProvisionTransaction fp5 = new FlatPercentProvisionTransaction(10, 20, "20.00$", 30);
    FlatPercentProvisionTransaction fp6 = new FlatPercentProvisionTransaction(30, 40, "20.00$", 10);
    if (fa1.equals(fa1) &&
      !fa2.equals(null) &&
      fa2.equals(fa1) &&
      fa1.equals(fa2) &&
      fa1.equals(fa3) &&
      !fa1.equals(fa4) &&
      !fa1.equals(fa5) &&
      !fa1.equals(fp1) &&
      fp1.equals(fp1) &&
      !fp2.equals(null) &&
      fp2.equals(fp1) &&
      fp1.equals(fp2) &&
      fp1.equals(fp3) &&
      !fp1.equals(fp4) &&
      !fp1.equals(fp5) &&
      !fp1.equals(fp6)) {
      System.out.println("Your transactions equals methods do not work properly.");
      return;
    }
    Account accounts[] = new Account[]{a1, a2, a3, a4};
    Account accounts1[] = new Account[]{a2, a1, a3, a4};
    Account accounts2[] = new Account[]{a1, a2, a3};
    Account accounts3[] = new Account[]{a1, a2, a3, a4};

    Bank b1 = new Bank("Test", accounts);
    Bank b2 = new Bank("Test", accounts1);
    Bank b3 = new Bank("Test", accounts2);
    Bank b4 = new Bank("Sample", accounts);
    Bank b5 = new Bank("Test", accounts3);
    if (!(b1.equals(b1) &&
      !b1.equals(null) &&
      !b1.equals(b2) &&
      !b2.equals(b1) &&
      !b1.equals(b3) &&
      !b3.equals(b1) &&
      !b1.equals(b4) &&
      b1.equals(b5))) {
      System.out.println("Your bank equals method do not work properly.");
      return;
    }
    accounts[2] = a1;
    if (!b1.equals(b5)) {
      System.out.println("Your bank equals method do not work properly.");
      return;
    }
    long from_id = a2.getID();
    long to_id = a3.getID();
    Transaction t = new FlatAmountProvisionTransaction(from_id, to_id, "3.00$", "3.00$");
    b1.makeTransaction(t);
    if (b1.equals(b5)) {
      System.out.println("Your bank equals method do not work properly.");
      return;
    }
    b5.makeTransaction(t);
    if (!b1.equals(b5)) {
      System.out.println("Your bank equals method do not work properly.");
      return;
    }
    System.out.println("All your equals methods work properly.");
  }

  private static void testTypicalUsage(Scanner jin) {
    String bank_name = jin.nextLine();
    int num_accounts = jin.nextInt();
    jin.nextLine();
    Account accounts[] = new Account[num_accounts];
    for (int i = 0; i < num_accounts; ++i)
      accounts[i] = new Account(jin.nextLine(), jin.nextLine());
    Bank bank = new Bank(bank_name, accounts);
    while (true) {
      String line = jin.nextLine();
      if (line.equals("stop")) return;
      else if (line.equals("transaction")) {
        String descrption = jin.nextLine();
        String amount = jin.nextLine();
        String parameter = jin.nextLine();
        int from_idx = jin.nextInt();
        int to_idx = jin.nextInt();
        jin.nextLine();
        Transaction t = getTransaction(descrption, from_idx, to_idx, amount, parameter, bank);
        System.out.println("Transaction amount:" + t.getAmount());
        System.out.println("transaction description:" + t.getDescription());
        System.out.println("Transaction succesfull? " + bank.makeTransaction(t));
      } else if (line.equals("print")) {
        System.out.println(bank.toString());
        System.out.println("Total provisions:" + bank.totalProvision());
        System.out.println("Total transfers:" + bank.totalTransfers());
        System.out.println();
      }
    }
  }

  private static Transaction getTransaction(String description, int from_idx, int to_idx, String amount, String o, Bank bank) {
    if (description.equals("FlatAmount"))
      return new FlatAmountProvisionTransaction(bank.accounts[from_idx].getID(), bank.accounts[to_idx].getID(), amount, o);
    else if (description.equals("FlatPercent"))
      return new FlatPercentProvisionTransaction(bank.accounts[from_idx].getID(), bank.accounts[to_idx].getID(), amount, Integer.parseInt(o));
    return null;
  }
}

class Account {
  private String name;
  private String balance;
  private long id;

  public Account(String name, String balance) {
    this.name = name;
    this.balance = balance;
    id = (long) (Math.random() * 1000000);
  }

  public String getBalance() {
    return balance;
  }

  public String getName() {
    return name;
  }

  public long getID() {
    return id;
  }

  public Account copy() {
    Account account = new Account(name, balance);
    account.id = this.id;
    return account;
  }

  public void setBalance(String balance) {
    this.balance = balance;
  }

  @Override
  public String toString() {
    return String.format("Name:%s\nBalance:%s\n\n", name, balance);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Account)) return false;

    Account account = (Account) o;

    if (id != account.id) return false;
    if (!name.equals(account.name)) return false;
    return balance.equals(account.balance);

  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + balance.hashCode();
    result = 31 * result + (int) (id ^ (id >>> 32));
    return result;
  }
}

abstract class Transaction {
  protected final long fromId;
  protected final long toId;
  protected final String description;
  protected final String amount;

  public Transaction(long fromId, long toId, String description, String amount) {
    this.fromId = fromId;
    this.toId = toId;
    this.description = description;
    this.amount = amount;
  }

  public String getAmount() {
    return amount;
  }

  public long getFromAccountID() {
    return fromId;
  }

  public long getToAccountID() {
    return toId;
  }

  public String getDescription() {
    return description;
  }

  abstract String getProvision();

  @Override
  public String toString() {
    return String.format("Amount:%s\nProvision:%s\nDescription:%s\nFrom:%d\nTo:%d\n",
      amount, getProvision(), description, fromId, toId);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Transaction)) return false;
    Transaction that = (Transaction) o;
    if (fromId != that.fromId) return false;
    if (toId != that.toId) return false;
    if (description != null ? !description.equals(that.description) : that.description != null) return false;
    return !(amount != null ? !amount.equals(that.amount) : that.amount != null);

  }

  @Override
  public int hashCode() {
    int result = (int) (fromId ^ (fromId >>> 32));
    result = 31 * result + (int) (toId ^ (toId >>> 32));
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (amount != null ? amount.hashCode() : 0);
    return result;
  }
}

class FlatAmountProvisionTransaction extends Transaction {
  private String provision;

  public FlatAmountProvisionTransaction(long fromId, long toId, String amount, String provision) {
    super(fromId, toId, "FlatAmount", amount);
    this.provision = provision;
  }

  @Override
  String getProvision() {
    return provision;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof FlatAmountProvisionTransaction)) return false;
    if (!super.equals(o)) return false;
    FlatAmountProvisionTransaction that = (FlatAmountProvisionTransaction) o;
    return !(provision != null ? !provision.equals(that.provision) : that.provision != null) && super.equals(that);

  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (provision != null ? provision.hashCode() : 0);
    return result;
  }
}

class FlatPercentProvisionTransaction extends Transaction {
  private int percent;

  public FlatPercentProvisionTransaction(long fromId, long toId, String amount, int percent) {
    super(fromId, toId, "FlatPercent", amount);
    this.percent = percent;
  }

  @Override
  String getProvision() {
    double amount = Bank.moneyToDouble(this.amount);
    return Bank.doubleToMoney((int) amount * percent / 100.0);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof FlatPercentProvisionTransaction)) return false;
    if (!super.equals(o)) return false;
    FlatPercentProvisionTransaction that = (FlatPercentProvisionTransaction) o;
    return percent == that.percent && super.equals(that);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + percent;
    return result;
  }
}

class Bank {
  private String name;
  Account[] accounts;
  private double totalTransfers;
  private double totalProvision;

  public Bank(String name, Account accounts[]) {
    this.name = name;
    this.accounts = new Account[accounts.length];
    for (int i = 0; i < accounts.length; ++i) {
      this.accounts[i] = accounts[i].copy();
    }
    totalProvision = 0;
    totalTransfers = 0;
  }

  public boolean makeTransaction(Transaction transaction) {
    Account fromAccount = null;
    Account toAccount = null;

    for (int i = 0; i < accounts.length; ++i) {
      if (accounts[i].getID() == transaction.getFromAccountID()) {
        if (fromAccount == null) {
          fromAccount = accounts[i];
        }
      }
      if (accounts[i].getID() == transaction.getToAccountID()) {
        if (toAccount == null) {
          toAccount = accounts[i];
        }
      }
    }
    if (fromAccount == null || toAccount == null) return false;
    double amount = moneyToDouble(transaction.getAmount());
    double provision = moneyToDouble(transaction.getProvision());
    double total = amount + provision;
    double fromBalance = moneyToDouble(fromAccount.getBalance());
    double toBalance = moneyToDouble(toAccount.getBalance());
    if (total > fromBalance) {
      return false;
    }
    fromBalance -= total;
    if (fromAccount.getID() == toAccount.getID()) {
      toBalance = fromBalance;
    }
    toBalance += amount;
    totalTransfers += amount;
    totalProvision += provision;
    fromAccount.setBalance(doubleToMoney(fromBalance));
    toAccount.setBalance(doubleToMoney(toBalance));
    return true;
  }

  static double moneyToDouble(String money) {
    return Double.parseDouble(money.substring(0, money.length() - 1));
  }

  static String doubleToMoney(double money) {
    return String.format("%.2f$", money);
  }

  public String totalProvision() {
    return doubleToMoney(totalProvision);
  }

  public String totalTransfers() {
    return doubleToMoney(totalTransfers);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Account account : accounts) {
      sb.append(account.toString());
    }
    return String.format("%s\n\n%s", name, sb.toString());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Bank)) return false;
    Bank bank = (Bank) o;
    if (Double.compare(bank.totalTransfers, totalTransfers) != 0) return false;
    if (Double.compare(bank.totalProvision, totalProvision) != 0) return false;
    if (!name.equals(bank.name)) return false;
    return Arrays.equals(accounts, bank.accounts);
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = name.hashCode();
    result = 31 * result + Arrays.hashCode(accounts);
    temp = Double.doubleToLongBits(totalTransfers);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(totalProvision);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }
}

