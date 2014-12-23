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
	HashMap<Integer, TreeSet<Event>> events;
	HashMap<Integer, Integer> byMonth;

	public EventCalendar(int year) {
		this.year = year;
		events = new HashMap<Integer, TreeSet<Event>>();
		byMonth = new HashMap<Integer, Integer>();
		for(int i = 1; i <= 12; ++i) {
			byMonth.put(i, 0);
		}
	}

	public void addEvent(String name, String location, Date date)
			throws WrongDateException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int y = cal.get(Calendar.YEAR);
		if (y != year)
			throw new WrongDateException(date);
		int day = dayFromDate(date);
		TreeSet<Event> dayEvents = events.get(day);
		if (dayEvents == null) {
			dayEvents = new TreeSet<Event>();
			events.put(day, dayEvents);
		}
		dayEvents.add(new Event(name, location, date));
		int month = cal.get(Calendar.MONTH) + 1;
		Integer count = byMonth.get(month);
		if(count == null) {
			count = 0;
			//byMonth.put(month, count);
		}
		++count;
		byMonth.put(month, count);
	}

	public void listEvents(Date date) {
		int day = dayFromDate(date);
		TreeSet<Event> dayEvents = events.get(day);
		if (dayEvents == null) {
			System.out.println("No events on this day!");
			return;
		}
		for (Event e : dayEvents) {
			System.out.println(e);
		}
	}

	static int dayFromDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_YEAR);
	}
	
	public void listByMonth() {
		for(Entry<Integer, Integer> e : byMonth.entrySet()) {
			System.out.printf("%d : %d\n", e.getKey(), e.getValue());
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
		if (this.date.compareTo(o.date) == 0)
			return name.compareTo(o.name);
		return date.compareTo(o.date);
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