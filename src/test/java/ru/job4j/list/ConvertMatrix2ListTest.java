package ru.job4j.list;

import org.junit.Test;
import org.junit.Ignore;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.*;

public class ConvertMatrix2ListTest {
    @Test
    public void when2on2ArrayThenList4() {
        ConvertMatrix2List list = new ConvertMatrix2List();
        int[][] input = {
                {1, 2},
                {3, 4}
        };
        List<Integer> expect = Arrays.asList(
                1, 2, 3, 4
        );
        List<Integer> result = list.toList(input);
        assertThat(result, is(expect));
    }

    @Test
    public void when3on3ArrayThenList9() {
        ConvertMatrix2List list = new ConvertMatrix2List();
        int[][] input = {{1, 3, 5},
                {6, 7, 8},
                {9, 10, 11}};
        List<Integer> expect = Arrays.asList(1, 3, 5, 6, 7, 8, 9, 10, 11);
        List<Integer> result = list.toList(input);
        assertThat(result, is(expect));
    }
}