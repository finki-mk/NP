package edu.finki.np.ex1;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class ParkingTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.nextLine();
		String[] sectorNames = new String[n];
		int[] counts = new int[n];
		for (int i = 0; i < n; ++i) {
			String[] parts = scanner.nextLine().split(" ");
			sectorNames[i] = parts[0];
			counts[i] = Integer.parseInt(parts[1]);
		}
		String name = scanner.nextLine();
		CityParking cityParking = new CityParking(name);
		cityParking.createSectors(sectorNames, counts);
		n = scanner.nextInt();
		scanner.nextLine();
		for (int i = 0; i < n; ++i) {
			String[] parts = scanner.nextLine().split(" ");
			String sectorName = parts[0];
			int spotNumber = Integer.parseInt(parts[1]);
			String registrationNumber = parts[2];
			try {
				cityParking.addCar(sectorName, spotNumber, registrationNumber);
			} catch (InvalidSpotNumberException e) {
				System.out.println(e.getMessage());
			} catch (SpotTakenException e) {
				System.out.println(e.getMessage());
			} catch (NoSuchSectorException e) {
				System.out.println(e.getMessage());
			}
		}
		n = scanner.nextInt();
		scanner.nextLine();
		for (int i = 0; i < n; ++i) {
			String registrationNumber = scanner.nextLine();
			try {
				cityParking.findCar(registrationNumber);
			} catch (CarNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println(cityParking);
	}
}

class ParkingSector {
	private String code;
	private int count;
	private Map<Integer, String> spots;
	private Map<String, Integer> index;

	public ParkingSector(String code, int count) {
		this.code = code;
		this.count = count;
		this.spots = new HashMap<Integer, String>();
		this.index = new HashMap<String, Integer>();
	}

	public void addCar(int spotNumber, String registrationNumber)
			throws InvalidSpotNumberException, SpotTakenException {
		if (spotNumber <= 0 || spotNumber > count) {
			throw new InvalidSpotNumberException();
		}
		if (spots.containsKey(spotNumber)) {
			throw new SpotTakenException();
		}
		spots.put(spotNumber, registrationNumber);
		index.put(registrationNumber, spotNumber);
	}

	public int findSpotNumber(String registrationNumber)
			throws CarNotFoundException {
		if (index.containsKey(registrationNumber)) {
			return index.get(registrationNumber);
		}
		throw new CarNotFoundException(registrationNumber);
	}

	public void clearSpot(int spotNumber) {
		spots.remove(spotNumber);
	}

	public String getCode() {
		return this.code;
	}

	public Map<Integer, String> getSpots() {
		return spots;
	}

	@Override
	public String toString() {
		return String.format("%s : %d/%d", code, spots.size(), count);
	}

}

class InvalidSpotNumberException extends Exception {
	public InvalidSpotNumberException() {
		super("Invalid spot number");
	}
}

class SpotTakenException extends Exception {
	public SpotTakenException() {
		super("Spot is taken already!");
	}
}

class CityParking {
	private String name;
	private Map<String, ParkingSector> sectors;
	private Map<String, ParkingSector> index;

	public CityParking(String name) {
		this.name = name;
		this.sectors = new TreeMap<String, ParkingSector>();
		this.index = new HashMap<String, ParkingSector>();
	}

	public void createSectors(String[] sectorNames, int[] counts) {
		for (int i = 0; i < sectorNames.length; ++i) {
			ParkingSector parkingSector = new ParkingSector(sectorNames[i],
					counts[i]);
			sectors.put(parkingSector.getCode(), parkingSector);
		}
	}

	public void addCar(String sectorName, int spotNumber,
			String registrationNumber) throws InvalidSpotNumberException,
			SpotTakenException, NoSuchSectorException {
		ParkingSector parkingSector = sectors.get(sectorName);
		if (parkingSector != null) {
			parkingSector.addCar(spotNumber, registrationNumber);
			index.put(registrationNumber, parkingSector);
		} else {
			throw new NoSuchSectorException(sectorName);
		}
	}

	public void findCar(String registrationNumber) throws CarNotFoundException {
		if (index.containsKey(registrationNumber)) {
			ParkingSector parkingSector = index.get(registrationNumber);
			int spotNumber = parkingSector.findSpotNumber(registrationNumber);
			System.out.println(String.format("%s : %d",
					parkingSector.getCode(), spotNumber));
		} else {
			throw new CarNotFoundException(registrationNumber);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append("\n");
		for (Entry<String, ParkingSector> sector : sectors.entrySet()) {
			sb.append(sector.getValue().toString());
			sb.append("\n");
		}
		return sb.toString();
	}
}

class NoSuchSectorException extends Exception {
	public NoSuchSectorException(String sectorName) {
		super(String.format("No sector with name %s", sectorName));
	}
}

class CarNotFoundException extends Exception {
	public CarNotFoundException(String registrationNumber) {
		super(String.format("Car with RN %s not found", registrationNumber));
	}
}