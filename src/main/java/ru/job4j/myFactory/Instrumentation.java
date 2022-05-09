package ru.job4j.myFactory;

public class Instrumentation implements Dishes {
    @Override
    public void type() {
        System.out.println("Ими едят");
    }
}
