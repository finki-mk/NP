package edu.finki.np.av3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Scoreboard {
	private static final String FILENAME = "scores.dat";
	private Score[] scores;
	private int total;
	private static final int N = 10;

	public Scoreboard() {
		load();
		if (scores == null) {
			scores = new Score[N];
			this.total = 0;
		}
	}
	
	public void addScore(Score score) {
		int i;
		for(i = 0; i < total; i++) {
			if(scores[i].getPoints() < score.getPoints()) {
				break;
			}
		}
		if(i != total) {
			for(int j = total + 1; j > i; j--) {
				if(j < N) {
					scores[j] = scores[j - 1];
				}
			}
			scores[i] = score;
		} else {
			if(total < N) {
				scores[total++] = score;
			}
		}
	}

	private void load() {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(FILENAME));
			scores = (Score[]) ois.readObject();
			total = ois.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(FILENAME));
			oos.writeObject(scores);
			oos.writeInt(total);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < total; i++) {
			sb.append(scores[i]);
			sb.append('\n');
		}
		return sb.toString();
	}

}
