package me.eexxlliinn.task2.mappers;

import jakarta.servlet.http.HttpServletRequest;
import me.eexxlliinn.task2.dtos.ProductRequest;
import me.eexxlliinn.task2.dtos.ProductResponse;
import me.eexxlliinn.task2.entities.ProductEntity;
import me.eexxlliinn.task2.enums.ColorEnum;
import me.eexxlliinn.task2.enums.SizeEnum;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    public ProductRequest toProductRequest(HttpServletRequest request) {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName(request.getParameter("name"));
        productRequest.setColor(ColorEnum.valueOf(request.getParameter("color")));
        productRequest.setSize(SizeEnum.valueOf(request.getParameter("size")));
        productRequest.setPrice(Float.parseFloat(request.getParameter("price")));
        productRequest.setAmount(Long.parseLong(request.getParameter("amount")));
        return productRequest;
    }

    public ProductEntity toEntity(ProductRequest productRequest) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productRequest.getName());
        productEntity.setColor(productRequest.getColor());
        productEntity.setSize(productRequest.getSize());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setAmount(productRequest.getAmount());
        return productEntity;
    }

    public ProductResponse toDto(ProductEntity productEntity) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(productEntity.getId());
        productResponse.setName(productEntity.getName());
        productResponse.setColor(productEntity.getColor());
        productResponse.setSize(productEntity.getSize());
        productResponse.setPrice(productEntity.getPrice());
        productResponse.setAmount(productEntity.getAmount());
        return productResponse;
    }

    public List<ProductResponse> toDtoList(List<ProductEntity> productEntities) {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (ProductEntity productEntity : productEntities) {
            productResponses.add(toDto(productEntity));
        }
        return productResponses;
    }
}
