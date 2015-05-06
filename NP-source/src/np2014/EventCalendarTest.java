package np2014;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeSet;

public class EventCalendarTest {
	public static void main(String[] args) throws ParseException {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.nextLine();
		int year = scanner.nextInt();
		scanner.nextLine();
		EventCalendar eventCalendar = new EventCalendar(year);
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		for (int i = 0; i < n; ++i) {
			String line = scanner.nextLine();
			String[] parts = line.split(";");
			String name = parts[0];
			String location = parts[1];
			Date date = df.parse(parts[2]);
			try {
				eventCalendar.addEvent(name, location, date);
			} catch (WrongDateException e) {
				System.out.println(e.getMessage());
			}
		}
		Date date = df.parse(scanner.nextLine());
		eventCalendar.listEvents(date);
		eventCalendar.listByMonth();
	}
}

class EventCalendar {
	private int year;
	HashMap<String, TreeSet<Event>> events;
	HashMap<Integer, Integer> monthly;

	public EventCalendar(int year) {
		this.year = year;
		events = new HashMap<String, TreeSet<Event>>();
		monthly = new HashMap<Integer, Integer>();
	}

	public void addEvent(String name, String location, Date date)
			throws WrongDateException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int y = cal.get(Calendar.YEAR);
		if (y != year)
			throw new WrongDateException(date);
		Event event = new Event(name, location, date);
		String key = Event.getKey(date);
		TreeSet<Event> dayEvents = events.get(key);
		if (dayEvents == null) {
			dayEvents = new TreeSet<Event>();
			events.put(key, dayEvents);
		}
		dayEvents.add(event);
		int month = cal.get(Calendar.MONTH);
		Integer count = monthly.get(month);
		if (count == null) {
			count = 0;
			monthly.put(month, count);
		}
		++count;
		monthly.put(month, count);
	}

	public void listEvents(Date date) {
		String key = Event.getKey(date);
		TreeSet<Event> dayEvents = events.get(key);
		for (Event e : dayEvents) {
			System.out.println(e);
		}
	}

	public void listByMonth() {
		for (Entry<Integer, Integer> entry : monthly.entrySet()) {
			System.out.printf("%d : %d\n", entry.getKey(), entry.getValue());
		}
	}

}

class Event implements Comparable<Event> {
	String name;
	String location;
	Date date;

	public Event(String name, String location, Date date) {
		this.name = name;
		this.location = location;
		this.date = date;
	}

	@Override
	public int compareTo(Event o) {
		if (date.compareTo(o.date) == 0)
			return name.compareTo(o.name);
		return date.compareTo(o.date);
	}

	public static String getKey(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		return String.format("%02d-%02d", day, month);
	}

	@Override
	public String toString() {
		DateFormat format = new SimpleDateFormat("dd MMM, YYY HH:mm");
		return String.format("%s at %s, %s", format.format(date), location,
				name);
	}

}

class WrongDateException extends Exception {
	public WrongDateException(Date date) {
		super(String.format("Wrong date: %s", date));
	}
}
