package ru.job4j.stream;

import org.junit.Test;

import javax.swing.text.Style;
import java.sql.Struct;
import java.util.List;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StudentLevelTest {
    @Test
    public void whenSorted() {
        List<Student> input = new ArrayList<>();
        input.add(new Student("Masha", 28));
        input.add(new Student("Pety", 128));
        List<Student> expected = List.of(
                new Student("Pety", 128),
                (new Student("Masha", 28))
        );
        assertThat(StudentLevel.levelOf(input, 20), is(expected));
    }

    @Test
    public void whenOnlyNull() {
        List<Student> input = new ArrayList<>();
        input.add(null);
        List<Student> expected = List.of();
        assertThat(StudentLevel.levelOf(input, 100), is(expected));
    }

    @Test
    public void whenHasNull() {
        List<Student> input = new ArrayList<>();
        input.add(null);
        input.add(new Student("Pety", 28));
        List<Student> expected = List.of(new Student("Pety", 28));
        assertThat(StudentLevel.levelOf(input, 10), is(expected));
    }
}