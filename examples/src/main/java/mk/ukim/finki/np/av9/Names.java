package mk.ukim.finki.np.av9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Names {

    public List<Name> readNames(String fileName) throws IOException {
        return Files.lines(Paths.get(fileName))
                .map(Name::ofString)
                .collect(Collectors.toList());
    }

    public List<DuplicateName> findDuplicates(List<Name> girls, List<Name> boys) {
        Map<String, DuplicateName> duplicates = new HashMap<>();
        girls.forEach(name ->
                duplicates.computeIfAbsent(name.getName(), key -> new DuplicateName(key, name.getCount()))
        );
        boys.forEach(name ->
                duplicates.computeIfPresent(name.getName(), (key, duplicateName) -> {
                    duplicateName.setCountBoys(name.getCount());
                    return duplicateName;
                })
        );
        return duplicates.values().stream()
                .filter(name -> name.getCountBoys() > 0)
                .collect(Collectors.toList());
    }


    public static void main(String[] args) {
        Names names = new Names();
        try {
            List<Name> girls = names.readNames("examples/data/girlnames.txt");
            List<Name> boys = names.readNames("examples/data/boynames.txt");
            List<DuplicateName> duplicates = names.findDuplicates(girls, boys);
            duplicates.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

class Name {
    private final String name;
    private final int count;

    Name(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public static Name ofString(String line) {
        String[] parts = line.split("\\s+");
        String name = parts[0];
        int count = Integer.parseInt(parts[1]);
        return new Name(name, count);
    }

    @Override
    public String toString() {
        return String.format("%s %d", name, count);
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}

class DuplicateName extends Name {
    private int countBoys;

    public DuplicateName(String name, int count) {
        super(name, count);
    }

    public int getCountBoys() {
        return countBoys;
    }

    public void setCountBoys(int countBoys) {
        this.countBoys = countBoys;
    }

    @Override
    public String toString() {
        return String.format("%s %d", super.toString(), countBoys);
    }
}
