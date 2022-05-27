package ru.job4j.collection;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Collections;

public class Departments {

    public static void main(String[] args) {
        List<String> list = List.of(
                "K1/SK1",
                "K1/SK1/SSK1"
        );
        List<String> list1 = fillGaps(list);
        System.out.println(list1);
        sortAsc(list1);
        System.out.println(list1);
        sortDesc(list1);
        System.out.println(list1);
    }

    public static List<String> fillGaps(List<String> deps) {
        Set<String> tmp = new LinkedHashSet<>();
        for (String value : deps) {
            String start = value.substring(0, 2);
            for (String el : value.split("/")) {
                tmp.add(start);
                if (!el.equals(start)) {
                    tmp.add(start + "/" + el);
                }
            }
        }
        return new ArrayList<>(tmp);
    }

    public static void sortAsc(List<String> orgs) {
        Collections.sort(orgs);
    }

    public static void sortDesc(List<String> orgs) {
        Collections.sort(orgs, new DepDescComp());
    }
}
