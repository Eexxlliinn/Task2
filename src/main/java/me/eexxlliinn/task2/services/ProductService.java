package me.eexxlliinn.task2.services;

import me.eexxlliinn.task2.dtos.ProductRequest;
import me.eexxlliinn.task2.dtos.ProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductResponse getProductById(UUID productId);

    List<ProductResponse> getAllProducts();

    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse updateProduct(UUID productId, ProductRequest productRequest);

    void deleteProduct(UUID productId);
}
