package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        ArrayList<Person> result = new ArrayList<>();
        Predicate<Person> nameContains = p -> p.getName().contains(key);
        Predicate<Person> surnameContains = p -> p.getSurname().contains(key);
        Predicate<Person> phoneContains = p -> p.getPhone().contains(key);
        Predicate<Person> addressContains = p -> p.getAddress().contains(key);
        Predicate<Person> prCombine = nameContains.
                or(surnameContains.
                        or(phoneContains.
                                or(addressContains)));
        for (var value : persons) {
            if (prCombine.test(value)) {
                result.add(value);
            }
        }
        return result;
    }
}
