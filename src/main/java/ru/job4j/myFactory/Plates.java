package ru.job4j.myFactory;

public class Plates implements Dishes {
    @Override
    public void type() {
        System.out.println("Из них едят");
    }
}
