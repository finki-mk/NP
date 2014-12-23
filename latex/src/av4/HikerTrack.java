package edu.finki.np.av4;

import java.util.ArrayList;

public class HikerTrack {
	private static final double SCALE_FACTOR = 0.1;
	private ArrayList<Waypoint> waypoints;
	private double distance;
	private int time;

	public HikerTrack() {
		waypoints = new ArrayList<Waypoint>();
		distance = 0;
		time = 0;
	}

	public void addPoint(double x, double y, int timestamp) {
		Waypoint w = new Waypoint(x, y, timestamp);
		waypoints.add(w);
		int size = waypoints.size();
		if(size >= 2) {
			distance += waypoints.get(size - 2).getDistance(waypoints.get(size - 1));
		}
	}

	public double getDistance() {
		/*double d = 0;
		for (int i = 1; i < waypoints.size(); i++) {
			d += waypoints.get(i).getDistance(waypoints.get(i - 1));
		}
*/		return distance * SCALE_FACTOR;
	}

	public int getTime() {
		return waypoints.get(waypoints.size() - 1).getTimestamp()
				- waypoints.get(0).getTimestamp();
	}

	public double getSpeed() {
		double distance = getDistance();
		int time = getTime();
		return distance * 3600 / time;
	}

	public static void main(String[] args) {
		HikerTrack track = new HikerTrack();
		double[] x = new double[] { 0.1, 3.4, 4, 5.3, 8.9, 12.4, 13 };
		double[] y = new double[] { 1, 2.4, 5, 6.3, 8.2, 11.4, 12 };
		int[] times = new int[] { 0, 500, 1000, 1500, 2000, 2500, 3000 };
		for (int i = 0; i < x.length; i++) {
			track.addPoint(x[i], y[i], times[i]);
		}
		System.out.println(String.format("Distance: %.2f km",
				track.getDistance()));
		System.out.println(String.format("Time: %d seconds", track.getTime()));
		System.out.println(String.format("Speed: %.2f km/h", track.getSpeed()));
	}
}
