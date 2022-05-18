package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс создаёт новых пользователей
 * @author KUZNETSOV SERGEY
 * @version 1.0
 */
public class User {
    private String passport;
    private String username;

    /**
     * @param passport - номер парспорта пользователя;
     * @param username - имя пользователя.
     */
    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

    /**
     *
     * @return метод возвращает номер паспорта пользователя
     */
    public String getPassport() {
        return passport;
    }

    /**
     *
     * Метод принимает новый номер паспорта пользователя
     * и устанавливает его новое значение
     */
    public void setPassport(String passport) {
        this.passport = passport;
    }

    /**
     *
     * Метод возвращает имя пользователя
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * Метод принимает новое имя пользователя
     * и устанавливает его новое значение
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(passport, user.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }
}
