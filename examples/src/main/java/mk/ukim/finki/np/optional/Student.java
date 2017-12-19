package mk.ukim.finki.np.optional;

public class Student {
    private final String number;
    private final String name;

    public Student(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public boolean matchName(String name) {
        return this.name.startsWith(name);
    }

    public boolean matchNumber(String number) {
        return this.number.equals(number);
    }

    @Override
    public String toString() {
        return String.format("%s. %s", number, name);
    }
}
