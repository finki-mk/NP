package edu.finki.np.av7;

import java.util.HashMap;
import java.util.Map;

public class CityParking {
	private String name;
	private Map<String, ParkingSektor> sektors;
	private Map<String, ParkingSektor> index;

	public CityParking(String name) {
		this.name = name;
		sektors = new HashMap<String, ParkingSektor>();
		index = new HashMap<String, ParkingSektor>();
	}

	public void createSectors(String[] sectorNames, int[] counts) {
		for (int i = 0; i < counts.length; ++i) {
			sektors.put(sectorNames[i], new ParkingSektor(sectorNames[i],
					counts[i]));
		}
	}

	public void addCar(String sectorName, int spotNumber,
			String registrationNumber) throws SectorNotFoundException,
			InvalidSpotNumberException, SpotTakenException {
		ParkingSektor ps = sektors.get(sectorName);
		if (ps == null)
			throw new SectorNotFoundException();
		ps.addCar(registrationNumber, spotNumber);
		index.put(registrationNumber, ps);
	}

	public void findCar(String registrationNumber) throws CarNotFoundException {
		ParkingSektor ps = index.get(registrationNumber);
		if (ps == null)
			throw new CarNotFoundException();
		ps.findCar(registrationNumber);
	}

}

class SectorNotFoundException extends Exception {

}

class CarNotFoundException extends Exception {

}
