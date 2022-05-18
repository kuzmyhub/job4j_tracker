package ru.job4j.collection;

import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import static org.junit.Assert.*;

public class LexSortTest {

    @Test
    public void whenSortNum10and1and2ThenNum1and2and10() {
        String[] input = {
                "10. Task.",
                "1. Task.",
                "2. Task."
        };
        String[] out = {
                "1. Task.",
                "2. Task.",
                "10. Task."
        };
        Arrays.sort(input, new LexSort());
        assertThat(input, is(out));
    }

    @Test
    public void whenSortNum3Num2Num1ThenNum1Num2Num3() {
        String[] input = {
                "3. Task",
                "2. Task",
                "1. Task"
        };
        String[] out = {
                "1. Task",
                "2. Task",
                "3. Task"
        };
        Arrays.sort(input, new LexSort());
        assertThat(input, is(out));
    }

    @Test
    public void whenSortNum9Num6Num5ThenNum9Num6Num5() {
        String[] input = {
                "9. Task",
                "6. Task",
                "5. Task"
        };
        String[] out = {
                "5. Task",
                "6. Task",
                "9. Task"
        };
        Arrays.sort(input, new LexSort());
        assertThat(input, is(out));
    }
}