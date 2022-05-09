package ru.job4j.myFactory;

public class MakeInstrumentation extends DishesMaker {
    @Override
    Dishes makeDish() {
        return new Instrumentation();
    }
}
