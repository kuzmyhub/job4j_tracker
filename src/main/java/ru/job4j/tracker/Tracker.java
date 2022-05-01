package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < size; index++) {
            if (items[index].getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    public boolean replace(int id, Item item) {
        boolean rsl = false;
        if (indexOf(id) != -1) {
            items[indexOf(id)].setName(item.getName());
            rsl = true;
        }
        return rsl;
    }

    public Item[] findByName(String key) {
        Item[] itemsMatchingNames = new Item[size];
        int sizeMatchingNames = 0;
        for (int i = 0; i < itemsMatchingNames.length; i++) {
            if (key.equals(items[i].getName())) {
                itemsMatchingNames[sizeMatchingNames] = items[i];
                sizeMatchingNames++;
            }
        }
        itemsMatchingNames = Arrays.copyOf(itemsMatchingNames, sizeMatchingNames);
        return itemsMatchingNames;
    }

    public Item[] findAll(Item[] items) {
        Item[] itemsWithoutNull = new Item[size];
        int sizeWithoutNull = 0;
        for (int i = 0; i < itemsWithoutNull.length; i++) {
            if (items[i] != null) {
                itemsWithoutNull[sizeWithoutNull] = items[i];
                sizeWithoutNull++;
            }
        }
        itemsWithoutNull = Arrays.copyOf(itemsWithoutNull, sizeWithoutNull);
        return itemsWithoutNull;
    }

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items[index] : null;
    }

    public Item[] getItems() {
        return items;
    }
}