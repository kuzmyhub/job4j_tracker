package ru.job4j.myDecorator;

public class Eating {
    public static void main(String[] args) {
        Dishes dinner = new Dinner("soup", "potato");
        dinner.firstDish();
        dinner.secondDish();
        System.out.println();
        Dishes dinnerWithTea = new DecorTea(new Dinner("soup", "potato"), "black tea");
        dinnerWithTea.firstDish();
        dinnerWithTea.secondDish();
    }
}
