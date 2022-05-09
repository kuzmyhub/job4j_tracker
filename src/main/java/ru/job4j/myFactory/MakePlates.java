package ru.job4j.myFactory;

public class MakePlates extends DishesMaker {
    @Override
    Dishes makeDish() {
        return new Plates();
    }
}
