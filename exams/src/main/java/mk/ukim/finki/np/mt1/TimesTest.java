package mk.ukim.finki.np.mt1;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class TimesTest {

    public static void main(String[] args) {
        TimeTable timeTable = new TimeTable();
        try {
            timeTable.readTimes(System.in);
        } catch (UnsupportedFormatException e) {
            System.out.println("UnsupportedFormatException: " + e.getMessage());
        } catch (InvalidTimeException e) {
            System.out.println("InvalidTimeException: " + e.getMessage());
        }
        System.out.println("24 HOUR FORMAT");
        timeTable.writeTimes(System.out, TimeFormat.FORMAT_24);
        System.out.println("AM/PM FORMAT");
        timeTable.writeTimes(System.out, TimeFormat.FORMAT_AMPM);
    }

}

enum TimeFormat {
    FORMAT_24, FORMAT_AMPM
}

class UnsupportedFormatException extends Exception {
    public UnsupportedFormatException(String msg) {
        super(msg);
    }
}

class InvalidTimeException extends Exception {
    public InvalidTimeException(String msg) {
        super(msg);
    }
}

class TimeTable {
    List<Time> times;

    public TimeTable() {
        times = new ArrayList<Time>();
    }

    public void readTimes(InputStream input) throws UnsupportedFormatException,
            InvalidTimeException {
        Scanner scanner = new Scanner(input);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            for (String p : parts) {
                Time time = new Time(p);
                times.add(time);
            }
        }
    }

    public void writeTimes(OutputStream output, TimeFormat format) {
        PrintWriter writer = new PrintWriter(output);
        times.sort(Comparator.<Time, Integer>comparing(time -> time.getHour())
                .thenComparing(time -> time.getMinute()));
        times.forEach(time -> {
            if (format == TimeFormat.FORMAT_24) {
                writer.println(time);
            } else {
                writer.println(time.toStringAMPM());
            }
        });
        writer.flush();
    }
}

class Time {
    private final int hour;
    private final int minute;

    public Time(String time) throws UnsupportedFormatException,
            InvalidTimeException {
        String[] parts = time.split("\\.");
        if (parts.length == 1) {
            parts = time.split(":");
        }
        if (parts.length == 1)
            throw new UnsupportedFormatException(time);
        this.hour = Integer.parseInt(parts[0]);
        this.minute = Integer.parseInt(parts[1]);
        if (hour < 0 || hour > 23 || minute < 0 || minute > 59)
            throw new InvalidTimeException(time);
    }

    public String toStringAMPM() {
        String part = "AM";
        int h = hour;
        if (h == 0) {
            h += 12;
        } else if (h == 12) {
            part = "PM";
        } else if (h > 12) {
            h -= 12;
            part = "PM";
        }
        return String.format("%2d:%02d %s", h, minute, part);
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    @Override
    public String toString() {
        return String.format("%2d:%02d", hour, minute);
    }

}