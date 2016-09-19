package mk.ukim.finki.np.exams.jun2015;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CheckInTest {
  public static void main(String[] args) {
    CheckIn checkIns = new CheckIn();
    try {
      checkIns.loadTimes(System.in);
    } catch (UnsupportedFormatException e) {
      System.out.println("UnsupportedFormatException: " + e.getMessage());
    } catch (InvalidCheckInTimes e2) {
      System.out.println("InvalidCheckInTimes: " + e2.getMessage());
    }
    System.out.println(String.format("====== PRINT CHECK INS ======"));
    checkIns.printTimes(System.out);
  }
}

class CheckIn {
  List<CheckInTime> elements;

  public CheckIn() {
    elements = new ArrayList<CheckInTime>();
  }

  public void loadTimes(InputStream inputStream) throws UnsupportedFormatException, InvalidCheckInTimes {
    Scanner scanner = new Scanner(inputStream);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] parts = line.split(" ");
      String name = parts[0];
      String[] time1 = parts[1].split("-");
      String[] time2 = parts[2].split("-");
      CheckInTime time = new CheckInTime(name,
        CheckInTime.setDateFormat(time1[0]),
        CheckInTime.setDateFormat(time2[0]),
        CheckInTime.strToTime(time1[1]),
        CheckInTime.strToTime(time2[1]));
      if (!time.valid()) throw new InvalidCheckInTimes(line);
      elements.add(time);
    }
  }

  public void printTimes(OutputStream outputStream) {
    PrintWriter writer = new PrintWriter(outputStream);
    Collections.sort(elements);
    for (CheckInTime checkInTime : elements) {
      writer.println(checkInTime);
    }
    writer.flush();
    writer.close();
  }


}

class CheckInTime implements Comparable<CheckInTime> {
  private String name;
  private String dateFrom;
  private String dateTo;
  private int timeFrom;
  private int timeTo;

  public CheckInTime(String name, String dateFrom, String dateTo, int timeFrom, int timeTo) {
    this.name = name;
    this.dateFrom = dateFrom;
    this.dateTo = dateTo;
    this.timeFrom = timeFrom;
    this.timeTo = timeTo;
  }

  public boolean valid() {
    return dateFrom.equals(dateTo) && timeFrom <= timeTo;
  }

  public static int strToTime(String time) throws UnsupportedFormatException {
    String[] t = time.split(":");
    if (t.length == 1) {
      t = time.split("\\.");
      if (t.length == 1) throw new UnsupportedFormatException(time);
    }
    return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
  }

  public static String setDateFormat(String date) throws UnsupportedFormatException {
    date = date.replace("/", ".");
    if (!date.contains(".")) throw new UnsupportedFormatException(date);
    return date;
  }

  @Override
  public String toString() {
    return String.format("%-10s%s %02d:%02d - %02d:%02d (%d)", name, dateFrom, timeFrom / 60, timeFrom % 60,
      timeTo / 60, timeTo % 60, timeTo - timeFrom);
  }

  @Override
  public int compareTo(CheckInTime o) {
    return Integer.compare(timeFrom, o.timeFrom);
  }
}

class UnsupportedFormatException extends Exception {
  String data;

  public UnsupportedFormatException(String data) {
    this.data = data;
  }

  @Override
  public String getMessage() {
    return data;
  }
}

class InvalidCheckInTimes extends Exception {
  String data;

  public InvalidCheckInTimes(String data) {
    this.data = data;
  }

  @Override
  public String getMessage() {
    return data;
  }
}
