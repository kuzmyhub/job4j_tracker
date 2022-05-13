package ru.job4j.collection;

import java.util.HashSet;
import java.util.List;
import java.util.Arrays;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;



import static org.junit.Assert.*;

public class NotifyAccountTest {

    @Test
    public void whenSentThenNoDuplicate() {
        List<Account> accounts = Arrays.asList(
                new Account("123", "Petr Arsentev", "eDer3432f"),
                new Account("142", "Petr Arsentev", "000001")
        );
        HashSet<Account> expect = new HashSet<>(
                Arrays.asList(
                        new Account("123", "Petr Arsentev", "eDer3432f"),
                        new Account("142", "Petr Arsentev", "000001")
                )
        );
        assertThat(NotifyAccount.sent(accounts), is(expect));
    }

    @Test
    public void whenSentThen1Duplicate() {
        List<Account> accounts = Arrays.asList(
                new Account("555", "Ivan Dunaev", "82k8kd9p"),
                new Account("232", "Pavel Dhonov", "9238jd8"),
                new Account("555", "Ivan Dunaev", "82k8kd9p")
                );

        HashSet<Account> expected = new HashSet<>(
                Arrays.asList(
                        new Account("555", "Ivan Dunaev", "82k8kd9p"),
                        new Account("232", "Pavel Dhonov", "9238jd8")
                )
        );
        assertThat(NotifyAccount.sent(accounts), is(expected));
    }

    @Test
    public void whenSentThen2Duplicate() {
        List<Account> accounts = Arrays.asList(
                new Account("234", "Olga Znova", "9sksd9jo"),
                new Account("000", "Ivan Slyagin", "234dr5g56"),
                new Account("234", "Olga Znova", "9sksd9jo"),
                new Account("000", "Ivan Slyagin", "234dr5g56")

        );

        HashSet<Account> expected = new HashSet<>(
                Arrays.asList(
                        new Account("234", "Olga Znova", "9sksd9jo"),
                        new Account("000", "Ivan Slyagin", "234dr5g56")
                )
        );
        assertThat(NotifyAccount.sent(accounts), is(expected));
    }
}