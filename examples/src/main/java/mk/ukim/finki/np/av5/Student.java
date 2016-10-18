package mk.ukim.finki.np.av5;

/**
 * Student class
 */
class Student implements Comparable<Student> {
    private String firstName;
    private String lastName;
    private int exam1;
    private int exam2;
    private int exam3;
    private char grade;
    private double total;

    public Student(String firstName, String lastName, int exam1, int exam2,
                   int exam3) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.exam1 = exam1;
        this.exam2 = exam2;
        this.exam3 = exam3;
        this.total = exam1 * .25 + exam2 * .3 + exam3 * .45;
        if (this.total >= 91) {
            this.grade = 'A';
        } else if (this.total >= 81) {
            this.grade = 'B';
        } else if (this.total >= 71) {
            this.grade = 'C';
        } else if (this.total >= 61) {
            this.grade = 'D';
        } else {
            this.grade = 'E';
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public char getGrade() {
        return grade;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public int compareTo(Student other) {
        return Double.compare(this.total, other.total);
    }

    public static Student fromString(String line) {
        String[] parts = line.split(":");
        int exam1 = Integer.parseInt(parts[2]);
        int exam2 = Integer.parseInt(parts[3]);
        int exam3 = Integer.parseInt(parts[4]);
        return new Student(parts[0], parts[1], exam1, exam2, exam3);
    }

    @Override
    public String toString() {
        return String.format("%s %s %d %d %d %.0f %c", firstName, lastName,
                exam1, exam2, exam3, total, grade);
    }

}
