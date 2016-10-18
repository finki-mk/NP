package mk.ukim.finki.np.av5;

import java.io.*;
import java.util.Random;

public class BinaryNumbers {
    static final String FILE_NAME = "examples/data/numbers.dat";

    public static void main(String[] args) {
        generateFile(1000);
        double avg = findNumbersAverage();
        System.out.println("Average: " + avg);
    }

    private static void generateFile(int n) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            Random random = new Random();
            for (int i = 0; i < n; i++) {
                int next = random.nextInt(1000);
                oos.writeInt(next);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static double findNumbersAverage() {
        int total = 0;
        double sum = 0;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            try {
                while (true) {
                    int num = ois.readInt();
                    sum += num;
                    total++;
                }
            } catch (EOFException e) {
                System.out.println("All numbers are read");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sum / total;
    }

}
