package me.eexxlliinn.task2.repositories.impl;

import lombok.extern.slf4j.Slf4j;
import me.eexxlliinn.task2.entities.UserEntity;
import me.eexxlliinn.task2.repositories.UserRepository;
import me.eexxlliinn.task2.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public UserEntity findByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            UserEntity userEntity = session.createQuery("FROM UserEntity WHERE username = :username", UserEntity.class)
                    .setParameter("username", username)
                    .uniqueResult();
            return userEntity;
        }
    }

    @Override
    public void save(UserEntity user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Error saving user", e);
            throw new RuntimeException(e);
        }
    }
}
