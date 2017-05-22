package mk.ukim.finki.np.av9;

import java.util.*;

public class PhoneBookTest {

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            try {
                phoneBook.addContact(parts[0], parts[1]);
            } catch (DuplicateNumberException e) {
                System.out.println(e.getMessage());
            }
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            if (parts[0].equals("N")) {
                phoneBook.contactsByNumber(parts[1]);
            } else {
                phoneBook.contactsByName(parts[1]);
            }
        }
    }

}

class PhoneBook {
    Set<String> allNumbers;
    Map<String, Set<Contact>> byNumberParts;
    Map<String, Set<Contact>> byName;
    static Comparator<Contact> comparator = Comparator.comparing(Contact::getName).thenComparing(Contact::getNumber);

    public PhoneBook() {
        byNumberParts = new TreeMap<>();
        byName = new HashMap<>();
        allNumbers = new HashSet<>();
    }

    public void addContact(String name, String number) throws DuplicateNumberException {
        if (allNumbers.contains(number))
            throw new DuplicateNumberException(number);

        allNumbers.add(number);
        Contact contact = new Contact(name, number);

        Set<Contact> contactsByName = byName.computeIfAbsent(name, key -> new TreeSet<>(comparator));
        contactsByName.add(contact);

        List<String> keys = getKeys(number, 3);
        for (String key : keys) {
            Set<Contact> contactsByNumber = byNumberParts.computeIfAbsent(key, k -> new TreeSet<>(comparator));
            contactsByNumber.add(contact);
        }

    }

    private List<String> getKeys(String key, int minLen) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i <= key.length() - minLen; ++i) {
            for (int len = minLen; len <= (key.length() - i); ++len) {
                String k = key.substring(i, i + len);
                result.add(k);
            }
        }
        return result;
    }

    // O(log N)
    public void contactsByNumber(String number) {
        if (byNumberParts.containsKey(number)) {
            byNumberParts.get(number).forEach(System.out::println);
        }
    }

    // O(1)
    public void contactsByName(String name) {
        if (byName.containsKey(name)) {
            byName.get(name).forEach(System.out::println);
        }
    }

    public static void main(String[] args) {
        new PhoneBook().getKeys("075444123", 3);
    }
}

class Contact {
    String name;
    String number;

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("%s %s", name, number);
    }

}

class DuplicateNumberException extends Exception {

    public DuplicateNumberException(String number) {
        super(String.format("Duplicate number: %s", number));
    }

}