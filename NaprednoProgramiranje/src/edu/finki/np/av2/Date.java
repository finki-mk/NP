package edu.finki.np.av2;

public class Date {
	private static final int FIRST_YEAR = 1800;
	private static final int LAST_YEAR = 2500;
	
	private static final int[] daysOfMonth = {
		31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 
	};
	private static final int[] daysTillFirstOfMonth;
	private static final int[] daysTillJan1;
	static {
		daysTillFirstOfMonth = new int[12];
		for(int i = 1; i < 12; i++) {
			daysTillFirstOfMonth[i] += daysTillFirstOfMonth[i - 1] + daysOfMonth[i - 1];
		}
		int totalYears = LAST_YEAR - FIRST_YEAR;
		daysTillJan1 = new int[totalYears + 1];
		int currentYear = FIRST_YEAR;
		for(int i = 1; i <= totalYears; i++) {
			if(isLeapYear(currentYear)) {
				daysTillJan1[i] = daysTillJan1[i - 1] + 366;
			} else {
				daysTillJan1[i] = daysTillJan1[i - 1] + 365;
			}
			currentYear++;
		}
	}
	
	private static boolean isLeapYear(int year) {
		return (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0));
	}
	
	private int days;
	
	public Date(int days) {
		this.days = days;
	}
	
	public Date(int date, int month, int year) {
		days = 0;
		if(year < FIRST_YEAR || year > LAST_YEAR) {
			throw new RuntimeException();
		}
		days += daysTillJan1[year - FIRST_YEAR];
		days += daysTillFirstOfMonth[month - 1];
		if(month > 2 && isLeapYear(year)) {
			days++;
		}
		days += date;
	}
	
	public int substract(Date date) {
		return this.days - date.days;
	}
	
	public Date increment(int days) {
		return new Date(this.days + days);
	}
	
	@Override
	public boolean equals(Object arg0) {
		Date date = (Date)arg0;
		return this.days == date.days;
	}
	
	public int compareTo(Date date) {
		return this.days - date.days;
	}
	
	@Override
	public String toString() {
		int d = days;
		int i;
		for(i = 0; i < daysTillJan1.length; i++) {
			if(daysTillJan1[i] >= days) {
				break;
			}
		}
		d -= daysTillJan1[i - 1];
		int year = FIRST_YEAR + i - 1;		
		for(i = 0; i < daysTillFirstOfMonth.length; i++) {
			if(daysTillFirstOfMonth[i] >= d) {
				break;
			}
		}
		int month = i;
		d -= daysTillFirstOfMonth[i - 1];
		int date = d;
		if(isLeapYear(year)) {
			date--;
		}
		return String.format("%02d.%02d.%4d", date, month, year);
	}
	
	public static void main(String[] args) {
		Date sample = new Date(1, 10, 2012);
		System.out.println("Date: " + sample);
		System.out.println(sample.substract(new Date(1, 1, 2000)));
		System.out.println(sample);
		sample = new Date(1, 1, 1800);
		System.out.println(sample);
		sample = new Date(31, 12, 2500);
		System.out.println(sample);
		sample = new Date(31, 12, 2300);
		System.out.println(sample);
		sample = sample.increment(100);
		System.out.println(sample);
	}
	
}
