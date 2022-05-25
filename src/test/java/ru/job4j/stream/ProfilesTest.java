package ru.job4j.stream;

import org.junit.Test;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.atomic.DoubleAdder;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class ProfilesTest {

    @Test
    public void whenMethodCollect() {
        Address first = new Address("City1", "Street1", 1, 1);
        Address second = new Address("City2", "Street2", 2, 2);
        Address third = new Address("City3", "Street3", 3, 3);
        List<Profile> profiles = Arrays.asList(
                new Profile(first),
                new Profile(second),
                new Profile(third)
        );
        List<Address> result = Profiles.collect(profiles);
        List<Address> expected = Arrays.asList(first, second, third);
        assertThat(result, is(expected));
    }

    @Test
    public void whenTestMethodCollectSortWithoutDuplicate() {
        Address first = new Address("City1", "Street1", 1, 1);
        Address second = new Address("City2", "Street2", 2, 2);
        Address third = new Address("City3", "Street3", 3, 3);
        Address fourth = new Address("City1", "Street1", 1, 1);
        Address fifth = new Address("City2", "Street2", 2, 2);
        Address sixth = new Address("City3", "Street3", 3, 3);
        Address seventh = new Address("City3", "Street3", 3, 3);
        List<Profile> profiles = Arrays.asList(
                new Profile(second),
                new Profile(fourth),
                new Profile(fifth),
                new Profile(first),
                new Profile(seventh),
                new Profile(third),
                new Profile(sixth)
        );
        List<Address> result = Profiles.collectSortWithoutDuplicate(profiles);
        List<Address> expected = Arrays.asList(first, second, third);
        assertThat(result, is(expected));
    }
}