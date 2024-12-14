package me.eexxlliinn.task2.services;

import me.eexxlliinn.task2.dtos.ProductRequest;
import me.eexxlliinn.task2.dtos.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse getProductById(long productId);

    List<ProductResponse> getAllProducts();

    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse updateProduct(long productId, ProductRequest productRequest);

    void deleteProduct(long productId);
}
