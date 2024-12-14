package me.eexxlliinn.task2.repositories.impl;

import me.eexxlliinn.task2.entities.ProductEntity;
import me.eexxlliinn.task2.repositories.ProductRepository;
import me.eexxlliinn.task2.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public ProductEntity getProduct(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(ProductEntity.class, id);
        }
    }

    @Override
    public List<ProductEntity> getProducts() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from ProductEntity", ProductEntity.class).list();
        }
    }

    @Override
    public void saveProduct(ProductEntity product) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(product);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateProduct(ProductEntity product) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.update(product);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteProduct(long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                ProductEntity product = session.get(ProductEntity.class, id);
                if (product != null) {
                    session.delete(product);
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            }
        }
    }
}
