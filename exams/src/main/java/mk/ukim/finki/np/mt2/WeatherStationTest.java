package mk.ukim.finki.np.mt2;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.function.Predicate;

public class WeatherStationTest {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
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
            LocalDateTime date = LocalDateTime.parse(line, timeFormatter);
            ws.addMeasurement(temp, wind, hum, vis, date);
        }
        String line = scanner.nextLine();
        LocalDateTime from = LocalDateTime.parse(line, timeFormatter);
        line = scanner.nextLine();
        LocalDateTime to = LocalDateTime.parse(line, timeFormatter);
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
    TreeSet<Measurement> measurments;

    int days;

    WeatherStation(int days) {
        this.days = days;
        measurments = new TreeSet<>();
    }

    public void addMeasurement(float temperature, float wind, float humidity,
                               float visibility, LocalDateTime date) {
        Measurement m = new Measurement(temperature, wind, humidity, visibility, date);
        if (!measurments.add(m)) {
            return;
        }

        Predicate<Measurement> old = measurement -> (measurement.date.minusDays(days).isAfter(date));
        measurments.removeIf(old);
    }

    public int total() {
        return measurments.size();
    }

    public void status(LocalDateTime from, LocalDateTime to) {
        Predicate<Measurement> isInRange = measurement -> !(measurement.date.isBefore(from)
                || measurement.date.isAfter(to));

        double averageTemperature = measurments.stream()
                .filter(isInRange)
                .mapToDouble(measurement -> measurement.temperature)
                .average().orElse(0);
        measurments.stream()
                .filter(isInRange)
                .forEach(System.out::println);
        System.out.printf("Average temperature: %.2f\n", averageTemperature);
    }
}

class Measurement implements Comparable<Measurement> {
    float temperature;
    float wind;
    float humidity;
    float visibility;
    LocalDateTime date;

    public Measurement(float temperature, float wind, float humidity,
                       float visibility, LocalDateTime date) {
        this.temperature = temperature;
        this.wind = wind;
        this.humidity = humidity;
        this.visibility = visibility;
        this.date = date;
    }

    @Override
    public int compareTo(Measurement other) {
        long span = Math.abs(other.date.until(date, ChronoUnit.SECONDS));
        if (span < 150) {
            return 0;
        } else return date.compareTo(other.date);
    }

    @Override
    public String toString() {
        return String.format("%.1f %.1f km/h %.1f%% %.1f km %s", temperature,
                wind, humidity, visibility, date);
    }

}
