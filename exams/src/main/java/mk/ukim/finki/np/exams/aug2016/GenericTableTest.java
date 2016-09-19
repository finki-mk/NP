package mk.ukim.finki.np.exams.aug2016;

import java.util.*;

/**
 * Generic table test
 */
public class GenericTableTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GenericTable<String, Integer> stringTable = new GenericTable<>();
        GenericTable<Integer, Double> integerTable = new GenericTable<>();
        GenericTable<Key, Float> keyTable = new GenericTable<>();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String[] parts = scanner.nextLine().split("\\s+");
            Integer[] values = new Integer[parts.length - 1];
            for (int j = 0; j < values.length; ++j) {
                values[j] = Integer.parseInt(parts[j + 1]);
            }
            stringTable.addRow(parts[0], values);
        }
        System.out.println("=== STRING TABLE ===");
        System.out.println(stringTable);
        String k = String.format("row%d", n / 2);
        System.out.printf("MAX (%s): %.2f\n", k, stringTable.max(k));
        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String[] parts = scanner.nextLine().split("\\s+");
            Double[] values = new Double[parts.length - 1];
            for (int j = 0; j < values.length; ++j) {
                values[j] = Double.parseDouble(parts[j + 1]);
            }
            integerTable.addRow(Integer.parseInt(parts[0]), values);
        }
        System.out.println("=== INTEGER TABLE ===");
        System.out.println(integerTable);
        System.out.printf("MAX (%d): %.2f\n", n / 2, integerTable.max(n / 2));
        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String[] parts = scanner.nextLine().split("\\s+");
            Float[] values = new Float[parts.length - 1];
            for (int j = 0; j < values.length; ++j) {
                values[j] = Float.parseFloat(parts[j + 1]);
            }
            String[] keys = parts[0].split(":");
            Key key = new Key(Integer.parseInt(keys[0]), keys[1]);
            keyTable.addRow(key, values);
        }
        System.out.println("=== KEY TABLE ===");
        System.out.println(keyTable);
        Key key = new Key(1, "a");
        System.out.printf("MAX (%s): %.2f\n", key, keyTable.max(key));
        scanner.close();

    }
}

class Key implements Comparable<Key> {
    int index;
    String name;

    public Key(int index, String name) {
        this.index = index;
        this.name = name;
    }

    @Override
    public int compareTo(Key o) {
        return Integer.compare(index, o.index);
    }

    @Override
    public String toString() {
        return String.format("%d (%s)", index, name);
    }
}

class GenericTable<RowKey extends Comparable, Value extends Number> {
    Map<RowKey, List<Value>> rows;

    public GenericTable() {
        rows = new TreeMap<>();
    }

    public void addRow(RowKey key, Value... values) {
        List<Value> row = rows.get(key);
        if (row == null) {
            row = new ArrayList<>();
        }
        row.addAll(Arrays.asList(values));
        rows.put(key, row);
    }

    public double max(RowKey key) {
        List<Value> row = rows.get(key);
        double max = Double.MIN_VALUE;
        for (Number number : row) {
            max = Math.max(max, number.doubleValue());
        }
        return max;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (RowKey key : rows.keySet()) {
            List<Value> row = rows.get(key);
            res.append(String.format("%s: ", key));
            for (Value value : row) {
                res.append(String.format("%6.2f\t", value.doubleValue()));
            }
            res.append('\n');
        }
        return res.toString();
    }

}
