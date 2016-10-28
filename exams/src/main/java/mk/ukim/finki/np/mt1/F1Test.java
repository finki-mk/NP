package mk.ukim.finki.np.mt1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class F1Test {

    public static void main(String[] args) {
        F1Race f1Race = new F1Race();
        f1Race.readResults(System.in);
        f1Race.printSorted(System.out);
    }

}

class F1Race {
    private List<Driver> drivers;

    public F1Race() {
        drivers = new ArrayList<>();
    }

    public void readResults(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        drivers = reader.lines()
                .map(Driver::fromString)
                .collect(Collectors.toList());
    }

    public void printSorted(OutputStream outputStream) {
        PrintWriter printWriter = new PrintWriter(outputStream);
        List<Driver> sorted = drivers.stream()
                .sorted()
                .collect(Collectors.toList());
        IntStream.range(0, sorted.size())
                .forEach(i -> printWriter.println(String.format("%d. %s", i + 1, sorted.get(i))));
        printWriter.close();
    }
}

class Driver implements Comparable<Driver> {
    private String name;
    private int lap1;
    private int lap2;
    private int lap3;
    private int best;

    public Driver(String name, int lap1, int lap2, int lap3) {
        this.name = name;
        this.lap1 = lap1;
        this.lap2 = lap2;
        this.lap3 = lap3;
        this.best = Math.min(Math.min(lap1, lap2), lap3);
    }

    public static int stringToTime(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 * 1000
                + Integer.parseInt(parts[1]) * 1000
                + Integer.parseInt(parts[2]);
    }

    public static String timeToString(int time) {
        int minutes = (time / 1000) / 60;
        int seconds = (time - minutes * 1000 * 60) / 1000;
        int ms = time % 1000;
        return String.format("%d:%02d:%03d", minutes, seconds, ms);
    }

    public static Driver fromString(String line) {
        String[] parts = line.split("\\s+");
        return new Driver(parts[0], Driver.stringToTime(parts[1]),
                Driver.stringToTime(parts[2]),
                Driver.stringToTime(parts[3]));
    }

    @Override
    public int compareTo(Driver o) {
        return this.best - o.best;
    }

    @Override
    public String toString() {
        return String.format("%-10s%10s", name, timeToString(best));
    }
}