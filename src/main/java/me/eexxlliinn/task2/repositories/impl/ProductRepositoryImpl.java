package me.eexxlliinn.task2.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import me.eexxlliinn.task2.entities.ProductEntity;
import me.eexxlliinn.task2.repositories.ProductRepository;

import java.util.List;
import java.util.UUID;

public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ProductEntity getProduct(UUID id) {
        return entityManager.find(ProductEntity.class, id);
    }

    @Override
    public List<ProductEntity> getProducts() {
        return entityManager.createQuery("SELECT p FROM ProductEntity p", ProductEntity.class).getResultList();
    }

    @Override
    @Transactional
    public void saveProduct(ProductEntity product) {
        entityManager.persist(product);
    }

    @Override
    public void updateProduct(ProductEntity product) {
        entityManager.merge(product);
    }

    @Override
    public void deleteProduct(UUID id) {
        ProductEntity product = getProduct(id);
        if (product != null) {
            entityManager.remove(product);
        }
    }
}
