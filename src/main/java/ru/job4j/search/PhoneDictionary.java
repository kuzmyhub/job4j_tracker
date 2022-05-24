package ru.job4j.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        ArrayList<Person> result = new ArrayList<>();
        Comparator<Person> compareName = Comparator.comparing(Person::getName);
        Comparator<Person> compareSurname = Comparator.comparing(Person::getSurname);
        Comparator<Person> comparePhone = Comparator.comparing(Person::getPhone);
        Comparator<Person> compareAddress = Comparator.comparing(Person::getAddress);
        Comparator<Person> cmpCombine = compareName.
                thenComparing(compareSurname).
                thenComparing(comparePhone).
                thenComparing(compareAddress);
        Predicate<Person> nameContains = n -> n.getName().contains(key);
        Predicate<Person> surnameContains = s -> s.getSurname().contains(key);
        Predicate<Person> phoneContains = p -> p.getPhone().contains(key);
        Predicate<Person> addressContains = a -> a.getAddress().contains(key);
        Predicate<Person> prCombine = nameContains.
                or(surnameContains.
                        or(phoneContains.
                                or(addressContains)));
        for (Person value : persons) {
            if (prCombine.test(value)) {
                result.add(value);
            }
        }
        result.sort(cmpCombine);
        return result;
    }
}
