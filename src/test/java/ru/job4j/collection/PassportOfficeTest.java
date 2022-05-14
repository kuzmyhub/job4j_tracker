package ru.job4j.collection;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PassportOfficeTest {

    @Test
    public void whenAddNoneDuplicate() {
        Citizen citizen = new Citizen("2323 232323", "Evgenyu Ermalov");
        PassportOffice office = new PassportOffice();
        office.add(citizen);
        assertThat(office.get(citizen.getPassport()), is(citizen));
    }

    @Test
    public void whenAddDuplicateThenFalse() {
        PassportOffice office = new PassportOffice();
        office.add(new Citizen("1212 121212", "Evgeniya Zmorina"));
        assertFalse(
                office.add(new Citizen("1212 121212", "Evgeniya Zmorina"))
        );

    }
}