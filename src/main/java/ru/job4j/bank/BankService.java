package ru.job4j.bank;

import java.util.*;

public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        if (!users.containsKey(user)) {
            users.put(user, new ArrayList<Account>());
        } else {
            System.out.println("Пользователь уже есть в базе");
        }
    }

    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (!users.get(user).contains(account)) {
            users.get(user).add(account);
            System.out.println("Аккаунт добавлен");
        } else {
            System.out.println("У пользователья есть такой аккаунт");
        }
    }

    public User findByPassport(String passport) {
        for (User u : users.keySet()) {
            if (u.getPassport().equals(passport)) {
                System.out.println("Пользователь найден");
                return u;
            }
        }
        System.out.println("Пользователь не найден");
        return null;
    }

    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            for (Account acc : users.get(user)) {
                if (acc.getRequisite().equals(requisite)) {
                    System.out.println("Аккаунт найдейн");
                    return acc;
                }
            }
        }
        System.out.println("Пользователь или аккаунт не найдены");
        return null;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        User user = findByPassport(destPassport);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount != null
                && srcAccount.getBalance() >= amount
                && user != null) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            if (destAccount != null) {
                destAccount.setBalance(destAccount.getBalance() + amount);
            } else {
                users.get(user).add(new Account(destRequisite, amount));
            }
            rsl = true;
        }
        return rsl;
    }
}
