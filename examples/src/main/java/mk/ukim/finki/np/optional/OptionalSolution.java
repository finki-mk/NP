package mk.ukim.finki.np.optional;

import java.util.Optional;

public class OptionalSolution {

    public static Optional<Student> findByNumber(String number) {
        return StudentsData.students.stream()
                .filter(it -> it.matchNumber(number))
                .findFirst();

    }
}
