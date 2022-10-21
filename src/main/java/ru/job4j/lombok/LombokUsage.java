package ru.job4j.lombok;

public class LombokUsage {
    public static void main(String[] args) {
        var permission = Permission.of()
                .id(1)
                .name("allowed")
                .rules("follows the rules")
                .build();
        System.out.println(permission);
    }
}
