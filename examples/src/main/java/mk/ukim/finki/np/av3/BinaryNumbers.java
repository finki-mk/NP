package mk.ukim.finki.np.av3;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

public class BinaryNumbers {
	private static void generateFile(int n) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("numbers.dat"));
			Random random = new Random();
			for (int i = 0; i < n; i++) {
				int next = random.nextInt(1000);
				oos.writeInt(next);
			}
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static double findNumbersAvarage() {
		ObjectInputStream ois = null;
		int total = 0;
		double sum = 0;
		try {
			ois = new ObjectInputStream(new FileInputStream("numbers.dat"));
			try {
				while (true) {
					int num = ois.readInt();
					sum += num;
					total++;
				}
			} catch (EOFException e) {
				System.out.println("All numbers are read");
			}
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sum / total;
	}

	public static void main(String[] args) {
		generateFile(1000);
		double avg = findNumbersAvarage();
		System.out.println("Average: " + avg);
	}
}
