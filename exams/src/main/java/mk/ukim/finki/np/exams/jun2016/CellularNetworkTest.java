package mk.ukim.finki.np.exams.jun2016;

import java.util.*;

/**
 * Created by tdelev on 29.5.16.
 */
public class CellularNetworkTest {
  public static void main(String[] args) {
    LinkedList<String> ll = new LinkedList<>();
    ListIterator<String> it = ll.listIterator(0);
    it.remove();
    String x = ll.remove(0);
    String y = ll.remove(ll.size() - 1);
    ll.addFirst(y);
    ll.addLast(x);

    Scanner scanner = new Scanner(System.in);
    String name = scanner.nextLine();
    String[] cells = scanner.nextLine().split("\\s+");
    String[] cellIds = new String[cells.length];
    int[] capacities = new int[cells.length];
    for (int i = 0; i < cells.length; ++i) {
      String[] parts = cells[i].split(":");
      cellIds[i] = parts[0];
      capacities[i] = Integer.parseInt(parts[1]);
    }
    CellularNetwork cellularNetwork = new CellularNetwork(name, cellIds, capacities);
    int n = scanner.nextInt();
    scanner.nextLine();
    System.out.println("----- Making calls -----");
    for (int i = 0; i < n; ++i) {
      String line = scanner.nextLine();
      String[] parts = line.split("\\s+");
      try {
        cellularNetwork.makeCall(parts[0], parts[1]);
      } catch (CellFullException e) {
        System.out.println(e.getMessage());
      }
    }
    n = scanner.nextInt();
    scanner.nextLine();
    System.out.println("----- Making handovers -----");
    for (int i = 0; i < n; ++i) {
      String line = scanner.nextLine();
      String[] parts = line.split("\\s+");
      try {
        cellularNetwork.handover(parts[0], parts[1], parts[2]);
      } catch (CellFullException e) {
        System.out.println(e.getMessage());
      }
    }
    System.out.println("===== Find numbers =====");
    n = scanner.nextInt();
    scanner.nextLine();
    for (int i = 0; i < n; ++i) {
      String number = scanner.nextLine();
      cellularNetwork.findNumber(number);
    }
    System.out.println("===== Load =====");
    cellularNetwork.load();
    scanner.close();
  }
}

class CellFullException extends Exception {

  public CellFullException(String cellId) {
    super(String.format("CellFullException: %s", cellId));
  }
}

class CellularNetwork {
  String name;
  Map<String, Cell> cells;
  Map<String, List<Cell>> numbers;

  public CellularNetwork(String name, String[] cellIds, int[] capacities) {
    this.name = name;
    cells = new TreeMap<>();
    for (int i = 0; i < cellIds.length; ++i) {
      Cell cell = new Cell(cellIds[i], capacities[i]);
      cells.put(cellIds[i], cell);
    }
    numbers = new HashMap<>();
  }

  public void makeCall(String cellId, String number) throws CellFullException {
    Cell cell = cells.get(cellId);
    cell.addNumber(number);
    List<Cell> cells = numbers.get(number);
    if (cells == null) {
      cells = new ArrayList<>();
      numbers.put(number, cells);
    }
    cells.add(cell);
  }

  public void handover(String number, String fromCellId, String toCellId) throws CellFullException {
    System.out.println(String.format("Handover: %s -> %s", fromCellId, toCellId));
    Cell from = cells.get(fromCellId);
    Cell to = cells.get(toCellId);
    from.removeNumber(number);
    to.addNumber(number);
    List<Cell> cells = numbers.get(number);
    if (cells == null) {
      cells = new ArrayList<>();
      numbers.put(number, cells);
    }
    cells.add(to);
  }

  public void load() {
    for (Cell cell : cells.values()) {
      System.out.println(cell);
    }
  }

  public void findNumber(String number) {
    List<Cell> cells = numbers.get(number);
    if (cells == null) {
      System.out.println(String.format("Number '%s' not found", number));
      return;
    }
    for (int i = 0; i < cells.size(); ++i) {
      System.out.print(cells.get(i).id);
      if (i != cells.size() - 1) {
        System.out.print(" -> ");
      } else {
        System.out.println();
      }
    }
  }

}

class Cell {
  String id;
  int capacity;
  Set<String> numbers;

  public Cell(String id, int capacity) {
    this.id = id;
    this.capacity = capacity;
    this.numbers = new HashSet<>();
  }

  public void addNumber(String number) throws CellFullException {
    if (isFull()) {
      throw new CellFullException(id);
    }
    numbers.add(number);
  }

  public void removeNumber(String number) {
    numbers.remove(number);
  }

  private boolean isFull() {
    return numbers.size() == capacity;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("ID: %s\n", id));
    double load = numbers.size() * 1.0 / capacity;
    int progress = (int) (load * 10);
    for (int i = 0; i < progress; ++i) {
      sb.append("*");
    }
    sb.append(String.format(" %.2f%%", load * 100));
    return sb.toString();
  }
}
