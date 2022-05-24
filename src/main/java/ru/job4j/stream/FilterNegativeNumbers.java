package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FilterNegativeNumbers {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(-1, 2, 5, 15, -2, -3, 9);
        List<Integer> positive = numbers.
                stream().filter(
                        x -> x > 0
                ).collect(Collectors.toList());
    }
}
