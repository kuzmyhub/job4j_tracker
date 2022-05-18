package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс создаёт новый банковский счёт пользователя {@link User}
 * @author KUZNETSOV SERGEY
 * @version 1.0
 */
public class Account {
    private String requisite;
    private double balance;

    /**
     *
     * @param requisite - номер банковского счёта
     * @param balance - сумма, которая лежит на банковском счету
     */
    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }

    /**
     *
     * Метод возвращает номер банковского счёта
     */
    public String getRequisite() {
        return requisite;
    }

    /**
     *
     * Метод принимает новый номер банковского счёта пользователя
     * и устанавливает его новое значение
     */
    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    /**
     *
     * Метод возвращает сумму на банковском счету
     */
    public double getBalance() {
        return balance;
    }

    /**
     *
     * Метод принимает новую сумму на банковском счету
     * и устанавливает её новое значение
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(requisite, account.requisite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisite);
    }
}
