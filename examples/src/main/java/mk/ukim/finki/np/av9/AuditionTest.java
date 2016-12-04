package mk.ukim.finki.np.av9;

import java.util.*;

public class AuditionTest {
    public static void main(String[] args) {
        Audition audition = new Audition();
        List<String> cities = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            if (parts.length > 1) {
                audition.addParticipant(parts[0], parts[1], parts[2],
                        Integer.parseInt(parts[3]));
            } else {
                cities.add(line);
            }
        }
        for (String city : cities) {
            System.out.printf("+++++ %s +++++\n", city);
            audition.listByCity(city);
        }
        scanner.close();
    }
}

class Audition {
    HashMap<String, HashSet<Participant>> participants;

    public Audition() {
        participants = new HashMap<>();
    }

    public void addParticipant(String city, String code, String name, int age) {
        participants.computeIfAbsent(city, key -> new HashSet<>());
        participants.computeIfPresent(city, (key, set) -> {
            set.add(new Participant(code, name, age));
            return set;
        });
    }

    public void listByCity(String city) {
        participants.get(city).stream()
                .sorted(Comparator
                        .comparing(Participant::getName)
                        .thenComparing(Participant::getAge))
                .forEach(System.out::println);
    }
}

class Participant {
    private final String code;
    private final String name;
    private final int age;

    public Participant(String code, String name, int age) {
        this.code = code;
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        Participant p = (Participant) obj;
        return code.equals(p.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s %s %d", code, name, age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
