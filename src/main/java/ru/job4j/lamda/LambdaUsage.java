package ru.job4j.lamda;

import java.util.Arrays;
import java.util.List;
import java.util.Comparator;

public class LambdaUsage {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList(
                "eeeee",
                "a",
                "ccc",
                "dddd",
                "bb"
        );
        Comparator<String> comparator = (left, right) -> {
            System.out.println("compare - "
                    + left.length() + " : " + right.length());
            return Integer.compare(right.length(), left.length());
        };
        strings.sort(comparator);
        for (String str : strings) {
            System.out.println(str);
        }
    }
}
