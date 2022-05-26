package ru.job4j.bank;

import java.util.*;

/**
 * Класс реализует работу банковского приложения
 * @author KUZNETSOV SERGEY
 * @version 1.0
 */
public class BankService {
    /**
     * Хранение пользователей {@link User} и их банковских счетов {@link Account} пользователей
     * осуществляется в коллекции типа HashMap
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     *
     * Метод принимает на вход пользователя и добавляет его
     * и его пустой список банковских счетов в виде коллекции ArrayList
     * в коллекцию типа HashMap
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     *
     * Метод принимает на вход номер паспорта пользователя и банковский счёт.
     * Если такой пользователь существует, то добавляет в список
     * счетов пользователя новый банковский счёт
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     *
     * Метод принимает на вход номер паспорта пользователя и циклом
     * {@code foreach} проверяет содержится ли в кколлекции
     * типа HashMap полльзователь с таким же номером паспорта.
     * @param passport номер паспорта пользователя
     * @return В зависимости от результата возвращает объект
     * типа {@linl User} или null;
     */
    public User findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(u -> u.getPassport().equals(passport))
                .findFirst().orElse(null);
    }

    /**
     *
     * Метод принимает навход номер паспорта пользователя и
     * номер его банковского счёта. Если такой пользователь
     * существует, циклом {@code for} проверяет существует ли
     * у данного пользователя банковский счёт с подобными номером
     * банковского счёта.
     * @param passport номер паспорта пользователя
     * @param requisite номер счёта пользователя
     * @return в зависимости от результата возвращает
     * банковский счёт пользователя {@link Account} или null;
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            return users.get(user)
                    .stream()
                    .filter(a -> a.getRequisite().equals(requisite))
                    .findFirst()
                    .orElse(null);
        }
        System.out.println("Пользователь или аккаунт не найдены");
        return null;
    }

    /**
     *
     * Метод принимает на вход номер паспорта пользоватля и номер
     * банковского счёта пользователя с которого будет списана
     * некоторая сумма, а так же номер паспорта и номер банковского
     * счёта пользователя на который будет переведена сумма
     * @param srcPassport номер паспорта пользоватя с чьего счёта
     *                    будет списана некоторая сумма
     * @param srcRequisite номер банковского счёта с которого будет
     *                     списанная некоторая сумма
     * @param destPassport номер паспорта пользователя на чей счёт
     *                     будет переведена некоторая мусса
     * @param destRequisite номер банковского счёта на который будет
     *                      зачисленна некоторая сумма
     * @param amount сумма которая будет списана/переведена
     * @return при реализации операции списывания/зачисления возвращает
     * {@code true}. Если операцию не удалось совершить, возвращает null
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount != null
                && destAccount != null
                && srcAccount.getBalance() >= amount) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            destAccount.setBalance(destAccount.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }
}
