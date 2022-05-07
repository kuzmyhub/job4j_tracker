package ru.job4j.myStrategy;

import javax.sound.midi.SysexMessage;
import java.lang.reflect.Type;

public class Athletes {
    private TypeTraining typeTraining;

    Athletes(TypeTraining typeTraining) {
        this.typeTraining = typeTraining;
    }

    public void eat() {
        System.out.println("Health food");
    }

    public void sleep() {
        System.out.println("Health sleep");
    }

    public void training() {
        typeTraining.training();
    }

    public static void main(String[] args) {
        Swimmer swimmer = new Swimmer();
        Runner runner = new Runner();
        Arrow arrow = new Arrow();

        swimmer.eat();
        swimmer.sleep();
        swimmer.training();
        System.out.println();
        runner.eat();
        runner.sleep();
        runner.training();
        System.out.println();
        arrow.eat();
        arrow.sleep();
        arrow.training();

    }
}
