package ru.job4j.myDecorator;

public class Dinner implements Dishes {
    private String first;
    private String second;

    public Dinner(String first, String second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public void firstDish() {
        System.out.println("Отведать " + first);
    }

    @Override
    public void secondDish() {
        System.out.println("Отведать " + second);
    }
}
