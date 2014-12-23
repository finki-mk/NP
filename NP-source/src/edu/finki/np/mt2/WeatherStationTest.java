package edu.finki.np.mt2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
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

	static void generate() {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 11, 15, 0, 0);
		Date from = cal.getTime();
		cal.set(2013, 11, 18, 0, 0);
		Date to = cal.getTime();
		long f = from.getTime();
		long t = to.getTime();
		Random r = new Random();

		for (long i = f; i <= t; i += 60000 * 5) {
			double temperature = (r.nextGaussian() + 2) * 10;
			double wind = r.nextDouble() * 50;
			double humidity = r.nextDouble() * 100;
			double visibility = r.nextDouble() * 200;
			Date date = new Date(i);
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			System.out.printf("%.1f %.1f %.1f %.1f\n%s\n", temperature, wind,
					humidity, visibility, df.format(date));
		}
	}
}

class WeatherStation {
	TreeSet<Measurement> measurments;

	int days;

	WeatherStation(int days) {
		this.days = days;
		measurments = new TreeSet<Measurement>();
	}

	public void addMeasurment(float temperature, float wind, float humidity,
			float visibility, Date date) {
		Measurement m = new Measurement(temperature, wind, humidity,
				visibility, date);
		if(!measurments.add(m)) {
			return;
		}
		
		long d = days * 24 * 60 * 60 * 1000;
		Iterator<Measurement> it = measurments.iterator();
		while (it.hasNext()) {
			Measurement mm = it.next();
			long diff = date.getTime() - mm.date.getTime();
			if (diff > d) {
				it.remove();
			} else {
				break;
			}
		}
		

	}

	public int total() {
		return measurments.size();
	}

	public void status(Date from, Date to) {
		float tempSum = 0;
		int count = 0;
		for (Measurement m : measurments) {
			if (m.date.compareTo(from) >= 0 && m.date.compareTo(to) <= 0) {
				System.out.println(m);
				tempSum += m.temperature;
				++count;
			}
		}
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
		/*long t1 = o.date.getTime();
		long t2 = this.date.getTime();
		long d = Math.abs(t1 - t2);
		if (d < 150 * 1000) {
			return 0;
		}*/
		return this.date.compareTo(o.date);
	}

	@Override
	public String toString() {
		return String.format("%.1f %.1f km/h %.1f%% %.1f km %s", temperature,
				wind, humidity, visibility, date);
	}

}
