package mk.ukim.finki.np.optional;

public class OptionalProblem {


    public static Student findByNumber(String number) {
        for (Student student : StudentsData.students) {
            if (student.matchNumber(number)) {
                return student;
            }
        }
        return null;
    }
}
