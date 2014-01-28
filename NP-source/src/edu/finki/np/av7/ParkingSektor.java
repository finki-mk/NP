package edu.finki.np.av7;

import java.util.HashMap;
import java.util.Map;

public class ParkingSektor {
	private String name;
	private int n;
	private Map<String, Integer> spots;
	private Map<Integer, String> taken;

	public ParkingSektor(String name, int n) {
		this.name = name;
		this.n = n;
		spots = new HashMap<String, Integer>();
		taken = new HashMap<Integer, String>();
	}

	public void addCar(String regNum, int spot)
			throws InvalidSpotNumberException, SpotTakenException {
		if (spot < 1 || spot > n)
			throw new InvalidSpotNumberException();
		if (taken.containsKey(spot))
			throw new SpotTakenException();
		taken.put(spot, regNum);
		spots.put(regNum, spot);
	}
	
	public void findCar(String registrationNumber) {
		int spot = spots.get(registrationNumber);
		System.out.printf("%s : %d", this.name, spot);
	}
}

class InvalidSpotNumberException extends Exception {

}

class SpotTakenException extends Exception {

}
