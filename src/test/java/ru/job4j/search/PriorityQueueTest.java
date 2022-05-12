package ru.job4j.search;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PriorityQueueTest {
    @Test
    public void whenHigherPriorityThenUrgent() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }

    @Test
    public void whenHigherPriorityThenTop() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("mid", 1));
        queue.put(new Task("bot", 2));
        queue.put(new Task("mid", 0));
        Task result = queue.take();
        assertThat(result.getDesc(), is("mid"));
    }
}