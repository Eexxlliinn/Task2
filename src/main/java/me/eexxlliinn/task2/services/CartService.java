package me.eexxlliinn.task2.services;

import me.eexxlliinn.task2.dtos.CartRequest;
import me.eexxlliinn.task2.dtos.CartResponse;

public interface CartService {
    CartResponse createCart(CartRequest cartRequest);
    CartResponse updateCart(long cartId, CartRequest cartRequest);
    CartResponse getCartById(long cartId);
    CartResponse getActiveCart(long userId);
    void addItemToCart(long cartId, long productId, int amount);
    void removeItemFromCart(long cartId, long productId, int amount);
    void clearCart(long cartId);
}
