package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrderConvertTest {

    @Test
    public void whenSingleOrderThen() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("3sfe", "Dress"));
        HashMap<String, Order> map = OrderConvert.process(orders);
        assertThat(map.get("3sfe"), is(new Order("3sfe", "Dress")));
    }

    @Test
    public void whenTwoOrdersThen() {
        List<Order> orders = new ArrayList<>(Arrays.asList(
                new Order("123k", "Paints"),
                new Order("j4uj", "Shorts")
        ));
        HashMap<String, Order> map = OrderConvert.process(orders);
        assertThat(map.get("j4uj"), is(new Order("j4uj", "Shorts")));
    }

    @Test
    public void whenFourOrdersAndTwoDuplicateThen() {
        List<Order> orders = new ArrayList<>(Arrays.asList(
                new Order("56g4", "Note"),
                new Order("56g4", "Note"),
                new Order("94kg", "Boots"),
                new Order("94kg", "Boots")
        ));
        HashMap<String, Order> map = OrderConvert.process(orders);
        assertThat(map.size(), is(2));
    }
}