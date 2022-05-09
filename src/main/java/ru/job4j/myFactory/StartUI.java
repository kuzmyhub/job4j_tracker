package ru.job4j.myFactory;

public class StartUI {
    public static void main(String[] args) {
        DishesMaker dishesMaker = new MakeInstrumentation();
        Dishes instrumentation = dishesMaker.makeDish();
        instrumentation.type();
    }
}
