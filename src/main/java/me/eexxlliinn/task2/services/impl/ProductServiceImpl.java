package me.eexxlliinn.task2.services.impl;

import me.eexxlliinn.task2.dtos.ProductRequest;
import me.eexxlliinn.task2.dtos.ProductResponse;
import me.eexxlliinn.task2.entities.ProductEntity;
import me.eexxlliinn.task2.mappers.ProductMapper;
import me.eexxlliinn.task2.repositories.ProductRepository;
import me.eexxlliinn.task2.repositories.impl.ProductRepositoryImpl;
import me.eexxlliinn.task2.services.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository = new ProductRepositoryImpl();
    private final ProductMapper productMapper = new ProductMapper();

    @Override
    public ProductResponse getProductById(long productId) {
        ProductEntity product = productRepository.getProduct(productId);
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<ProductEntity> products = productRepository.getProducts();
        return productMapper.toDtoList(products);
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        ProductEntity product = productMapper.toEntity(productRequest);
        productRepository.saveProduct(product);
        return productMapper.toDto(product);
    }

    @Override
    public ProductResponse updateProduct(long productId, ProductRequest productRequest) {
        ProductEntity product = productRepository.getProduct(productId);
        product.setName(productRequest.getName());
        product.setColor(productRequest.getColor());
        product.setSize(productRequest.getSize());
        product.setPrice(productRequest.getPrice());
        product.setAmount(productRequest.getAmount());
        productRepository.updateProduct(product);
        return productMapper.toDto(product);
    }

    @Override
    public void deleteProduct(long productId) {
        productRepository.deleteProduct(productId);
    }
}
