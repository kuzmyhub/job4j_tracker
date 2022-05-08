package ru.job4j.myDecorator;

public class DecorTea implements Dishes {
    Dinner dinner;
    private String teaVariety;

    public DecorTea(Dinner dinner, String teaVariety) {
        this.dinner = dinner;
        this.teaVariety = teaVariety;
    }

    @Override
    public void firstDish() {
        this.dinner.firstDish();
    }

    @Override
    public void secondDish() {
        this.dinner.secondDish();
        System.out.println("Шлифануть чайком " + teaVariety);
    }
}
