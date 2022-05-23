package ru.job4j.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.List;

public class CountingFunction {
    List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (int i = start; i < end; i++) {
            result.add(func.apply((double) i));
        }
        return result;
    }
}
