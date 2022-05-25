package ru.job4j.stream;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Comparator;

public class Profiles {
    public static List<Address> collect(List<Profile> profiles) {
        return profiles.stream().
                map(profile -> profile.getAddress()).
                collect(Collectors.toList());
    }

    public static List<Address> collectSortWithoutDuplicate(List<Profile> profiles) {
        Comparator<Address> cmp = Comparator.comparing(Address::getCity);
        return profiles.stream().
                map(profile -> profile.getAddress()).
                sorted(cmp).
                distinct().collect(Collectors.toList());
    }
}
