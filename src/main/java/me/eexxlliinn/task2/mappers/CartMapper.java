package me.eexxlliinn.task2.mappers;

import jakarta.servlet.http.HttpServletRequest;
import me.eexxlliinn.task2.dtos.CartProductResponse;
import me.eexxlliinn.task2.dtos.CartRequest;
import me.eexxlliinn.task2.dtos.CartResponse;
import me.eexxlliinn.task2.entities.CartEntity;
import me.eexxlliinn.task2.entities.CartProductEntity;
import me.eexxlliinn.task2.enums.CurrencyEnum;

public class CartMapper {

    public CartResponse toDto(CartEntity cartEntity) {
        CartResponse cartResponse = new CartResponse();
        cartResponse.setCartId(cartEntity.getId());
        cartResponse.setUserId(cartEntity.getUserId());
        cartResponse.setCurrency(cartEntity.getCurrency());
        cartResponse.setCartProducts(cartEntity.getProducts()
                .stream()
                .map(this::toDto)
                .toList());
        return cartResponse;
    }

    public CartEntity toEntity(CartRequest cartRequest) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setUserId(cartRequest.getUserId());
        cartEntity.setCurrency(cartRequest.getCurrency());
        return cartEntity;
    }

    public CartProductResponse toDto(CartProductEntity cartProductEntity) {
        CartProductResponse cartProductResponse = new CartProductResponse();
        cartProductResponse.setProductId(cartProductEntity.getId());
        cartProductResponse.setAmount(cartProductEntity.getAmount());
        return cartProductResponse;
    }

    public CartRequest toDto(HttpServletRequest request) {
        CartRequest cartRequest = new CartRequest();
        cartRequest.setUserId(Long.parseLong(request.getParameter("userId")));
        cartRequest.setCurrency(CurrencyEnum.valueOf(request.getParameter("currency")));
        return cartRequest;
    }
}
