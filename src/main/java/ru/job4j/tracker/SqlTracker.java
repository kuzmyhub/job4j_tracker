package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
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
            create(cn);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public void create(Connection cn) throws SQLException {
        try (Statement statement = cn.createStatement()) {
            String sql = "create table if not exists tracker(id serial primary key, name text, created timestamp);";
            statement.execute(sql);
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
                     = cn.prepareStatement("insert into tracker(name, created) values(?, ?);")) {
            statement.setString(1, item.getName());
            Timestamp timestampFromLDT = Timestamp.valueOf(item.getCreated());
            statement.setTimestamp(2, timestampFromLDT);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement statement
                     = cn.prepareStatement("select id from tracker where name = ? and created = ?;")) {
            statement.setString(1, item.getName());
            Timestamp timestampLDT = Timestamp.valueOf(item.getCreated());
            statement.setTimestamp(2, timestampLDT);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                item.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = false;
        try (PreparedStatement statement
                     = cn.prepareStatement("update tracker set name = ? where id = ?;"
                             + "update tracker set created = ? where id = ?;")) {
            statement.setString(1, item.getName());
            statement.setInt(2, id);
            Timestamp timestampFromLDT = Timestamp.valueOf(item.getCreated());
            statement.setTimestamp(3, timestampFromLDT);
            statement.setInt(4, id);
            statement.execute();
            rsl = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = false;
        try (PreparedStatement statement
                     = cn.prepareStatement("delete from tracker where id = ?;")) {
            statement.setInt(1, id);
            statement.execute();
            rsl = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        items.clear();
        try (PreparedStatement statement
                     = cn.prepareStatement("select id, name from tracker;")) {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt(1));
                item.setName(resultSet.getString(2));
                items.add(item);
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
                     = cn.prepareStatement("select id, name from tracker where name = ?;")) {
            statement.setString(1, key);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt(1));
                item.setName(resultSet.getString(2));
                result.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Item findById(int id) {
        Item item = new Item();
        try (PreparedStatement statement =
                     cn.prepareStatement("select id, name from tracker where id = ?;")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                item.setId(resultSet.getInt(1));
                item.setName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
}
