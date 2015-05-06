package np2014;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class WeatherStationTest {
	public static void main(String[] args) throws ParseException {
		Scanner scanner = new Scanner(System.in);
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		int n = scanner.nextInt();
		scanner.nextLine();
		WeatherStation ws = new WeatherStation(n);
		while (true) {
			String line = scanner.nextLine();
			if (line.equals("=====")) {
				break;
			}
			String[] parts = line.split(" ");
			float temp = Float.parseFloat(parts[0]);
			float wind = Float.parseFloat(parts[1]);
			float hum = Float.parseFloat(parts[2]);
			float vis = Float.parseFloat(parts[3]);
			line = scanner.nextLine();
			Date date = df.parse(line);
			ws.addMeasurment(temp, wind, hum, vis, date);
		}
		String line = scanner.nextLine();
		Date from = df.parse(line);
		line = scanner.nextLine();
		Date to = df.parse(line);
		scanner.close();
		System.out.println(ws.total());
		try {
			ws.status(from, to);
		} catch (RuntimeException e) {
			System.out.println(e);
		}
	}
}

class WeatherStation {
	int days;
	TreeSet<Measurement> measurments;

	public WeatherStation(int days) {
		this.days = days;
		this.measurments = new TreeSet<Measurement>();
	}

	public void addMeasurment(float temperature, float wind, float humidity,
			float visibility, Date date) {
		// find if there is meas. with 2.5 min diff in time ignore
		long diff = 150 * 1000;
		for (Measurement m : measurments) {
			if (Math.abs(m.date.getTime() - date.getTime()) < diff)
				return;
		}
		diff = days * 24 * 60 * 60 * 1000;
		Iterator<Measurement> iterator = measurments.iterator();
		while (iterator.hasNext()) {
			Measurement m = iterator.next();
			if ((date.getTime() - m.date.getTime()) > diff) {
				iterator.remove();
			}
		}
		Measurement m = new Measurement(temperature, wind, humidity,
				visibility, date);
		this.measurments.add(m);
	}

	public int total() {
		return measurments.size();
	}

	public void status(Date from, Date to) {
		// List<Measurement> result = new ArrayList<Measurement>();
		float tempSum = 0;
		int count = 0;
		for (Measurement m : measurments) {
			if (m.date.after(from) && m.date.before(to)) {
				// result.add(m);
				tempSum += m.temperature;
				++count;
				System.out.println(m);
			}
		}
		if(count == 0) throw new RuntimeException();
		System.out.printf("Avarage temperature: %.2f\n", tempSum / count);
	}

}

class Measurement implements Comparable<Measurement> {
	float temperature;
	float wind;
	float humidity;
	float visibility;
	Date date;

	public Measurement(float temperature, float wind, float humidity,
			float visibility, Date date) {
		this.temperature = temperature;
		this.wind = wind;
		this.humidity = humidity;
		this.visibility = visibility;
		this.date = date;
	}

	@Override
	public int compareTo(Measurement o) {
		return this.date.compareTo(o.date);
	}

	@Override
	public String toString() {
		return String.format("%.1f %.1f km/h %.1f%% %.1f km %s", temperature,
				wind, humidity, visibility, date);
	}
}
