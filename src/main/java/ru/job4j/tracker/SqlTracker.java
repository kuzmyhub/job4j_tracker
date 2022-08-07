package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {

    private final List<Item> items = new ArrayList<>();

    private Connection cn;

    public void init() {
        try (InputStream in = SqlTracker.class
                .getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn !=null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement statement
                     = cn.prepareStatement("insert into items(name, created) values(?, ?);")) {
            statement.setString(1, item.getName());
            Timestamp timestampFromLDT = Timestamp.valueOf(item.getCreated());
            statement.setTimestamp(2, timestampFromLDT);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = false;
        try (PreparedStatement statement
                     = cn.prepareStatement("update items set name = ?, created = ? where id = ?;")) {
            statement.setString(1, item.getName());
            Timestamp timestampFromLDT = Timestamp.valueOf(item.getCreated());
            statement.setTimestamp(2, timestampFromLDT);
            statement.setInt(3, id);
            rsl = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = false;
        try (PreparedStatement statement
                     = cn.prepareStatement("delete from items where id = ?;")) {
            statement.setInt(1, id);
            statement.execute();
            rsl = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        try (PreparedStatement statement
                     = cn.prepareStatement("select id, name, created from items;")) {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                items.add(getItem(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.copyOf(items);
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement statement
                     = cn.prepareStatement("select id, name, created from items where name = ?;")) {
            statement.setString(1, key);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(getItem(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        try (PreparedStatement statement =
                     cn.prepareStatement("select id, name, created from items where id = ?;")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                item = getItem(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    public Item getItem(ResultSet resultSet) throws SQLException {
        Item item = new Item();
        item.setId(resultSet.getInt(1));
        item.setName(resultSet.getString(2));
        Timestamp timestamp = resultSet.getTimestamp(3);
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        item.setCreated(localDateTime);
        return item;
    }
}
