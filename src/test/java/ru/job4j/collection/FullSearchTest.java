package ru.job4j.collection;

import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FullSearchTest {

    @Test
    public void whenExtractNumber121Then12() {
        List<Task> task = Arrays.asList(
                new Task("1", "First desc"),
                new Task("2", "Second desc"),
                new Task("1", "First desc")
        );
        Set<String> expected = new HashSet<>(Arrays.asList("1", "2"));
        assertThat(FullSearch.extractNumber(task), is(expected));
    }

    @Test
    public void whenExtractNumber1223Then123() {
        List<Task> task = Arrays.asList(
                new Task("1", "First desc"),
                new Task("2", "Second desc"),
                new Task("2", "Second desc"),
                new Task("3", "Third desc")
        );
        Set<String> expected = new HashSet<>(Arrays.asList("1", "2", "3"));
        assertThat(FullSearch.extractNumber(task), is(expected));
    }
}