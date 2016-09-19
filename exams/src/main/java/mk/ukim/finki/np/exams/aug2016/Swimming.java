package mk.ukim.finki.np.exams.aug2016;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * Swimming problem
 */
public class Swimming {
    public static void main(String[] args) {
        SwimmingEvent swimmingEvent = new SwimmingEvent();
        swimmingEvent.read(System.in);
        swimmingEvent.printFinal(System.out);
    }
}

class SwimmingEvent {
    List<Swimmer> semifinal1;
    List<Swimmer> semifinal2;
    List<Swimmer> finalEvent;
    static final Map<Integer, Integer> SEEDING;

    static {
        SEEDING = new HashMap<>();
        SEEDING.put(1, 4);
        SEEDING.put(2, 5);
        SEEDING.put(3, 3);
        SEEDING.put(4, 6);
        SEEDING.put(5, 7);
        SEEDING.put(6, 2);
        SEEDING.put(7, 1);
        SEEDING.put(8, 8);
    }

    int sf;
    int number;

    public SwimmingEvent() {
        semifinal1 = new ArrayList<>();
        semifinal2 = new ArrayList<>();
        sf = 0;
    }

    public void read(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line == null || line.isEmpty()) return;
            String[] parts = line.split("\\s+");
            if (parts.length == 2) {
                ++sf;
                number = 0;
            } else {
                String name = join(parts, 1, parts.length - 2);
                Swimmer swimmer = new Swimmer(++number, name, Float.parseFloat(parts[parts.length - 1]));
                if (sf == 1) {
                    semifinal1.add(swimmer);
                } else {
                    semifinal2.add(swimmer);
                }
            }
        }

    }

    void setFinal() {
        Collections.sort(semifinal1);
        Collections.sort(semifinal2);
        finalEvent = new ArrayList<>();
        finalEvent.addAll(semifinal1.subList(0, 2));
        finalEvent.addAll(semifinal2.subList(0, 2));
        List<Swimmer> joined = new ArrayList<>();
        joined.addAll(semifinal1.subList(2, semifinal1.size()));
        joined.addAll(semifinal2.subList(2, semifinal2.size()));
        Collections.sort(joined);
        finalEvent.addAll(joined.subList(0, 4));
        Collections.sort(finalEvent);
        int number = 1;
        for (Swimmer swimmer : finalEvent) {
            swimmer.setSeed(SEEDING.get(number++));
        }
        Collections.sort(finalEvent, new Comparator<Swimmer>() {
            @Override
            public int compare(Swimmer o1, Swimmer o2) {
                return Integer.compare(o1.getSeed(), o2.getSeed());
            }
        });
    }

    public void printFinal(OutputStream outputStream) {
        setFinal();
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.println("Final");
        for (Swimmer swimmer : finalEvent) {
            printWriter.println(swimmer);
        }
        printWriter.flush();
    }

    static String join(String[] parts, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; ++i) {
            sb.append(parts[i]);
            sb.append(" ");
        }
        return sb.toString().trim();
    }

}


class Swimmer implements Comparable<Swimmer> {
    private int number;
    private String name;
    private float time;
    private int seed;

    public Swimmer(int number, String name, float time) {
        this.number = number;
        this.name = name;
        this.time = time;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public int getSeed() {
        return seed;
    }

    @Override
    public String toString() {
        return String.format("%d. %-30s%.2f", seed, name, time);
    }

    @Override
    public int compareTo(Swimmer o) {
        int res = Float.compare(time, o.time);
        if (res == 0) {
            return name.compareTo(o.name);
        }
        return res;
    }
}