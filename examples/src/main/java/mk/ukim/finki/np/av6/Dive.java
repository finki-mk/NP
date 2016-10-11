package mk.ukim.finki.np.av6;

import java.util.ArrayList;
import java.util.Scanner;

public class Dive {
	private static final double COEFICIENT = 0.6;
	private ArrayList<Double> scores;
	private double difficulty;

	public Dive(double difficulty) {
		this.difficulty = difficulty;
		this.scores = new ArrayList<Double>();
	}

	public void addScore(double score) {
		scores.add(score);
	}

	public double getFinalScore() {
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		int maxIndex = -1;
		int minIndex = -1;
		for (int i = 0; i < scores.size(); i++) {
			double d = scores.get(i);
			if (d < min) {
				min = d;
				minIndex = i;
			}
			if (d > max) {
				max = d;
				maxIndex = i;
			}
		}
		double sum = 0;
		for (int i = 0; i < scores.size(); i++) {
			double d = scores.get(i);
			if (i != minIndex && i != maxIndex) {
				sum += d;
			}
		}
		return sum * difficulty * COEFICIENT;
	}

	public static void main(String[] args) {
		double difficulty;
		System.out.println("Difficulty: ");
		Scanner scanner = new Scanner(System.in);
		difficulty = scanner.nextDouble();
		Dive dive = new Dive(difficulty);
		for (int i = 0; i < 7; i++) {
			System.out.println(String.format("Judge %d: ", i + 1));
			double score = scanner.nextDouble();
			dive.addScore(score);
		}
		System.out.println(String.format("Result: %.2f", dive.getFinalScore()));
	}
}
