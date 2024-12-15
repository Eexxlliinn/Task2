package me.eexxlliinn.task2.repositories.impl;

import lombok.extern.slf4j.Slf4j;
import me.eexxlliinn.task2.entities.RoleEntity;
import me.eexxlliinn.task2.repositories.RoleRepository;
import me.eexxlliinn.task2.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Slf4j
public class RoleRepositoryImpl implements RoleRepository {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public RoleEntity getByName(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            RoleEntity role = session.createQuery("FROM RoleEntity WHERE name =: name", RoleEntity.class)
                    .setParameter("name", roleName)
                    .uniqueResult();
            return role;
        }
    }

    @Override
    public void save(RoleEntity role) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Error saving role", e);
            throw new RuntimeException(e);
        }
    }
}
