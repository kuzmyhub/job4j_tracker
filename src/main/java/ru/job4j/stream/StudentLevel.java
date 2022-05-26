package ru.job4j.stream;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.Comparator;

public class StudentLevel {
    public static List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .filter(Objects::nonNull)
                .sorted((s1, s2) -> Integer.compare(s2.getScore(), s1.getScore()))
                .takeWhile(student -> student.getScore() > bound)
                .collect(Collectors.toList());
    }
}
