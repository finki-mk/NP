package edu.finki.np.av3;

import java.io.Serializable;

public class Score implements Serializable {
	private String name;
	private int points;

	public Score(String name, int points) {
		this.name = name;
		this.points = points;
	}
	
	public int getPoints() {
		return points;
	}

	@Override
	public String toString() {
		return String.format("%s %d", name, points);
	}

}
