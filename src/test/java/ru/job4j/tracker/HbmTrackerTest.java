package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class HbmTrackerTest {

    @After
    public void cleanTable() throws Exception {
        try (var tracker = new HbmTracker()) {
            SessionFactory sf = tracker.getSessionFactory();
            Session session = sf.openSession();
            try {
                session.beginTransaction();
                session.createQuery("DELETE Item")
                        .executeUpdate();
                session.getTransaction().commit();
                session.close();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName(), is(item.getName()));
        }
    }

    @Test
    public void whenReplaceItemThenTrackerHasReplacedItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            Item itemForReplace = new Item();
            itemForReplace.setName("replace");
            tracker.replace(item.getId(), itemForReplace);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName(), is(itemForReplace.getName()));
        }
    }

    @Test
    public void whenDeleteItemThenTrackerHasDeletedItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            boolean rsl = tracker.delete(item.getId());
            assertTrue(rsl);
        }
    }

    @Test
    public void whenFindAllThenTrackerFindAllItems() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item firstItem = new Item();
            firstItem.setName("test1");
            Item secondItem = new Item();
            secondItem.setName("test2");
            tracker.add(firstItem);
            tracker.add(secondItem);
            List<Item> items = tracker.findAll();
            assertThat(items.get(0).getName(), is(firstItem.getName()));
            assertThat(items.get(1).getName(), is(secondItem.getName()));
        }
    }

    @Test
    public void whenFindByNameThenTrackerFindAlItems() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item firstItem = new Item();
            firstItem.setName("test1");
            Item secondItem = new Item();
            secondItem.setName("test1");
            tracker.add(firstItem);
            tracker.add(secondItem);
            List<Item> items = tracker.findByName("test1");
            assertThat(items.get(0).getName(), is(firstItem.getName()));
            assertThat(items.get(1).getName(), is(secondItem.getName()));
        }
    }
}