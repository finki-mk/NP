package edu.finki.np.ex3;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class RoadNetworkTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String country = scanner.nextLine();
		RoadNetwork roadNetwork = new RoadNetwork(country);
		int n = scanner.nextInt();
		scanner.nextLine();
		for (int i = 0; i < n; ++i) {
			String line = scanner.nextLine();
			String[] parts = line.split(" ");
			String cityName = parts[0];
			int population = Integer.parseInt(parts[1]);
			String[] cities = new String[(parts.length - 2) / 2];
			float[] distances = new float[(parts.length - 2) / 2];
			int k = 0;
			for (int j = 2; j < parts.length; j += 2) {
				cities[k] = parts[j];
				distances[k++] = Float.parseFloat(parts[j + 1]);
			}
			roadNetwork.addCity(cityName, population, cities, distances);
		}
		System.out.println("SEARCH");
		String cityName = scanner.nextLine();
		scanner.close();
		try {
			roadNetwork.search(cityName);
		} catch(CityNotFoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("ROAD NETWORK");
		System.out.printf("%.2f\n", roadNetwork.roadNetwork());
		System.out.println("MAX DENSE");
		roadNetwork.mostDense();
	}
}

class RoadNetwork {
	String country;
	Map<String, City> cities;

	public RoadNetwork(String country) {
		this.country = country;
		cities = new HashMap<String, City>();
	}

	public void addCity(String cityName, int population, String[] cities,
			float[] distances) {
		City city = new City(cityName, population);
		for (int i = 0; i < cities.length; ++i) {
			city.addConnection(cities[i], distances[i]);
		}
		this.cities.put(cityName, city);
	}

	public void search(String cityName) throws CityNotFoundException {
		City city = cities.get(cityName);
		if (city != null) {
			System.out.print(city);
		} else {
			throw new CityNotFoundException(String.format(
					"City '%s' was not found", cityName));
		}
	}

	public float roadNetwork() {
		float sum = 0;
		for (City city : cities.values()) {
			sum += city.totalRoads();
		}
		return sum;
	}

	public void mostDense() {
		float maxCoef = Float.MIN_VALUE;
		City maxCity1 = null;
		City maxCity2 = null;
		for (City city1 : cities.values()) {
			for (City city2 : cities.values()) {
				if (!city1.name.equals(city2.name)) {
					float coef = densityCoef(city1, city2);
					if (coef > 0&&coef > maxCoef) {
						maxCoef = coef;
						maxCity1 = city1;
						maxCity2 = city2;
					}
				}
			}
		}
		System.out.printf("%s - %s : %.2f\n", maxCity1.name, maxCity2.name,
				maxCoef);
	}

	static float densityCoef(City city1, City city2) {
		float distance = city1.distanceTo(city2.name);
		if (distance > 0) {
			return (city1.population + city2.population) / distance;
		}
		return -1;
	}
}

class CityNotFoundException extends Exception {
	public CityNotFoundException(String message) {
		super(message);
	}
}

class City {
	String name;
	int population;
	Map<String, Float> roads;

	public City(String name, int population) {
		this.population = population;
		this.name = name;
		roads = new TreeMap<String, Float>();
	}

	public void addConnection(String city, float distance) {
		roads.put(city, distance);
	}

	public float totalRoads() {
		float total = 0;
		for (Float f : roads.values()) {
			total += f;
		}
		return total;
	}

	public float distanceTo(String city) {
		if (roads.containsKey(city)) {
			return roads.get(city);
		}
		return -1;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append("\n");
       	sb.append(population);
        sb.append("\n");
		for (Entry<String, Float> entry : roads.entrySet()) {
			sb.append(String.format("%s : %.1f km\n", entry.getKey(),
					entry.getValue()));
		}
		return sb.toString();
	}

}
