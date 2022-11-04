package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class HbmTracker implements Store, AutoCloseable{

    private final StandardServiceRegistry registry =
            new StandardServiceRegistryBuilder()
                    .configure()
                    .build();
    private final SessionFactory sf =
            new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private Session getSession() {
        return sf.openSession();
    }

    @Override
    public Item add(Item item) {
        Session session = getSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        int rsl = 0;
        Session session = getSession();
        session.beginTransaction();
        try {
            rsl = session.createQuery(
                    "UPDATE Item SET name = :fName WHERE id = :fId"
                    )
                    .setParameter("fName", item.getName())
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return rsl > 0;
    }

    @Override
    public boolean delete(int id) {
        Session session = getSession();
        int rsl = 0;
        try {
            session.beginTransaction();
            rsl = session.createQuery("DELETE Item i WHERE i.id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return rsl > 0;
    }

    @Override
    public List<Item> findAll() {
        List<Item> list;
        Session session = getSession();
        try {
            session.beginTransaction();
            list = session.createQuery("FROM Item").list();
            session.getTransaction().commit();
            session.close();
            return list;
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = getSession();
        Query query = session.createQuery(
                "FROM Item i WHERE i.name LIKE :fName", Item.class
        );
        query.setParameter("fName", "%" + key + "%");
        List list = query.getResultList();
        session.close();
        return list;
    }

    @Override
    public Item findById(int id) {
        Session session = getSession();
        Query query = session.createQuery(
                        "FROM Item i WHERE i.id = :fId", Item.class
        );
        query.setParameter("fId", id);
        Item item = (Item) query.getSingleResult();
        session.close();
        return item;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    public SessionFactory getSessionFactory() {
        return sf;
    }

    public void delete() {
        Session session = getSession();
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
