package me.eexxlliinn.task2.repositories;

import me.eexxlliinn.task2.entities.ProductEntity;

import java.util.List;

public interface ProductRepository {
    ProductEntity getProduct(long productId);
    List<ProductEntity> getProducts();
    void saveProduct(ProductEntity product);
    void updateProduct(ProductEntity product);
    void deleteProduct(long productId);
}
