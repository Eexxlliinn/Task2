package me.eexxlliinn.task2.repositories;

import me.eexxlliinn.task2.entities.ProductEntity;

import java.util.List;
import java.util.UUID;

public interface ProductRepository {
    ProductEntity getProduct(UUID id);
    List<ProductEntity> getProducts();
    void saveProduct(ProductEntity product);
    void updateProduct(ProductEntity product);
    void deleteProduct(UUID id);
}
