package edu.finki.np.av4;

public final class Waypoint {
	private double x;
	private double y;
	private int timestamp;

	public Waypoint(double x, double y, int timestamp) {
		this.x = x;
		this.y = y;
		this.timestamp = timestamp;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getTimestamp() {
		return timestamp;
	}
	
	public int getTime(Waypoint point) {
		return timestamp - point.timestamp;
	}

	public double getDistance(Waypoint point) {
		return Math.sqrt((x - point.x) * (x - point.x)
				+ (y - point.y)
				* (y - point.y));
	}

}












