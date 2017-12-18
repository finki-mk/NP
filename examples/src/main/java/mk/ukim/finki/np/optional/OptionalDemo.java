package mk.ukim.finki.np.optional;

public class OptionalDemo {

    public static String findStudent(String number, String name) {
        Student student = OptionalProblem.findByNumber(number);
        if (student != null) {
            if (student.matchName(name)) {
                return student.toString();
            }
        }
        return "Student not found";
    }

    public static String findStudentOptional(String number, String name) {
        return OptionalSolution.findByNumber(number)
                .filter(it -> it.matchName(name))
                .map(Student::toString)
                .orElse("Student not found");
    }

    public static void main(String[] args) {
        System.out.println(findStudent("007", "Gjorgji"));
    }
}
