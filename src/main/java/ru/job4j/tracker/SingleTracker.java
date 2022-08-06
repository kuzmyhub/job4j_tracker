package ru.job4j.tracker;

import java.util.List;

public final class SingleTracker {
    private static SingleTracker singleTracker = null;

    private SingleTracker() {
    }

    private Store store = new MemTracker();

    public static SingleTracker addInstance() {
        if (singleTracker == null) {
            singleTracker = new SingleTracker();
        }
        return singleTracker;
    }

    public Item add(Item item) {
        return store.add(item);
    }

    public Item findById(int id) {
        return store.findById(id);
    }

    public List<Item> findByName(String key) {
        return store.findByName(key);
    }

    public List<Item> findAll() {
        return store.findAll();
    }

    public boolean replace(int id, Item item) {
        return store.replace(id, item);
    }

    public boolean delete(int id) {
        return store.delete(id);
    }
}
