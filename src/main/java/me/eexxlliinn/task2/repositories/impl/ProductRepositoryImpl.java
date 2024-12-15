package me.eexxlliinn.task2.repositories.impl;

import lombok.extern.slf4j.Slf4j;
import me.eexxlliinn.task2.entities.ProductEntity;
import me.eexxlliinn.task2.repositories.ProductRepository;
import me.eexxlliinn.task2.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

@Slf4j
public class ProductRepositoryImpl implements ProductRepository {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public ProductEntity getProduct(long productId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(ProductEntity.class, productId);
        } catch (Exception e) {
            log.error("Error getting product {}", productId, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductEntity> getProducts() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from ProductEntity", ProductEntity.class).list();
        } catch (Exception e) {
            log.error("Error getting products", e);
            throw new RuntimeException(e);
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
                if (transaction != null) {
                    transaction.rollback();
                }
                log.error("Error while saving product", e);
                throw new RuntimeException(e);
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
                if (transaction != null) {
                    transaction.rollback();
                }
                log.error("Error while updating product", e);
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void deleteProduct(long productId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                ProductEntity product = session.get(ProductEntity.class, productId);
                if (product != null) {
                    session.delete(product);
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                log.error("Error while deleting product {}", productId, e);
                throw new RuntimeException(e);
            }
        }
    }
}
