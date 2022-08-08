package ru.job4j.tracker;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeClass
    public static void initConnection() {
        try (InputStream in = SqlTrackerTest.class.getClassLoader().getResourceAsStream("test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @After
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemThenResultSetMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        Item testItem = null;
        try (PreparedStatement statement
                     = connection.prepareStatement(
                             "select id, name, created from items;"
        )) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            testItem = tracker.getItem(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertThat(item, is(testItem));
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId()), is(item));
    }

    @Test
    public void whenSaveItemAndFindByNameThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item firstItem = new Item("item");
        tracker.add(firstItem);
        Item secondItem = new Item("item");
        tracker.add(secondItem);
        Item thirdItem = new Item("otherItem");
        tracker.add(thirdItem);
        Item fourthItem = new Item("item");
        tracker.add(fourthItem);
        List<Item> items = List.of(
                new Item(firstItem.getId(), "item"),
                new Item(secondItem.getId(), "item"),
                new Item(fourthItem.getId(), "item")
        );
        assertThat(tracker.findByName("item"), is(items));
    }

    @Test
    public void whenSaveItemAndFindAllThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item firstItem = new Item("item");
        tracker.add(firstItem);
        Item secondItem = new Item("item");
        tracker.add(secondItem);
        Item thirdItem = new Item("otherItem");
        tracker.add(thirdItem);
        Item fourthItem = new Item("item");
        tracker.add(fourthItem);
        List<Item> items = List.of(
                new Item(firstItem.getId(), "item"),
                new Item(secondItem.getId(), "item"),
                new Item(thirdItem.getId(), "otherItem"),
                new Item(fourthItem.getId(), "item")
        );
        assertThat(tracker.findAll(), is(items));
    }

    @Test
    public void whenSaveItemAndDeleteThenTrue() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertTrue(tracker.delete(item.getId()));
    }

    @Test
    public void whenSaveItemAndDeleteThenFalse() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertFalse(tracker.delete(0));
    }

    @Test
    public void whenSaveItemAdDeleteThenItemNotFound() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        tracker.delete(item.getId());
        assertThat(tracker.findByName("item"), is(List.of()));
    }

    @Test
    public void whenSaveItemAndReplaceThenTrue() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertTrue(tracker.replace(item.getId(), new Item("testItem")));
    }

    @Test
    public void whenSaveItemAndReplaceThenFalse() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertFalse(tracker.replace(0, new Item("testItem")));
    }

    @Test
    public void whenSaveItemAndReplaceThenFindReplacedItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        tracker.replace(item.getId(), new Item("testItem"));
        assertThat(tracker.findByName("testItem"),
                is(List.of(new Item(item.getId(), "testItem"))));
    }
}