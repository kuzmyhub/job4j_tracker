package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import java.util.List;

public class HQLUsage {
    public static void main(String[] args) {
        StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .configure().build();
        try (SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory()) {
            Session session = sf.openSession();
            insert(session, "anotherItem");
            delete(session, 1);
            Query query = session.createQuery(
                    "from Item", Item.class
            );
            for (Object st : query.getResultList()) {
                System.out.println(st);
            }
            update(session, 2);
            findById(session, 2);
            session.close();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static void findById(Session session, int id) {
        Query query = session.createQuery(
                "from Item i where i.id = :fId", Item.class
        );
        query.setParameter("fId", id);
        System.out.println(query.getResultList());
    }

    public static void update(Session session, int id) {
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE Item SET name = :fName WHERE id = :fId")
                    .setParameter("fName", "newName")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    public static void delete(Session session, int id) {
        try {
            session.beginTransaction();
            session.createQuery("DELETE Item WHERE ID = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    public static void insert(Session session, String name) {
        try {
            session.beginTransaction();
            session.createNativeQuery(
                    "INSERT INTO items (name) VALUES (:fName)"
            )
                    .setParameter("fName", name)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }
}
