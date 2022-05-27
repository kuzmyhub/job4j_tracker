package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] o1Array = o1.split("/");
        String[] o2Array = o2.split("/");
        int minLength = Math.min(o1Array.length, o2Array.length);
        if (Integer.parseInt(o2Array[0].substring(o2Array[0].length() - 1))
                > Integer.parseInt(o1Array[0].substring(o1Array[0].length() - 1))) {
            return 1;
        } else if (Integer.parseInt(o2Array[0].substring(o2Array[0].length() - 1))
                < Integer.parseInt(o1Array[0].substring(o1Array[0].length() - 1))) {
            return -1;
        }

        for (int i = 1; i < minLength; i++) {
            if (Integer.parseInt(o2Array[i].substring(o2Array[i].length() - 1))
                    > Integer.parseInt(o1Array[i].substring(o1Array[i].length() - 1))) {
                return -1;
            } else if (Integer.parseInt(o2Array[i].substring(o2Array[i].length() - 1))
                    < Integer.parseInt(o1Array[i].substring(o1Array[i].length() - 1))) {
                return 1;
            }
        }

        boolean max = o2Array.length > o1Array.length;
        if (max) {
            return -1;
        } else {
            return 1;
        }
    }
}
