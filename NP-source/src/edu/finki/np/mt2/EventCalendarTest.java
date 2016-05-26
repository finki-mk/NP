package edu.finki.np.mt2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class EventCalendarTest {
	public static void main(String[] args) throws ParseException {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.nextLine();
		int year = scanner.nextInt();
		scanner.nextLine();
		EventCalendar eventCalendar = new EventCalendar(year);
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
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
	int year;
	HashMap<String, TreeSet<Event>> events;
	HashMap<Integer, Integer> count;

	public EventCalendar(int year) {
		this.year = year;
		events = new HashMap<String, TreeSet<Event>>();
		count = new HashMap<Integer, Integer>();
	}

	public void addEvent(String name, String location, Date date)
			throws WrongDateException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int y = cal.get(Calendar.YEAR);
		if (y != year) {
			throw new WrongDateException(date);
		}
		Event event = new Event(name, location, date);
		String key = Event.getKey(date);
		TreeSet<Event> eventsSet = events.get(key);
		if (eventsSet == null) {
			eventsSet = new TreeSet<Event>();
			events.put(key, eventsSet);
		}
		eventsSet.add(event);

		int month = cal.get(Calendar.MONTH);
		Integer c = count.get(month);
		if (c == null) {
			c = 0;
		}
		++c;
		count.put(month, c);
	}
	
	public void listEvents(Date date) {
		String key = Event.getKey(date);
		TreeSet<Event> dayEvents = events.get(key);
		for(Event event : dayEvents) {
			System.out.println(event);
		}
	}
	
	public void listByMonth() {
		for(Integer key : count.keySet()) {
			Integer c = count.get(key);
			if(c == null) {
				c = 0;
			}
			System.out.printf("%d : %d", key, c);
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

	public static String getKey(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("dd.MM");
		return dateFormat.format(date);
	}

	@Override
	public int compareTo(Event o) {
		return date.compareTo(o.date);
	}
	
	@Override
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("dd MMM, YYY HH:mm");
		return String.format("%s at %s, %s", dateFormat.format(date), location, name);
	}

}

class WrongDateException extends Exception {
	public WrongDateException(Date date) {
		super(String.format("Wrong date: %s", date.toString()));
	}
}
